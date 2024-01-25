package com.dotori.backend.domain.room.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.dotori.backend.domain.room.model.entity.Room;

@Repository
@Transactional
public class RoomRepository {

	@PersistenceContext
	private EntityManager em;

	// 방 생성 테스트 용 메서드입니다. BookController 구현 이전까지는 주석을 풀고 테스트 하시면 됩니다.
	// public void saveBook(Book book) {
	// 	em.persist(book);
	// }

	public Long saveRoomInfo(Room room) {
		em.persist(room);
		em.flush();
		return room.getRoomId();
	}

	public String findSessionByRoomId(Long roomId) {
		Room room = em.find(Room.class, roomId);
		return room.getSessionId();
	}
}
