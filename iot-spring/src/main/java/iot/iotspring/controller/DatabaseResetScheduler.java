/*package iot.iotspring.controller;

import iot.iotspring.repository.MemberRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DatabaseResetScheduler {

    private final MemberRepository memberRepository;

    public DatabaseResetScheduler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 매일 자정에 데이터베이스를 초기화하는 메서드
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetDatabase() {
        memberRepository.deleteAll(); // 모든 데이터 삭제
        // 초기 데이터 추가하는 코드도 추가 가능
    }
}
*/