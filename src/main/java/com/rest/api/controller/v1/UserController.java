package com.rest.api.controller.v1;

import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.entity.User;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.repo.UserJpaRepo;
import com.rest.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 리소스의 사용 목적에 따라 GetMapping, PostMapping, PutMapping, DeleteMapping 을 사용.
 * 결과 데이터의 형태에 따라 단일건 처리는 getSingleResult()를,  다중건 처리는 getListResult()를,
 * api 처리 성공 결과만 필요한 경우 getSuccessResult()를 사용.
 */
// @RequiredArgsConstructor 사용하지 않고 선언된 객체에 @Autowired 해도 됨.
@Api(tags = {"1. User"}) // UserController를 대표하는 최상단 타이틀 영역에 표시될 값을 세팅한다.
@RequiredArgsConstructor // 클래스 상단에 선언하면 클래스 내부에서 final로 선언된 객체에 대해 생성자 주입.
@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = "/v1") // api resource를 버전별로 관리하기 위해 /v1을 모든 리소스 주소에 적용되도록 처리.
public class UserController {
    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService; // 결과를 처리할 Service

    /**
     * user테이블에 있는 데이터를 모두 읽어온다.
     * 데이터가 한 개 이상일 수 있으므로 리턴 타입은 List<Uesr>로 선언한다.
     */
    // 각각의 resource에 제목과 설명을 표시하기 위해 세팅한다.
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUser() {
        /*
        JPA를 사용하면 기본으로 CRUD에 대해서는 별다른 설정없이 쿼리를 질의할 수 있도록 지원한다. return userJpaRepo.findAll();
        findAll()은 select msrl, name, uid from user; 쿼리를 내부적으로 실행시켜준다.
        결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        */
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다")
    @GetMapping(value = "/user/{msrl}")
    public SingleResult<User> findUserById(@ApiParam(value = "회원ID", required = true) @PathVariable long msrl,
                                           @ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepo.findById(msrl).orElseThrow(CUserNotFoundException::new));
    }

    /**
     * user테이블에 데이터를 1건 입력. => insert문 실행됨.
     */
    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다")
    @PostMapping(value = "/user") // @ApiParam, @RequestParam: 파라미터에 대한 설명을 보여주기 위한 세팅
    public SingleResult<User> save(@ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
                                   @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .uid(uid)
                .name(name)
                .build();
        // return userJpaRepo.save(user);
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam long msrl,
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .msrl(msrl)
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable long msrl) {
        userJpaRepo.deleteById(msrl);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}