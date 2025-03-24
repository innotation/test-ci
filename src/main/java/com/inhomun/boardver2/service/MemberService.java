package com.inhomun.boardver2.service;

import com.inhomun.boardver2.dto.MailDTO;
import com.inhomun.boardver2.repository.MailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MailRepository emailRepository;

    public void createForm(String email) throws MessagingException {
        MailDTO mailDTO = createVerificationCode(email);
        String title = "회원가입 인증 번호";
        String content = "<html>" + "<body>" + "<h1> 인증 코드: " + mailDTO.getCode() + "</h1>"
                + "<p> 해당 코드를 입력해주세요.</p>"
                + "<footer style='color: grey; font-size: small;'>"
                + "</footer>"
                + "</body>"
                + "</html>";
    }

    @Transactional
    @Scheduled(cron = "0 0 12 * * ?")
    public void deleteExpiredVerificationCodes() {

    }

    public MailDTO createVerificationCode(String mail) {
        // 이미 테이블에 있을 경우 삭제하고 재생성하는 로직 추가 필요
        String randomCode = generateRandomCode(6);
        MailDTO mailDTO = new MailDTO();
        mailDTO.setEmail(mail);
        mailDTO.setCode(randomCode);
        mailDTO.setExpiresTime(LocalDateTime.now().plusDays(1));
        return emailRepository.save(mailDTO);
    }

    public String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        ThreadLocalRandom  random = ThreadLocalRandom.current();

        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(characters.length());
            sb.append(characters.charAt(idx));
        }

        return sb.toString();
    }

    public boolean verifyCode(String mail, String code) {
        return emailRepository.findByEmail(mail)
                .map(vc -> vc.getExpiresTime().isAfter(LocalDateTime.now()))
                .orElse(false);
    }
}
