package com.desafioviasoft.br.DesafioViaSoft.controllerTest;

import com.desafioviasoft.br.DesafioViaSoft.controller.EmailController;
import com.desafioviasoft.br.DesafioViaSoft.dto.EmailDTO;
import com.desafioviasoft.br.DesafioViaSoft.service.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class EmailControllerTest {

    @InjectMocks
    private EmailController emailController;

    @Mock
    private EmailService emailService;

    @Test
    void testSendEmailSuccess() {
        // Arrange
        EmailDTO emailDTO = new EmailDTO();

        // Act
        ResponseEntity<Void> response = emailController.sendEmail(emailDTO);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testSendEmailBadRequest() throws Exception {
        // Arrange
        EmailDTO emailDTO = new EmailDTO();
        doThrow(IllegalArgumentException.class).when(emailService).sendEmail(any(EmailDTO.class));

        // Act
        ResponseEntity<Void> response = emailController.sendEmail(emailDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testSendEmailServerError() throws Exception {
        // Arrange
        EmailDTO emailDTO = new EmailDTO();
        doThrow(Exception.class).when(emailService).sendEmail(any(EmailDTO.class));

        // Act
        ResponseEntity<Void> response = emailController.sendEmail(emailDTO);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
