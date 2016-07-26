package be.jpendel.application;

import be.jpendel.domain.member.Member;
import be.jpendel.domain.member.MemberRepository;

public class SignupUC {

    private MemberRepository memberRepository;

    public SignupUC(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void signup(Request request) {

        memberRepository.save(Member.withNameAndEmail(request.getName(), request.getEmail()));
    }

    // TODO fix github immutables + gradle
    public interface Request {
        String getName();

        String getEmail();
    }

}
