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
import com.dotori.backend.domain.room.repository.RoomMemberRepository;
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
	private final RoomMemberRepository roomMemberRepository;
	// private final BookRepository bookRepository;

	@Autowired
	private EntityManager em;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public RoomServiceImpl(RoomRepository roomRepository,
		RoomMemberRepository roomMemberRepository
		//, BookRepository bookRepository
	) {
		this.roomRepository = roomRepository;
		this.roomMemberRepository = roomMemberRepository;
		// this.bookRepository = bookRepository;
	}

	@Override
	public Map<String, String> createRoom(OpenVidu openvidu, RoomInitializationDto params) throws Exception {
		// 세션을 생성합니다.
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
			.limitCnt(2)
			.joinCnt(0)
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

		// 세션과 커넥션을 생성합니다.
		Connection connection = session.createConnection(
			ConnectionProperties.fromJson(params.getConnectionProperties()).build());
		if (connection == null)
			throw new RuntimeException("토큰 생성 중 문제 발생");

		// 방 id와 token 데이터를 반환합니다.
		Map<String, String> resultData = new HashMap<>();
		resultData.put("roomId", String.valueOf(roomRepository.save(room).getRoomId()));
		resultData.put("token", connection.getToken());

		return resultData;
	}

	@Override
	public Session findSessionByRoomId(OpenVidu openvidu, Long roomId) {
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		if (optionalRoom.isPresent()) {
			return openvidu.getActiveSession(optionalRoom.get().getSessionId());
		}
		throw new RuntimeException("방 조회 불가");
	}

	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	@Override
	public String createConnection(OpenVidu openvidu, Session session,
		Map<String, Object> connectionProperties) throws OpenViduJavaClientException, OpenViduHttpException {
		// 방 참여자는 세션과 커넥션을 생성합니다.
		Connection connection = session.createConnection(
			ConnectionProperties.fromJson(connectionProperties).build());
		if (connection == null)
			throw new RuntimeException("토큰 생성 중 문제 발생");
		// 토큰을 반환합니다.
		return connection.getToken();
	}

	@Override
	public boolean checkJoinPossible(OpenVidu openvidu, Long roomId) {
		// 방 id에 해당하는 방 엔티티를 가져옵니다.
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		if (optionalRoom.isEmpty()) {
			throw new RuntimeException("방 조회 불가");
		}
		Room room = optionalRoom.get();

		// // 방에 연결된 유효한 connection 리스트를 openvidu 서버에서 불러옵니다.

		// List<Connection> activeConnections = openvidu.getActiveSession(room.getSessionId()).getActiveConnections();
		// return activeConnections.size() < room.getLimitCnt();

		// db와 openvidu 서버 둘 다 확인하는 게 맞지만, 일단은 해피케이스 생각하겠습니다.
		return room.getJoinCnt() < room.getLimitCnt();
	}

	@Override
	public void addMemberToRoom(Long roomId, Long memberId) {
		// 방 id에 해당하는 방을 가져옵니다.
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		if (optionalRoom.isEmpty()) {
			throw new RuntimeException("방 조회 불가");
		}
		Room room = optionalRoom.get();

		// Member member = memberRepository.findById(memberId);
		Member member = Member.builder()
			//.nickname("도토리유저2")
			.nickname(String.valueOf(memberId))
			.profileImg("프로필이미지")
			.build();

		em.persist(member);
		em.flush();

		// member id에 해당하는 멤버를 방 참여 멤버로 등록합니다.
		RoomMember roomMember = RoomMember.builder()
			.member(member)
			.room(room)
			.build();
		roomMemberRepository.save(roomMember);

		// 방 참여 인원을 갱신합니다.
		room.setJoinCnt(room.getJoinCnt() + 1);
		roomRepository.save(room);
	}

	@Override
	public void removeMemberFromRoom(OpenVidu openvidu, Long roomId, Long memberId) {
		// 방 참여 멤버를 DB에서 지웁니다.
		Optional<RoomMember> optionalRoomMember = roomMemberRepository.findByRoomRoomIdAndMemberMemberId(roomId,
			memberId);
		if (optionalRoomMember.isEmpty()) {
			throw new RuntimeException("유저 조회 불가");
		}
		RoomMember roomMember = optionalRoomMember.get();
		roomMemberRepository.delete(roomMember);

		// 방 id 에 해당하는 방을 가져옵니다.
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		if (optionalRoom.isEmpty()) {
			throw new RuntimeException("방 조회 불가");
		}
		Room room = optionalRoom.get();
		// 방 참여 인원을 갱신합니다.
		room.setJoinCnt(room.getJoinCnt() - 1);

		// 방에 더이상 남아있는 인원이 없다면 방을 삭제합니다.
		if (room.getJoinCnt() == 0) {
			roomRepository.delete(room);
		}
	}

}