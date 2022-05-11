package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
