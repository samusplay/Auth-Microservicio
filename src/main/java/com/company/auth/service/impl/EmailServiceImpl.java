package com.company.auth.service.impl;

import com.company.auth.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    //proceso asincronico
    @Async
    public void sendVerificationEmail(String toEmail, String verificationCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            // Nombre
            message.setFrom("noreply@companypackges.com");
            message.setTo(toEmail);
            message.setSubject("Código de Verificación - Registro");
            message.setText("Hola, bienvenido.\n\n" +
                    "Tu código de verificación es: " + verificationCode + "\n\n" +
                    "Este código expirará en 15 minutos.");

            mailSender.send(message);
            System.out.println("Correo enviado exitosamente en segundo plano a: " + toEmail);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo a " + toEmail + ": " + e.getMessage());
        }

    }
}
