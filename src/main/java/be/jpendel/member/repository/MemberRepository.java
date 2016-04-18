package be.jpendel.member.repository;

import be.jpendel.member.member.Member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);

    Optional<Member> findMemberByEmail(String email);

}
