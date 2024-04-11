package com.example.jolvre.controller;

import com.example.jolvre.domain.user.User;
import com.example.jolvre.domain.user.dto.UserSignUpDTO;
import com.example.jolvre.infra.security.PrincipalDetails;
import com.example.jolvre.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/api/user/verify")
    public User verifyStudent(User user) {
        return userService.verifyStudent(user);
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserSignUpDTO userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);
        return "회원가입 성공";
    }

    @GetMapping("/jwt-test")
    public String jwtTest(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("name =  {}", principalDetails.getUser().getName());
        return "jwtTest 요청 성공";
    }

}
