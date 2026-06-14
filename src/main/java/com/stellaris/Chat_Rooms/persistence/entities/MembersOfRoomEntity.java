package com.stellaris.Chat_Rooms.persistence.entities;

import com.stellaris.Chat_Rooms.domain.enums.TypeOfMember;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members_of_room_tb", uniqueConstraints = {
        @UniqueConstraint(
                name = "user_room",
                columnNames = {"user_id", "room_id"}
        )
})
public class MembersOfRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "members_of_room_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    @Column(name = "type_of_member")
    @Enumerated(EnumType.STRING)
    private TypeOfMember typeOfMember = TypeOfMember.PARTICIPANT;

    @Builder.Default
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public static MembersOfRoomEntity buildMemberParticipant(UserEntity currentUser, RoomEntity room) {
        return buildMember(currentUser, room, TypeOfMember.PARTICIPANT);
    }

    public static MembersOfRoomEntity buildMemberOwner(UserEntity currentUser, RoomEntity room) {
        return buildMember(currentUser, room, TypeOfMember.OWNER);
    }

    private static MembersOfRoomEntity buildMember(UserEntity currentUser, RoomEntity room, TypeOfMember typeOfMember) {
        return MembersOfRoomEntity.builder()
                .typeOfMember(typeOfMember)
                .user(currentUser)
                .room(room)
                .build();
    }
}
