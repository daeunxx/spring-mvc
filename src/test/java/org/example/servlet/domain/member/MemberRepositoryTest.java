package org.example.servlet.domain.member;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

  MemberRepository memberRepository = MemberRepository.getInstance();

  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void save() {
    // given
    Member member = new Member("daeun", 20);

    // when
    Member saveMember = memberRepository.save(member);

    // then
    Member findMember = memberRepository.findById(member.getId());

    Assertions.assertThat(saveMember).isEqualTo(findMember);
  }

  @Test
  void findAll() {
    // given
    Member member1 = new Member("daeun1", 20);
    Member member2 = new Member("daeun2", 30);

    memberRepository.save(member1);
    memberRepository.save(member2);

    // when

    List<Member> members = memberRepository.findAll();

    // then
    Assertions.assertThat(members.size()).isEqualTo(2);
    Assertions.assertThat(members).contains(member1, member2);
  }

}