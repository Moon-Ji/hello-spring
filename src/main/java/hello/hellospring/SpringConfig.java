package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /**
     * spring 이 뜰 때 Configuration 을 읽고 spring bean 에 component 를
     * 아래 로직으로 등록하게 됨
     */
    private DataSource dataSource;  // 설정 파일을 주고 spring boot가 자동으로 bean 생성

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {

//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);    // 해당 코드만 수정하면 db를 바꿀 수 있다.
    }
}
