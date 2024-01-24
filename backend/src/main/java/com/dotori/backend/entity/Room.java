package com.dotori.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "room")
public class Room extends BaseTimeEntity{
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomMember> roommembers = new ArrayList<>();;

    @Column(name = "host_id")
    private Long hostId;

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

    @Column(name = "session_id")
    private String sessionId;
}
