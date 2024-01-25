package com.dotori.backend.domain.room.service;

import org.springframework.stereotype.Service;

import com.dotori.backend.domain.room.repository.RoomRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomServiceImpl {

	private RoomRepository roomRepository;

}
