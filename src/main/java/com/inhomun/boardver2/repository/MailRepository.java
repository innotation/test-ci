package com.inhomun.boardver2.repository;

import com.inhomun.boardver2.dto.MailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MailRepository {

    private final RedisTemplate<String, MailDTO> redisTemplate;

    public MailDTO save(MailDTO mailDTO) {
        String key = "authCode:" + mailDTO.getEmail(); // 키 생성
        redisTemplate.opsForValue().set(key, mailDTO); // Redis에 저장
        return mailDTO; // 저장된 객체 반환
    }

    public Optional<MailDTO> findByEmail(String email) {
        String key = "authCode:" + email; // 키 생성
        MailDTO mailDTO = redisTemplate.opsForValue().get(key); // Redis에서 조회
        return Optional.ofNullable(mailDTO); // 결과를 Optional로 반환
    }
}
