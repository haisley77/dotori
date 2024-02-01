package com.dotori.backend.domain.room.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dotori.backend.domain.room.model.entity.Room;

@Transactional
public class RoomRepository extends JpaRepository<Room, Long> {

	@PersistenceContext
	private EntityManager em;

	// 방 생성 테스트 용 메서드입니다. BookController 구현 이전까지는 주석을 풀고 테스트 하시면 됩니다.
	// public void saveBook(Book book) {
	// 	em.persist(book);
	// }

	public Long saveRoomInfo(Room room) {
		em.persist(room);
		return room.getRoomId();
	}

	List<Room> findAll();

	Optional<Room> findByRoomId(Long roomId);

}

	public String findSessionByRoomId(Long roomId) throws Exception {
		Room room = em.find(Room.class, roomId);
		if (room != null) {
			return room.getSessionId();
		} else {
			throw new Exception();
		}

	}

	public void removeRoom(Long roomId) throws Exception {
		Room room = em.find(Room.class, roomId);
		if (room != null) {
			em.remove(room);
		} else {
			throw new Exception();
		}

	}

	public void removeRoomMember(Long roomId) throws Exception {
		// 방에 속한 모든 멤버 삭제 (JPQL)
		em.createQuery("DELETE FROM RoomMember m WHERE m.room.roomId = :roomId")
			.setParameter("roomId", roomId)
			.executeUpdate();
	}