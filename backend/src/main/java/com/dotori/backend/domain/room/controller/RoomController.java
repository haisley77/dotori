package com.dotori.backend.domain.room.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dotori.backend.domain.room.model.dto.RoomDTO;
import com.dotori.backend.domain.room.model.dto.RoomInitializationDto;
import com.dotori.backend.domain.room.model.entity.Room;
import com.dotori.backend.domain.room.service.RoomService;

import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.Session;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rooms")
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

	@PostMapping("/session")
	public ResponseEntity<Map<String, String>> createRoom(
		@RequestBody(required = true) RoomInitializationDto params) {
		Map<String, String> resultData = new HashMap<>();
		try {
			openvidu.fetch();
			resultData = roomService.createRoom(openvidu, params);
			return new ResponseEntity<>(resultData, HttpStatus.CREATED);
		} catch (Exception e) {
			resultData.put("message", "방 생성 중 문제가 발생했습니다");
			return new ResponseEntity<>(resultData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/connection/{roomId}")
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
	@GetMapping("")
	public ResponseEntity<List<RoomDTO>> getAllRooms() {
		List<Room> rooms = roomService.getAllRooms();
		List<RoomDTO> roomDTOs = rooms.stream().map(RoomDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok(roomDTOs);
	}

	@PostMapping("/room")
	public ResponseEntity<String> connectionByRoomId(@RequestParam("roomId") Long roomId) throws
		OpenViduJavaClientException,
		OpenViduHttpException {
		// 클라이언트가 보낸 roomId를 사용하여 세션 ID 조회
		openvidu.fetch();
		String sessionId = roomService.findByRoomId(roomId).getSessionId();
		Session session = openvidu.getActiveSession(sessionId);

		if (session == null)
			return null;

		Map<String, Object> params = new HashMap<>();
		params.put("sessionId", sessionId);

		ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
		Connection connection = session.createConnection(properties);

		return ResponseEntity.ok(connection.getToken());
	}

}
