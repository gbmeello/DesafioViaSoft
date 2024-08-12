package com.desafioviasoft.br.DesafioViaSoft.serviceTest;

import com.desafioviasoft.br.DesafioViaSoft.dto.EmailAwsDTO;
import com.desafioviasoft.br.DesafioViaSoft.dto.EmailDTO;
import com.desafioviasoft.br.DesafioViaSoft.dto.EmailOciDTO;
import com.desafioviasoft.br.DesafioViaSoft.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmailServiceTest {
    @InjectMocks
    private EmailService emailService;

    @Mock
    private ObjectMapper objectMapper;

    @Value("${mail.integracao}")
    private String mailIntegration;

    @Test
    void testSendEmailWithAwsIntegration() throws Exception {
        // Arrange
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient(String.valueOf(List.of("recipient@example.com")));
        emailDTO.setRecipientName("Recipient Name");
        emailDTO.setSender("sender@example.com");
        emailDTO.setSubject("Test Subject");
        emailDTO.setContent("Test Content");

        mailIntegration = "AWS";

        EmailAwsDTO awsDTO = emailService.mapToAws(emailDTO);

        // Act
        emailService.sendEmail(emailDTO);

        // Assert
        assertNotNull(awsDTO);
        assertEquals("recipient@example.com", awsDTO.getRecipient());
        assertEquals("Recipient Name", awsDTO.getRecipientName());
        assertEquals("sender@example.com", awsDTO.getSender());
        assertEquals("Test Subject", awsDTO.getSubject());
        assertEquals("Test Content", awsDTO.getContent());
    }

    @Test
    void testSendEmailWithOciIntegration() throws Exception {
        // Arrange
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient(String.valueOf(List.of("recipient@example.com")));
        emailDTO.setRecipientName("Recipient Name");
        emailDTO.setSender("sender@example.com");
        emailDTO.setSubject("Test Subject");
        emailDTO.setContent("Test Content");

        mailIntegration = "OCI";

        EmailOciDTO ociDTO = emailService.mapToOci(emailDTO);

        // Act
        emailService.sendEmail(emailDTO);

        // Assert
        assertNotNull(ociDTO);
        assertEquals("recipient@example.com", ociDTO.getRecipientEmail());
        assertEquals("Recipient Name", ociDTO.getRecipientName());
        assertEquals("sender@example.com", ociDTO.getSenderEmail());
        assertEquals("Test Subject", ociDTO.getSubject());
        assertEquals("Test Body", ociDTO.getBody());
    }

    @Test
    void testInvalidMailIntegration() {
        // Arrange
        EmailDTO emailDTO = new EmailDTO();
        mailIntegration = "INVALID";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            emailService.sendEmail(emailDTO);
        });
    }
}
