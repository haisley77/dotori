package com.dotori.backend.domain.room.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dotori.backend.domain.room.model.entity.Room;
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
