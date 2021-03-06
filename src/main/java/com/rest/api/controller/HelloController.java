package com.rest.api.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/*
@ResponseBody : 자바 객체를 HTTP 응답 본문의 객체로 변환
@ResponseBody를 지정하지 않으면 return에 지정된 "helloworld"이름으로 된 파일을 프로젝트 경로에서 찾아 화면에 출력한다.
 */
@Controller
public class HelloController {
    /*
    1. 화면에 helloworld가 출력됩니다.
    */
    @GetMapping(value = "/helloworld/string")
    @ResponseBody
    public String helloworldString() {
        return "helloworld";
    }

    /*
    2. 화면에 {message:"helloworld"} 라고 출력됩니다.
    이거 왜 json으로 전달되지? 객체 리턴하면 디폴트가 json임?
    -> 부트는 JACKSON이 default serializer여서 그런듯.
    */
    @GetMapping(value = "/helloworld/json")
    @ResponseBody
    public Hello helloworldJson() {
        Hello hello = new Hello();
        hello.message = "helloworld";
        return hello;
    }

    /*
    3. 화면에 helloworld.ftl의 내용이 출력됩니다.
    */
    @GetMapping(value = "/helloworld/page")
    public String helloworld() {
        return "helloworld";
    }

    @Setter
    @Getter
    public static class Hello {
        private String message;
    }
}