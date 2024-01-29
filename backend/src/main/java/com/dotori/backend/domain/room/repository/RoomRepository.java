package com.dotori.backend.domain.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dotori.backend.domain.room.model.entity.Room;
import com.dotori.backend.domain.room.model.entity.RoomMember;

public interface RoomRepository extends JpaRepository<Room, Long> {
	void save(RoomMember roomMember);

	@Query("delete from RoomMember where RoomMember.member.memberId = :memberId")
	void deleteByRoomMemberId(@Param("memberId") Long memberId);
}
