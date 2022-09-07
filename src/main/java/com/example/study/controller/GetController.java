package com.example.study.controller;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:8080/api/getMethod
    public String getRequests() {

        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=yymmdd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
        String password = "bbbb";
        System.out.println("id : " + id);
        System.out.println("password : " + pwd);

        return id + pwd;
    }

    // localhost:8080/api/getMultiParameter?account=abcd&email=study@plateer.com&page=10
    // getter, setter package 생성자로 호출
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // { "account" : "", "email" : "", "page" : "" }
        return searchParam;
    }
}

//    @GetMapping("/header")
//    public Header getHeader(){
//
//        // {"resultCode": "OK", "description": "OK"}
//        return Header.builder().resultCode("OK").description("OK").build();
//    }
//}

// HTTP - GET Method
// 주소 창에 파라미터가 노출 된다.
// 브라우저에서 주소에 대한 캐시가 이루어 지므로, 정보를 얻을 때 사용 한다.