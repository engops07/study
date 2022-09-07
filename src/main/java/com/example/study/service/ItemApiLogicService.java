package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    // DB 연결 시 final 선언 필요함.
    private final PartnerRepository partnerRepository;    // Partner DB 연결
    private final ItemRepository itemRepository;          // Item DB 연결

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        // 1. requests data body 조회
        ItemApiRequest body = request.getData();

        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .price(body.getPrice())     // integer -> BigDecimal
                .brandName(body.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build();

        // 2. newItem 저장
        Item newItem = itemRepository.save(item);

        // 3. 생성된 데이터 -> ItemApiResponse return     // Response Header 생성해야 사용 가능함.
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        // ItemRepository "id" 조회
        return itemRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(()-> Header.ERROR("데이터 없음"));    // Header.java ERROR 반환
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

        // data 조회
        ItemApiRequest body = request.getData();

        // id -> item 데이터 찾기
        return itemRepository.findById(body.getId())
                // data -> update
                .map(entityItem -> {
                    entityItem
                            .setStatus(body.getStatus())
                            .setName(body.getName())
                            .setTitle(body.getTitle())
                            .setContent(body.getContent())
                            .setPrice(body.getPrice())
                            .setBrandName(body.getBrandName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                            ;
                    return entityItem;
                })
                .map(newEntityItem -> itemRepository.save(newEntityItem))   // update data save
                .map(item -> response(item))                                // itemApiResponse
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        // data 조회
        itemRepository.findById(id)
                .map(item -> {
                    // item 삭제
                    itemRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("데이터 없음"));

        return null;
    }

    private Header<ItemApiResponse> response(Item item){
        // Item -> ItemApiResponse

        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();
        return Header.OK(body);
    }
}
