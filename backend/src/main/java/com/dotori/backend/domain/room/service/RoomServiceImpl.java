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

	@Override
	public Map<String, String> createRoom(OpenVidu openvidu, RoomInitializationDto params) throws Exception {
		Session session = openvidu.createSession(
			SessionProperties.fromJson(params.getSessionProperties()).build());

		if (session == null)
			throw new RuntimeException("세션 생성 중 문제 발생");

		List<RoomMember> roomMembers = new ArrayList<>();

		Book book = Book.builder()
			.title("Example Title")
			.bookImg("exampleProfileImgUrl1")
			.author("Example Author")
			.build();
		em.persist(book);

		Member host = Member.builder()
			.nickname("exampleNickname1")
			.profileImg("exampleProfileImgUrl1")
			.build();
		em.persist(host);

		Room room = Room.builder()
			.book(book)
			.hostId(1L)
			.roomMembers(roomMembers)
			.title("토끼와 거북이 같이 연극해요!")
			.password("1234")
			.limitCnt(4)
			.isPublic(false)
			.sessionId(session.getSessionId())
			.build();
		em.persist(room);
		em.flush();

		// Map<String, Object> roomInfo = objectMapper.readValue((JsonParser)params.getRoomInfo().get("roomInfo"),
		// 	Map.class);

		// Book book = bookRepository.findById(bookId);
		// Member host = memberRepository.findById(roomInfo.get("hostId"));

		// Room room = Room.builder()
		// 	.book(book)
		// 	.hostId(host.getMemberId())
		// 	.roomMembers(roomMembers)
		// 	.title((String)roomInfo.get("title"))
		// 	.password((String)roomInfo.get("password"))
		// 	.isPublic((Boolean)roomInfo.get("isPublic"))
		// 	.sessionId(session.getSessionId())
		// 	.build();

		Connection connection = session.createConnection(
			ConnectionProperties.fromJson(params.getConnectionProperties()).build());
		if (connection == null)
			throw new RuntimeException("토큰 생성 중 문제 발생");

		Map<String, String> resultData = new HashMap<>();
		resultData.put("roomId", String.valueOf(roomRepository.save(room).getRoomId()));
		resultData.put("token", connection.getToken());

		return resultData;
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