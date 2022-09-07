package com.example.study.repository;

import com.example.study.config.JpaConfig;
import com.example.study.component.LoginUserAuditorAware;
import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AdminUser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AdminUserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("AdminUser01");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
        //adminUser.setCreatedAt(LocalDateTime.now()); // LoginUserAuditorAware 적용으로 자동 createdAt, createdBy 설정
        //adminUser.setCreatedBy("AdminServer"); // LoginUserAuditorAware 적용으로 자동 createdAt, createdBy 설정

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminUser);
    }
}
