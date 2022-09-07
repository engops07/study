package com.example.study.ifs;

import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;

public interface CrudInterface<Req, Res> {  // generic type 정의 시 모든 controller 상속 가능

    Header<Res> create(Header<Req> request);     // todo request object 추가

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
