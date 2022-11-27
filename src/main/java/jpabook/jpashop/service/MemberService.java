package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * Join long.
     *
     * @param member the member
     * @return the long
     */
    public Long join(Member member) {
        validateDuplicateMember(member);//중복회원 검증 로직
        memberRepository.save(member);  //회원 가입
        return member.getId();
    }

    /**회원 가입 시 중복된 이름이 있는지 검증 있을 경우 예외 발생
     * Validate duplicate member.
     *
     * @param member the member
     */
    private void validateDuplicateMember(Member member) {
        //문제 발생시 예외 처리 exception
        List<Member> findList = memberRepository.findByName(member.getUsername());
        if (!findList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    /**
     * 회원 전체 조회 리스트
     * Find member list.
     *
     * @return the list
     */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }


    /**
     * 회원 아이디로 한명 찾기
     * Find one member.
     *
     * @param memberId the member id
     * @return the member
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
