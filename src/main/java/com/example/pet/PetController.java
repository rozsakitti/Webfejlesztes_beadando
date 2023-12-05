package com.example.pet;

import com.example.user.User;
import com.example.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PetController {
    @Autowired private PetService service;
    @Autowired private UserService userservice;


    @GetMapping("/pets")
    public String showPetList(Model model){
        List<Pet> listPets = service.listAll();
        model.addAttribute("listPets", listPets);

        return "pets";
    }

    @GetMapping("/pets/new")
    public String showNewForm(Model model){
        List<User> userList = userservice.listAll();
        model.addAttribute("pet", new Pet());
        model.addAttribute("userList", userList);
        model.addAttribute("pageTitle","Add New Pet");
        return "pet_form";
    }

    @PostMapping("/pets/save")
    public String savePet(Pet pet, RedirectAttributes ra){
        service.save(pet);
        ra.addFlashAttribute("message", "The pet has been save succesfully");
        return "redirect:/pets";
    }

    @GetMapping("/pets/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Pet pet = service.get(id);
            List<User> userList = userservice.listAll();
            model.addAttribute("pet", pet);
            model.addAttribute("userList", userList);
            model.addAttribute("pageTitle","Edit Pet (ID: " + id + ")");

            return "pet_form";
        } catch (PetNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/pets";
        }
    }

    @GetMapping("/pets/delete/{id}")
    public String deletePet(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The Pet ID "+ id + " has been deleted");
        } catch (PetNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/pets";
    }
}
