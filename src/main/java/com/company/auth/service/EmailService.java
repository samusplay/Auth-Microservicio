package com.company.auth.service;

public interface EmailService {
    //metodo para enviar solictud de email
    void sendVerificationEmail(String toEmail, String verificationCode);

}
