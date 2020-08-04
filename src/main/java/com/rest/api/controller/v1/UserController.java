package com.rest.api.controller.v1;

import com.rest.api.entity.User;
import com.rest.api.repo.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// @RequiredArgsConstructor 사용하지 않고 선언된 객체에 @Autowired 해도 됨.
@RequiredArgsConstructor // 클래스 상단에 선언하면 클래스 내부에서 final로 선언된 객체에 대해 생성자 주입.
@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = "/v1") // api resource를 버전별로 관리하기 위해 /v1을 모든 리소스 주소에 적용되도록 처리.
public class UserController {
    private final UserJpaRepo userJpaRepo;

    /**
     * user테이블에 있는 데이터를 모두 읽어온다.
     * 데이터가 한 개 이상일 수 있으므로 리턴 타입은 List<Uesr>로 선언한다.
     */
    @GetMapping(value = "/user")
    public List<User> findAllUser() {
        // JPA를 사용하면 기본으로 CRUD에 대해서는 별다른 설정없이 쿼리를 질의할 수 있도록 지원한다.
        // findAll()은 select msrl, name, uid from user; 쿼리를 내부적으로 실행시켜준다.
        return userJpaRepo.findAll();
    }

    /**
     * user테이블에 데이터를 1건 입력. => insert문 실행됨.
     */
    @PostMapping(value = "/user")
    public User save() {
        User user = User.builder()
                .uid("yumi@naver.com")
                .name("유미")
                .build();
        return userJpaRepo.save(user);
    }
}