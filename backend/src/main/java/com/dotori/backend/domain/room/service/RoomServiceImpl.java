package com.dotori.backend.domain.room.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotori.backend.domain.book.model.entity.Book;
import com.dotori.backend.domain.member.model.entity.Member;
import com.dotori.backend.domain.room.model.dto.RoomInitializationDto;
import com.dotori.backend.domain.room.model.entity.Room;
import com.dotori.backend.domain.room.model.entity.RoomMember;
import com.dotori.backend.domain.room.repository.RoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.openvidu.java.client.Connection;
import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

	private final RoomRepository roomRepository;
	// private final BookRepository bookRepository;

	@Autowired
	private EntityManager em;

	private ObjectMapper objectMapper;

	@Autowired
	public RoomServiceImpl(RoomRepository roomRepository
		//, BookRepository bookRepository
	) {
		this.roomRepository = roomRepository;
		// this.bookRepository = bookRepository;
		this.objectMapper = new ObjectMapper();
	}

	/**
	 * 방 정보와 세션 아이디를 저장하는 메서드
	 * @param roomInfo 방 정보
	 * @param sessionId 세션 아이디
	 * @return DB에 저장한 방 pk
	 */
	@Override
	public Long saveRoomInfo(Map<String, Object> roomInfo, String sessionId) {
		List<RoomMember> roomMembers = new ArrayList<>();

		// 책 정보 더미데이터
		Book book = Book.builder()
			.title("토끼와 거북이")
			.bookImg("토끼와 거북이 책 이미지 주소")
			.author("이하은")
			.build();
		// jpa entity cascade 오류로 추가한 코드입니다. 테스트가 필요할 시 주석을 풀고 테스트 하시면 됩니다.
		// roomRepository.saveBook(book);

		// 방 정보 더미데이터
		Room room = Room.builder()
			.book(book)
			.hostId(1L)
			.roomMembers(roomMembers)
			.title("토끼와 거북이 같이 연극해요!")
			.password("1234")
			.isPublic(false)
			.sessionId(sessionId)
			.build();

		// DB에 저장하고 방 pk를 반환합니다.
		return roomRepository.saveRoomInfo(room);
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
		return session.createConnection(properties);
	}

	@Override
	public void destroyRoom(Long roomId) throws Exception {
		roomRepository.removeRoom(roomId);
		roomRepository.removeRoomMember(roomId);
	}

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