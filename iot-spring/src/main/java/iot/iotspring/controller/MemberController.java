package iot.iotspring.controller;

import iot.iotspring.domain.Member;
import iot.iotspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    private List<Member> member;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        this.member = memberService.findMembers();
    }

    @GetMapping("/messages")
    public String createForm(Model model) {
        List<Member> updatedMembers = memberService.findMembers(); // 새로운 멤버 목록 가져오기
        model.addAttribute("members", updatedMembers); // 모델에 새로운 멤버 목록 추가
        return "messages"; // 확장자 제거
    }


    @PostMapping("/messages")
    public String create(MemberForm form, RedirectAttributes attributes) {
        Member member = new Member();
        member.setName(form.getName());

        Long memberId = memberService.join(member); // 새로 생성된 멤버의 ID를 가져옴

        // 실제 경로를 사용하여 리다이렉트
        return "redirect:/messages/" + memberId;
    }

    @GetMapping("/messages/{memberId}")
    public String list(@PathVariable Long memberId, Model model) {
        // 새로운 멤버의 ID를 사용하여 해당 멤버의 정보를 조회하고 모델에 추가
        Member member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);

        // 기존의 멤버 목록도 모델에 추가
        model.addAttribute("members", memberService.findMembers());

        System.out.println("Listing all member: " + member); // 등록된 모든 멤버 확인용 로그

        return "result"; // 경로 수정
    }

    @GetMapping("/messages/get")
    public String createFromURL(@RequestParam(required = false) String name, RedirectAttributes attributes) {
        if (name != null) {
            Member member = new Member();
            member.setName(name);
            Long memberId = memberService.join(member);
            return "redirect:/messages/" + memberId;
        } else {
            // 파라미터가 없는 경우에 대한 처리
            return "error"; // 예시로 에러 페이지로 리다이렉트하거나 에러 메시지를 반환할 수 있습니다.
        }
    }


}
