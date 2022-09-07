package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
// class mapping 주소는 중복되도 상관 없다.
public class PostController {

    // HTML <Form>
    // ajax 검색
    // http post body -> data
    // json, xml, multipart-form, text-plain

    // HTTP - POST Method
    // 주소 창에 사용자의 요청 사항이 노출 되지 않는다.
    // GET 방식에서는 주소 길이 제한이 있지만 POST는 그보다 길게 사용 가능(제한존재)
    // 브라우저가 주소 캐시를 하지 못 하는 특성이 있다.

    @PostMapping("/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

    // PUT/PATCH Method
    // POST와 마찬가지로 BODY에 데이터가 들어 있으며, 주로 업데이트에 사용한다.

    // DELETE Method
    // GET과 마찮가지로 주소에 파라미터가 들어가며, 데이터를 삭제할 때 사용한다.

    // REST API
    // HTTP 프로토콜에 있는 Method를 활용한 아키텍처 스타일
    // HTTP Method를 통해서 Resource를 처리 한다.
    // CRUD를 통한 Resource 조작을 할 때 사용 한다.

    // GET = (SELECT * READ) /user{id}
    // POST = (CREATE) / user
    // PUT, PATCH = (UPDATE) * CRATE /user
    // DELETE = (DELETE) /user/{1}
    @PutMapping()
    public void put(){

    }

//    @PatchMapping()
//    public void patch(){
//
//    }
}
