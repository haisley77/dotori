package com.dotori.backend.domain.room.service;

import java.util.Map;

import io.openvidu.java.client.Connection;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.Session;

public interface RoomService {

	Session createSession(OpenVidu openvidu, Map<String, Object> sessionProperties) throws Exception;

	Long saveRoomInfo(Map<String, Object> roomInfo, String sessionId) throws Exception;

	Session findBySessionId(String sessionId) throws Exception;

	Connection createConnection(Map<String, Object> connectionProperties) throws Exception;

}
