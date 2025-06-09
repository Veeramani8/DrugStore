package com.drugstore.Controller;


import com.drugstore.DTO.LoginRequestDTO;
import com.drugstore.DTO.LoginResponseDTO;
import com.drugstore.DTO.UserRequestDTO;
import com.drugstore.DTO.UserResponseDTO;
import com.drugstore.Model.User;
import com.drugstore.Service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/list";
    }
    
    @GetMapping("/list")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user-list"; 
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user-edit";
    }


    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable long id, @ModelAttribute("User") UserRequestDTO dto) {
    	userService.updateUser(id,dto);
        return "redirect:/list";
    }
    
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
	    return "login";
	}

   
//	@PostMapping("/login")
//	public String login(@RequestParam("username") String username,
//	                    @RequestParam("password") String password,
//	                    Model model,
//	                    HttpSession session) {
//
//	    try {
//	        LoginResponseDTO user = userService.login(new LoginRequestDTO(username, password));
//	        session.setAttribute("loggedInUser", user); // store user in session
//	        return "redirect:/dashboard";
//	    } catch (Exception e) {
//	        model.addAttribute("error", "Invalid credentials");
//	        return "login";
//	    }
//	}





}



    

