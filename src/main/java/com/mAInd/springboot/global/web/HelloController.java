package com.mAInd.springboot.global.web;
import com.mAInd.springboot.domain.profiles.entity.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
@RestController
public class HelloController {
    //HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌
    // '/hello'로 요청이 오면 문자열 hello 반환
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, //외부에서 api로 넘긴 파라미터를 가져오는 어노테이션
                                     @RequestParam("amount") int amount) { //name과 amount는 api를 호출하는 곳에서 넘겨준 값들
        return new HelloResponseDto(name, amount);
    }


}
