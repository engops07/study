package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor // 모든 매개변수
@NoArgsConstructor
@Entity // ==table
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
@Builder // builder pattern 생성
@Accessors(chain = true) // update pattern
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql
    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private String phoneNumber;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // User 1 : N OrderGroup
    // User - OrderGroup 연결관계 설정
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;

}
