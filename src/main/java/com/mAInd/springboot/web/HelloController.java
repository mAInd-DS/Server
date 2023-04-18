package com.mAInd.springboot.web;
import com.mAInd.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello") //HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌
    public String hello(){ // /hello로 요청이 오면 문자열 hello 반환
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, //외부에서 api로 넘긴 파라미터를 가져오는 어노테이션
                                     @RequestParam("amount") int amount){ //name과 amount는 api를 호출하는 곳에서 넘겨준 값들
        return new HelloResponseDto(name, amount);
    }
}
