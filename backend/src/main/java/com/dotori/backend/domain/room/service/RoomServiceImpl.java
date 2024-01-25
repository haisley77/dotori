package com.dotori.backend.domain.room.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.room.model.entity.Room;
import com.dotori.backend.domain.room.model.entity.RoomMember;
import com.dotori.backend.domain.room.repository.RoomRepository;

import io.openvidu.java.client.Connection;
import io.openvidu.java.client.ConnectionProperties;
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
	public Long saveRoomInfo(Map<String, Object> roomInfo, String sessionId) {
		List<RoomMember> roomMembers = new ArrayList<>();

		// 책 정보 더미데이터
		Book book = Book.builder()
			.title("토끼와 거북이")
			.bookImg("토끼와거북이주소")
			.author("이하은")
			.build();

		// room 엔티티를 생성합니다.
		Room room = Room.builder()
			.book(book)
			.hostId((Long)roomInfo.get("hostId"))
			.roomMembers(roomMembers)
			.title("토끼와 거북이 같이 연극해요!")
			.password("1234")
			.isPublic(false)
			.sessionId(sessionId)
			.build();

		// DB에 저장하러 갑니다.
		return roomRepository.saveRoomInfo(room);
	}

	@Override
	public Session findSessionByRoomId(OpenVidu openvidu, Long roomId) {
		String sessionId = roomRepository.findSessionByRoomId(roomId);
		return openvidu.getActiveSession(sessionId);
	}

	@Override
	public Connection createConnectionByRoomManager(OpenVidu openvidu, Long roomId,
		Map<String, Object> connectionProperties) throws
		Exception {
		Session session = findSessionByRoomId(openvidu, roomId);
		if (session == null) {
			//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new Exception("방이 존재하지 않습니다.");
		}
		ConnectionProperties properties = ConnectionProperties.fromJson(connectionProperties).build();
		// System.out.println(properties.toString());
		return session.createConnection(properties);
		// System.out.println(connection.getConnectionId());
	}
}