package com.dotori.backend.domain.room.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.dotori.backend.domain.room.model.entity.Room;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Repository
@NoArgsConstructor
@AllArgsConstructor
public class RoomRepository {

	private EntityManager em;

	public Long saveRoomInfo(Room room) {
		// System.out.println(room.getTitle());
		// em.persist(room);
		// em.flush();
		return room.getRoomId();
	}
}
