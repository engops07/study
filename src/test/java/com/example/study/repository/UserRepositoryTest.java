package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection (DI)
    @Autowired // 해당 키워드를 찾아서 데이터를 입력
    private UserRepository userRepository;

    @Test
    public void create(){
        String account = "Test02";
        String password = "Test02";
        String status = "REGISTERED";
        String email = "Test02@gmail.com";
        String phoneNumber = "010-2222-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        // User 객체화
        User user = new User();

        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        // builder pattern
        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);
        //Assertions.assertEquals("AdminServer", newUser.getCreatedBy());


    }

    @Test
    @Transactional
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        // update -> chain pattern
        user
            .setEmail("")
            .setPhoneNumber("")
            .setStatus("");

        User u = new User().setAccount("").setEmail("").setPassword("");

        // Database 연관관계 설정으로 인해 DB 조회를 객체로 사용가능 (JPA 장점)
        if(user != null){ // npe 처리
            user.getOrderGroupList().stream().forEach(orderGroup -> {

                System.out.println("------------주문묶음------------ : ");
                System.out.println("수령인 : "+orderGroup.getRevName());
                System.out.println("수령지 : "+orderGroup.getRevAddress());
                System.out.println("총금액 : "+orderGroup.getTotalPrice());
                System.out.println("총수량 : "+orderGroup.getTotalQuantity());

                System.out.println("------------주문상세------------ : ");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : "+orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : "+orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : "+orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : "+orderDetail.getStatus());
                    System.out.println("도착예정일자 : "+orderDetail.getArrivalDate());
                });
            });
        }

    }

    @Test
    @Transactional // Rolled back transaction for test
    public void update(){

        // update user set account=?, created_at=?, created_by=?, email=?, phone_number=?, updated_at=?, updated_by=? where id=?
        Optional<User> user = userRepository.findById(2L); // update 기준 = select id

        user.ifPresent(selectUser ->{
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });

    }

    @Test
    @Transactional // Rolled back transaction for test
    public void delete(){
        Optional<User> user = userRepository.findById(3L); // delete 기준 = select id

        Assertions.assertTrue(user.isPresent()); // false

        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        if (deleteUser.isPresent()){
            System.out.println("데이터 존재 : "+deleteUser.get());
        }else {
            System.out.println("데이터 삭제 데이터 없음");
        }
    }
}
