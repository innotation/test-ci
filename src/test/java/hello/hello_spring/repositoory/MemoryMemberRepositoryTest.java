package hello.hello_spring.repositoory;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("helloworld");

        Member member2 = new Member();
        member2.setName("helloworld");
        repository.save(member1);
        repository.save(member2);

        Member result = repository.findByName("helloworld").get();
        Assertions.assertThat(member1).isEqualTo(result);

    }
    @Test
    public  void findAll() {
        Member member1 = new Member();
        member1.setName("helloworld");

        Member member2 = new Member();
        member2.setName("helloworld");
        repository.save(member1);

        List<Member> result1 = repository.findAll();
        repository.save(member2);
        List<Member> result2 = repository.findAll();
        Assertions.assertThat(result2.size()).isEqualTo(2);
        Assertions.assertThat(result1).isNotEqualTo(result2);
    }
}
