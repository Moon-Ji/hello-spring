package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * spring 이 시작될 때 Controller 어노테이션을 보고 객체를 생성해 spring 이 들고 있게 된다.
 * spring container 에서 spring bean이 관리된다.
 */
@Controller
public class MemberController {

    private final MemberService memberService;

    /**
        memberService 를 new 로 생성하게 되면 다른 controller 에서 memberService 를
        이용할 경우 객체가 여러 개가 생성되게 된다. Autowired 를 붙여주면 생성자를 호출할 때
        spring container 에서 memberService 를 붙여줌
    */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
