package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    // MemberServiceTest 에서 MemberRepository 를 생성하면 별도의 repository 가
    // 생성되는 문제를 해결하기 위해 생성자에서 repository 를 주입하도록 변경
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /* 회원 가입 */
    public Long join(Member member) {
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());    // ctrl + alt + v -> 자동 return
        // Optional 로 감쌌기 때문에 여러 기능을 사용 가능, result 가 null 이 아니면 exception 던짐
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        // 이름이 중복되면 x
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 메서드 추출 단축키 ctrl + alt + m
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /* 전체 회원 조회 */
   public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
