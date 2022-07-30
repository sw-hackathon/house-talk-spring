package com.house.talk.domain.alarm.dao;

import com.house.talk.domain.alarm.domain.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {
}
