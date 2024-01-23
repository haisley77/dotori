package com.dotori.backend.entity;

import lombok.Getter;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "room")
public class RoomEntity {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity bookEntity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomMemberEntity> roomMembers = new ArrayList<>();;

    @Column(name = "host_id")
    private BigInteger hostId;

    @Column(name = "title")
    private String title;

    @Column(name = "password")
    private String password;

    @Column(name = "is_recording")
    private boolean isRecording;

    @Column(name = "join_cnt")
    private int joinCnt;

    @Column(name = "limit_cnt")
    private int limitCnt;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "session_id")
    private String sessionId;

    public RoomEntity() {
        // 기본 생성자
    }
}
