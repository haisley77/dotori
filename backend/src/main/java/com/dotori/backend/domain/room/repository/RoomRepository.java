package com.dotori.backend.domain.room.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dotori.backend.domain.room.model.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	// 모든 방 정보를 불러오는 메서드.
	List<Room> findAll();

	Optional<Room> findByRoomId(Long roomId);
}
