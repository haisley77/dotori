package com.dotori.backend.domain.room.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dotori.backend.domain.room.model.dto.RoomDTO;
import com.dotori.backend.domain.room.model.entity.Room;
import com.dotori.backend.domain.room.service.RoomServiceImpl;

import io.openvidu.java.client.Connection;
import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;

@CrossOrigin(origins = "*")
@RestController
@PropertySource("classpath:application-openvidu.yml")
@ConfigurationProperties(prefix = "openvidu")
public class RoomController {

	@Value("${url}")
	private String OPENVIDU_URL;

	@Value("${secret}")
	private String OPENVIDU_SECRET;

	private OpenVidu openvidu;

	@Autowired
	private RoomServiceImpl rs;

	@PostConstruct
	public void init() {
		this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
	}

	/**
	 * @param params The Session properties
	 * @return The Session ID
	 */
	@PostMapping("/api/sessions")
	public ResponseEntity<String> initializeSession(@RequestBody(required = false) Map<String, Object> params)
		throws OpenViduJavaClientException, OpenViduHttpException {
		SessionProperties properties = SessionProperties.fromJson(params).build();
		System.out.println(properties.toString());
		Session session = openvidu.createSession(properties);
		return new ResponseEntity<>(session.getSessionId(), HttpStatus.OK);
	}

	// 모든 방 정보를 가져오는 API
	@GetMapping("/api/rooms")
	public ResponseEntity<List<RoomDTO>> getAllRooms() {
		List<Room> rooms = rs.getAllRooms();
		List<RoomDTO> roomDTOs = rooms.stream().map(RoomDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok(roomDTOs);
	}

	@PostMapping("/api/room")
	public ResponseEntity<String> connectionByRoomId(@RequestParam("roomId") Long roomId) throws
		OpenViduJavaClientException,
		OpenViduHttpException {
		// 클라이언트가 보낸 roomId를 사용하여 세션 ID 조회
		openvidu.fetch();
		String sessionId = rs.findByRoomId(roomId).getSessionId();
		Session session = openvidu.getActiveSession(sessionId);

		if (session == null)
			return null;

		Map<String, Object> params = new HashMap<>();
		params.put("sessionId", sessionId);

		ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
		Connection connection = session.createConnection(properties);

		// connection.getToken()에서 token 값 추출
		String token = extractTokenFromUrl(connection.getToken());
		System.out.println(token);

		return ResponseEntity.ok(token);
	}

	// 정규표현식을 사용하여 token 값을 추출하는 메서드
	private String extractTokenFromUrl(String url) {
		String regex = "token=([^&]+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return null;
		}
	}

}
