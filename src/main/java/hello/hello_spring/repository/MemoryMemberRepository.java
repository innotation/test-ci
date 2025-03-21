package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository{

    private  static Map<Long, Member> store = new HashMap<>(); // 실무에서 공유 변수의 경우 동시성 문제가 있을 수 있어 ConcurrentHashMap을 사용함.
    private static  long sequence = 0L; // 0, 1, 2 등 키 값을 생성해주는 녀석. 실무에서는 그냥 Long type 보단 동시성을 고려한 Atomic Long 등등 필요.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 과거에는 그냥 반환했으나, 요즘엔 optional 형태로 반환하는 추세.
        // 그래야 client-side에서 대응 가능.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store는 map, but 반환은 List -> java 실무 시
        // loop의 용이성 때문에 List를 많이 씀.

    }

    public void clearStore() {
        store.clear();
    }
}
