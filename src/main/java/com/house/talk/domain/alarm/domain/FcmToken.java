package com.house.talk.domain.alarm.domain;

import com.house.talk.domain.house.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "fcm_tokens")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FcmToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User userId;

    @Column(name = "token", columnDefinition = "TEXT")
    private String token;
}
