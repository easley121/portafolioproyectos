package com.nekorp.portafolio.proyectos.controller;

import com.nekorp.portafolio.proyectos.dto.ContactDTO;
import com.nekorp.portafolio.proyectos.entity.Contact;
import com.nekorp.portafolio.proyectos.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("title", "Contacto - Portfolio");
        model.addAttribute("contactDTO", new ContactDTO());
        return "pages/contact";
    }

    @PostMapping("/contact")
    public String submitContactForm(@Valid @ModelAttribute ContactDTO contactDTO,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {

        if (result.hasErrors()) {
            model.addAttribute("title", "Contacto - Portfolio");
            return "pages/contact";
        }

        try {
            // Convertir DTO a entidad
            Contact contact = new Contact();
            contact.setName(contactDTO.getName());
            contact.setEmail(contactDTO.getEmail());
            contact.setSubject(contactDTO.getSubject());
            contact.setMessage(contactDTO.getMessage());
            contact.setPhone(contactDTO.getPhone());
            contact.setCompany(contactDTO.getCompany());

            contactService.saveContact(contact);

            redirectAttributes.addFlashAttribute("successMessage",
                    "¡Gracias por tu mensaje! Te responderé lo antes posible.");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Hubo un error al enviar tu mensaje. Por favor, intenta nuevamente.");
        }

        return "redirect:/contact";
    }
}
