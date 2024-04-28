package com.example.jolvre.auth.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DuplicateCheckDTO {

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class EmailDuplicateRequest {
        private String email;
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class EmailDuplicateResponse {
        private boolean check;
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class NicknameDuplicateRequest {
        private String Nickname;
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class NicknameDuplicateResponse {
        private boolean check;
    }
}