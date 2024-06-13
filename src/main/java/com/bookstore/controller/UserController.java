package com.bookstore.controller;

import com.bookstore.entity.User;
import com.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/change-password")
    public String changePasswordForm(Model model) {
        model.addAttribute("changePasswordForm", new ChangePasswordForm());
        return "user/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(ChangePasswordForm form, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);

        if (!passwordEncoder.matches(form.getCurrentPassword(), user.getPassword())) {
            model.addAttribute("error", "Mật khẩu hiện tại không đúng");
            return "user/change-password";
        }

        if (!form.getNewPassword().equals(form.getConfirmNewPassword())) {
            model.addAttribute("error", "Mật khẩu mới không khớp");
            return "user/change-password";
        }

        user.setPassword(passwordEncoder.encode(form.getNewPassword()));
        userService.save(user);

        model.addAttribute("message", "Đổi mật khẩu thành công");
        return "user/change-password";
    }
}
