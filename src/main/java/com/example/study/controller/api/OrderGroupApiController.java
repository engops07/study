package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.service.OrderGroupApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderGroup")
@RequiredArgsConstructor    // final 선언시 annotation 선언
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    // service logic 연결
    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping("") // /api/orderGroup
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")   // /api/orderGroup/{id}
    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // /api/orderGroup
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")     // /api/orderGroup/{id}
    public Header delete(@PathVariable Long id) {
        return orderGroupApiLogicService.delete(id);
    }
}
