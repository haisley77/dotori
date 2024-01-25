package com.dotori.backend.domain.room.controller;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dotori.backend.domain.room.model.dto.RoomInitializationDto;
import com.dotori.backend.domain.room.service.RoomService;

import io.openvidu.java.client.Connection;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.Session;

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

	private RoomService roomService;

	@PostConstruct
	public void init() {
		this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
	}

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	/**
	 * @param params The Session properties
	 * @return The Session ID
	 */
	@PostMapping("/api/sessions")
	public ResponseEntity<String> initializeSession(@RequestBody(required = true) RoomInitializationDto params) {
		Session session = null;
		try {
			// 세션을 만듭니다.
			session = roomService.createSession(openvidu, params.getSessionProperties());
			if (session != null) {
				// 방 정보를 DB에 등록합니다.
				Long roomId = roomService.saveRoomInfo(params.getRoomInfo(), session.getSessionId());
				// room id를 반환합니다.
				return new ResponseEntity<>(roomId.toString(), HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param roomId The Session in which to create the Connection
	 * @param params    The Connection properties
	 * @return The Token associated to the Connection
	 */
	@PostMapping("/api/sessions/connections/{roomId}")
	public ResponseEntity<String> createConnectionByRoomManager(@PathVariable("roomId") String roomId,
		@RequestBody(required = false) Map<String, Object> params) {
		Connection connection = null;
		try {
			// 방 Id에 해당하는 방과 커넥션을 생성합니다.
			connection = roomService.createConnectionByRoomManager(openvidu, Long.parseLong(roomId), params);
			// connection이 정상적으로 생성되었다면 connection의 token을 반환합니다.
			if (connection != null) {
				return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
