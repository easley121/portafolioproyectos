package com.nekorp.portafolio.proyectos.service;

import com.nekorp.portafolio.proyectos.entity.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${app.admin.email:admin@localhost}")
    private String adminEmail;

    @Value("${app.email.enabled:false}")
    private boolean emailEnabled;

    public void sendContactNotification(Contact contact) {
        if (!emailEnabled) {
            System.out.println("Email deshabilitado - Nuevo contacto recibido:");
            System.out.println("Nombre: " + contact.getName());
            System.out.println("Email: " + contact.getEmail());
            System.out.println("Asunto: " + contact.getSubject());
            return;
        }

        // TODO: Implementar envío real de email con JavaMailSender
        // Por ahora solo log
        System.out.println("Enviando notificación de contacto a: " + adminEmail);
        System.out.println("Nuevo mensaje de: " + contact.getName() + " (" + contact.getEmail() + ")");
    }

    public void sendAutoReply(Contact contact) {
        if (!emailEnabled) {
            return;
        }

        // TODO: Implementar auto-respuesta
        System.out.println("Enviando auto-respuesta a: " + contact.getEmail());
    }
}
