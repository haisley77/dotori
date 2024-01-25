package com.dotori.backend.domain.room.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.dotori.backend.domain.room.repository.RoomRepository;

import io.openvidu.java.client.Connection;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

	private RoomRepository roomRepository;

	@Override
	public Session createSession(OpenVidu openvidu, Map<String, Object> sessionProperties) throws
		Exception {
		SessionProperties properties = SessionProperties.fromJson(sessionProperties).build();
		// System.out.println(properties.toString());
		return openvidu.createSession(properties);
	}

	@Override
	public String saveRoomInfo(Map<String, Object> roomInfo, String sessionId) {
		return null;
	}

	@Override
	public Session findBySessionId(String sessionId) {
		return null;
	}

	@Override
	public Connection createConnection(Map<String, Object> connectionProperties) {
		return null;
	}
}