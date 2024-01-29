package com.dotori.backend.domain.room.repository;

import java.util.List;
import java.util.Optional;

import com.dotori.backend.domain.room.model.entity.Room;

public interface RoomRepository {

	Long saveRoomInfo(Room room);

	void removeRoom(Long roomId) throws Exception;

	void removeRoomMember(Long roomId) throws Exception;

	String findSessionByRoomId(Long roomId) throws Exception;

	List<Room> findAll();

	Optional<Room> findByRoomId(Long roomId);
}
