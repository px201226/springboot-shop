package com.alethio.service.springboot.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    @DisplayName("익명 유저가 /anonymous 엔드포인트를 접근할 할 수 있는지 검증 ")
    public void 익명유저는_annoymous_접근가능하다() throws Exception {
        mockMvc.perform(get("/anonymous"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    @DisplayName("익명 유저가 /user 엔드포인트를 접근불가를 검증 ")
    public void 익명유저는_user_접근불가능하다() throws Exception {
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithAnonymousUser
    @DisplayName("익명 유저가 /admin 엔드포인트를 접근불가를 검증 ")
    public void 익명유저는_admin_접근불가능하다() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "px2007@naver.com", password = "custom_password", roles = {"USER"})
    @DisplayName("USER권한의 사용자가 /user 엔드포인트에 접근 가능하다")
    public void USER_권한의_사용자는_user에_접근가능_하다() throws Exception {
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "px2007@naver.com", password = "custom_password", roles = {"ADMIN"})
    @DisplayName("ADMIN권한의 사용자가 /admin 엔드포인트에 접근 가능하다")
    public void ADMIN_권한의_사용자는_admin에_접근가능_하다() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}