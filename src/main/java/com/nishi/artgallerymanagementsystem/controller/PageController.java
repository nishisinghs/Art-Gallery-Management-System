package com.nishi.artgallerymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.nishi.artgallerymanagementsystem.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.nishi.artgallerymanagementsystem.entity.Artwork;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.nishi.artgallerymanagementsystem.entity.Contact;
import com.nishi.artgallerymanagementsystem.service.ContactService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @Autowired
    private ArtworkService artworkService;

    @Autowired
    private ContactService contactService;

    @GetMapping("/admin")
    public String admin(Model model) {

        List<Artwork> artworks = artworkService.getAllArtworks();

        long totalCategories = artworks.stream()
                .map(Artwork::getCategory)
                .filter(category -> category != null && !category.isBlank())
                .distinct()
                .count();

        model.addAttribute("artworks", artworks);
        model.addAttribute("totalArtworks", artworks.size());
        model.addAttribute("totalCategories", totalCategories);
        model.addAttribute("totalMessages",contactService.getTotalMessages());

        return "admin";
    }

    @GetMapping("/admin/add")
    public String showAddForm(Model model) {
        model.addAttribute("artwork", new Artwork());
        return "artwork-form";
    }

    @PostMapping("/admin/save")
    public String saveArtwork(@ModelAttribute Artwork artwork) {
        artworkService.saveArtwork(artwork);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Artwork artwork = artworkService.getArtworkById(id);
        model.addAttribute("artwork", artwork);
        return "artwork-form";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteArtwork(@PathVariable Long id) {
        artworkService.deleteArtwork(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/messages")
    public String viewMessages(Model model) {

        model.addAttribute("messages",
                contactService.getAllContacts());

        return "messages";
    }

    @GetMapping("/admin/messages/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {

        contactService.deleteContact(id);

        return "redirect:/admin/messages";
    }

    @GetMapping("/gallery")
    public String gallery(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            Model model) {

        List<Artwork> artworks;

        if (category != null && !category.isBlank()) {
            artworks = artworkService.getArtworksByCategory(category);
        }
        else if (keyword != null && !keyword.isBlank()) {
            artworks = artworkService.searchArtworks(keyword);
        }
        else {
            artworks = artworkService.getAllArtworks();
        }

        model.addAttribute("artworks", artworks);
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);

        return "gallery";
    }

    @GetMapping("/artwork/{id}")
    public String artworkDetails(@PathVariable Long id, Model model) {

        Artwork artwork = artworkService.getArtworkById(id);

        model.addAttribute("artwork", artwork);

        return "artwork-details";
    }

    @GetMapping("/artists")
    public String artists() {
        return "artists";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {

        model.addAttribute("contact", new Contact());

        return "contact";
    }

    @PostMapping("/contact")
    public String saveContact(@ModelAttribute Contact contact, Model model) {

        contactService.saveContact(contact);

        model.addAttribute("success",
                "Your message has been sent successfully!");

        // Empty object
        model.addAttribute("contact", new Contact());

        return "contact";
    }
}
