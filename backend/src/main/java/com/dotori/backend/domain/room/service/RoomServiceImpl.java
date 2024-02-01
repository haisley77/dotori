package com.dotori.backend.domain.room.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.room.model.entity.Room;
import com.dotori.backend.domain.room.model.entity.RoomMember;
import com.dotori.backend.domain.room.repository.RoomRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomServiceImpl {

	private RoomRepository roomRepository;

	// 모든 방 정보를 가져오는 메서드
	public List<Room> getAllRooms() {
		List<Room> rooms = roomRepository.findAll();
		return rooms;
	}

	// 특정 roomId에 대한 방 정보를 가져오는 메서드
	public Room findByRoomId(Long roomId) {
		Optional<Room> optionalRoom = roomRepository.findByRoomId(roomId);
		return optionalRoom.orElse(null);
	}

}

	/**
	 * 방 pk로 해당하는 세션을 찾는 메서드
	 * @param openvidu openvidu 객체
	 * @param roomId 방 pk
	 * @return session 객체
	 */
	@Override
	public Session findSessionByRoomId(OpenVidu openvidu, Long roomId) throws Exception {
		String sessionId = roomRepository.findSessionByRoomId(roomId);
		return openvidu.getActiveSession(sessionId);
	}

	/**
	 * 방 pk에 해당하는 세션과 커넥션을 생성하는 메서드(방장)
	 * @param openvidu openvidu 객체
	 * @param roomId 방 pk
	 * @param connectionProperties connection 설정 정보를 담은 객체
	 * @return connection 객체
	 * @throws Exception connection 생성 시 발생하는 예외 객체
	 */
	@Override
	public Connection createConnectionByHost(OpenVidu openvidu, Long roomId,
		Map<String, Object> connectionProperties) throws Exception {
		Session session = findSessionByRoomId(openvidu, roomId);
		ConnectionProperties properties = ConnectionProperties.fromJson(connectionProperties).build();
		// System.out.println(properties.toString());
		return session.createConnection(properties);
	}
}