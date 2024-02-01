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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dotori.backend.domain.room.model.dto.RoomDTO;
import com.dotori.backend.domain.room.model.dto.RoomInitializationDto;
import com.dotori.backend.domain.room.model.entity.Room;
import com.dotori.backend.domain.room.service.RoomService;

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

	private final RoomService roomService;

	@PostConstruct
	public void init() {
		this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
	}

	@Autowired
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
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

	@PostMapping("/sessions")
	public ResponseEntity<String> initializeSession(@RequestBody(required = true) RoomInitializationDto params) {

		try {
			openvidu.fetch();
			// 세션을 만듭니다.
			Session session = roomService.createSession(openvidu, params.getSessionProperties());
			if (session != null) {
				// 방 정보를 DB에 등록합니다.
				Long roomId = roomService.saveRoomInfo(params.getRoomInfo(), session.getSessionId());
				// room id를 반환합니다.
				return new ResponseEntity<>(roomId.toString(), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// e.printStackTrace();

		}
		return new ResponseEntity<>("세션 생성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> createConnectionByHost(@PathVariable("roomId") Long roomId,
		@RequestBody(required = false) Map<String, Object> params) {
		try {
			openvidu.fetch();
			// 방 Id에 해당하는 방과 커넥션을 생성합니다.
			Connection connection = roomService.createConnectionByHost(openvidu, roomId, params);
			// token을 반환합니다.
			if (connection != null) {
				return new ResponseEntity<>(connection.getToken(), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return new ResponseEntity<>("커넥션 생성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/remove/{roomId}")
	public ResponseEntity<String> removeRoomAndRoomMember(@PathVariable("roomId") Long roomId) {
		try {
			roomService.destroyRoom(roomId);
			return new ResponseEntity<>(String.valueOf(roomId), HttpStatus.OK);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return new ResponseEntity<>("방 제거 중 문제가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
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
