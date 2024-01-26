package com.dotori.backend.domain.room.controller;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dotori.backend.domain.room.model.dto.RoomInitializationDto;
import com.dotori.backend.domain.room.service.RoomService;

import io.openvidu.java.client.Connection;
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

	@PostMapping("/sessions")
	public ResponseEntity<String> initializeSession(@RequestBody(required = true) RoomInitializationDto params) {

		Session session = null;
		String errorMessage = null;
		try {
			openvidu.fetch();
			// 세션을 만듭니다.
			session = roomService.createSession(openvidu, params.getSessionProperties());
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

	@PostMapping("/connections/{roomId}")
	public ResponseEntity<String> createConnectionByHost(@PathVariable("roomId") String roomId,
		@RequestBody(required = false) Map<String, Object> params) {
		Connection connection = null;
		try {
			openvidu.fetch();
			// 방 Id에 해당하는 방과 커넥션을 생성합니다.
			connection = roomService.createConnectionByHost(openvidu, Long.parseLong(roomId), params);
			// token을 반환합니다.
			if (connection != null) {
				return new ResponseEntity<>(connection.getToken(), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return new ResponseEntity<>("커넥션 생성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
