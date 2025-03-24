package com.inhomun.boardver2.controller;

import com.inhomun.boardver2.dto.MailDTO;
import com.inhomun.boardver2.service.MailService;
import com.inhomun.boardver2.service.MemberService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register/sendEmail")
    public ResponseEntity sendMessage(@RequestParam String mail) {
        try {
            memberService.createForm(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/register/verifyEmail")
    public ResponseEntity verifyEmail(@RequestBody MailDTO mailDTO) {
        boolean isVerified = memberService.verifyCode(mailDTO.getEmail(), mailDTO.getCode());
        if (isVerified) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
