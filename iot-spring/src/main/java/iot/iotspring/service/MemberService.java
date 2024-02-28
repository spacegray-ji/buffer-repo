package iot.iotspring.service;

import iot.iotspring.domain.Member;
import iot.iotspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 등록 메서드
    public Long join(Member member) {


        memberRepository.save(member);

        // 회원을 데이터베이스에 저장
        Member savedMember = memberRepository.save(member);

        // 저장된 회원의 ID 반
        return savedMember.getId();
    }

    // 모든 회원 조회 메서드
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 ID로 회원 조회 메서드
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }
}
