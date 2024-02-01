package com.dotori.backend.domain.room.controller;

import java.util.List;
import java.util.Map;
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
@RestController("/")
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

	/**
	 * @param sessionId The Session in which to create the Connection
	 * @param params    The Connection properties
	 * @return The Token associated to the Connection
	 */
	// @PostMapping("/api/sessions/{sessionId}/connections")
	// public ResponseEntity<String> createConnection(@PathVariable("sessionId") String sessionId,
	// 	@RequestBody(required = false) Map<String, Object> params)
	// 	throws OpenViduJavaClientException, OpenViduHttpException {
	// 	Session session = openvidu.getActiveSession(sessionId);
	// 	if (session == null) {
	// 		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// 	}
	// 	ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
	// 	Connection connection = session.createConnection(properties);
	// 	// System.out.println(connection.getConnectionId());
	// 	return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);
	// }

	// 방 참여 유저
	@GetMapping("/api/sessions/{sessionId}/connections")
	public ResponseEntity<String> creatConnectionByMember(@PathVariable("sessionId") String sessionId,
		// 이부분(@pathvariable부터) 우리는 디비에서 가져온다.
		@RequestBody(required = false) Map<String, Object> params)
		throws OpenViduJavaClientException, OpenViduHttpException {
		Session session = openvidu.getActiveSession(sessionId);
		if (session == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
		Connection connection = session.createConnection(properties);
		// System.out.println(connection.getConnectionId());
		return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);
	}

	// 모든 방 정보를 가져오는 API 엔드포인트
	@GetMapping("/api/rooms")
	public ResponseEntity<List<RoomDTO>> getAllRooms() {
		List<Room> rooms = rs.getAllRooms();
		List<RoomDTO> roomDTOs = rooms.stream().map(RoomDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok(roomDTOs);
	}

	@PostMapping("/api/room")
	public ResponseEntity<String> connectionByRoomId(@RequestParam("roomId") Long roomId) {

		// 클라이언트가 보낸 roomId를 사용하여 세션 ID 조회
		String sessionId = String.valueOf(rs.findByRoomId(roomId).getSessionId());
		if (sessionId != null) {
			// 세션 ID가 존재하면 성공 응답과 세션 ID 반환
			System.out.println(sessionId);
			return ResponseEntity.ok(sessionId);
		} else {
			// 세션 ID가 존재하지 않으면 실패 응답
			return ResponseEntity.status(404).body("Room not found");
		}
	}
}
