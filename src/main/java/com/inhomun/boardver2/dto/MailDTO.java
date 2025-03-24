package com.inhomun.boardver2.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MailDTO {

    private String email;
    private String code;
    private String message;
    private LocalDateTime expiresTime;
}