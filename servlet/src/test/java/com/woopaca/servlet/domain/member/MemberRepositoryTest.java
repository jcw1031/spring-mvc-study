package com.woopaca.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }
    
    @Test
    void save() throws Exception {
        //given
        Member member = new Member("woopaca", 24);

        //when
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId());

        //then
        assertThat(findMember).isEqualTo(member);
    }
    
    @Test
    void findAll() throws Exception {
        //given
        Member member1 = new Member("member1", 24);
        Member member2 = new Member("member2", 26);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}