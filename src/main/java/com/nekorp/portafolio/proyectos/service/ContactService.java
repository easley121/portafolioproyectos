package com.nekorp.portafolio.proyectos.service;

import com.nekorp.portafolio.proyectos.entity.Contact;
import com.nekorp.portafolio.proyectos.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmailService emailService;

    public Contact saveContact(Contact contact) {
        Contact savedContact = contactRepository.save(contact);

        // Enviar notificación por email (opcional)
        try {
            emailService.sendContactNotification(savedContact);
        } catch (Exception e) {
            // Log error pero no fallar el guardado
            System.err.println("Error enviando email de notificación: " + e.getMessage());
        }

        return savedContact;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAllOrderByCreatedAtDesc();
    }

    public List<Contact> getUnreadContacts() {
        return contactRepository.findByReadStatusFalse();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public void markAsRead(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            Contact c = contact.get();
            c.setReadStatus(true);
            contactRepository.save(c);
        }
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public long getUnreadCount() {
        return contactRepository.countByReadStatusFalse();
    }
}
