package be.jpendel.domain.member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);

    Optional<Member> findMemberByEmail(String email);

}
