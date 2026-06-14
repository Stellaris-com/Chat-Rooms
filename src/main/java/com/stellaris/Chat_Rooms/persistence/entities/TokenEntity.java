package com.stellaris.Chat_Rooms.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token_tb")
public class TokenEntity {
    @Id
    @Column(name = "token_id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
