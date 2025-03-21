package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("helloWorld");
        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void duplicateMember1() {
        Member member = new Member();
        member.setName("helloWorld");
        memberService.join(member);
        assertThatThrownBy(() -> memberService.join(member)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void duplicateMember2() {
        Member member = new Member();
        member.setName("helloWorld");
        memberService.join(member);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member));
        assertThat(e.getMessage()).isEqualTo("Already Exists");
    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }
}