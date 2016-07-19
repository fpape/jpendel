package be.jpendel.application;

import be.jpendel.domain.member.Member;
import be.jpendel.domain.member.MemberRepository;

public class SignupUC {

    private MemberRepository memberRepository;

    public SignupUC(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //TODO brrrr 'new Member' ipv een builder
    // Volgens mijn begrip mag de applicatie laag niet een Member rechtreeks aanmaken ..waar is CreateMemberCommand ?
    public void signup(Request request) {
        memberRepository.save(new Member(request.getName(), request.getEmail()));
    }

    // TODO fix github immutables + gradle
    public interface Request {
        String getName();

        String getEmail();
    }

}
