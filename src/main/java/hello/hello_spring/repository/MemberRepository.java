package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원이 저장소에 저장됨.
    Optional<Member> findById(Long id); // id 기반으로 회원 탐색.
    Optional<Member> findByName(String name); // name 기반으로 회원 탐색.
    List<Member> findAll(); // 모든 회원을 List로 반환.
}



