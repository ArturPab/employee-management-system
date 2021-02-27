package pl.pabjan.employeemanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.pabjan.employeemanagementsystem.exceptions.EmployeeManagementSystemException;

@Component
@AllArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender mailSender;

    @Async
    public void sendmail(String email, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@facebookclone.com");
            message.setTo(email);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);
        }
        catch(Exception e) {
            throw new EmployeeManagementSystemException("Error during sending email to " + email);
        }
    }

}
