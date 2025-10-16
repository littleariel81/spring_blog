package uon.spring_blog.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uon.spring_blog.dto.AddUserRequest;
import uon.spring_blog.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserApiController {
private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        // refresh_token 쿠키 삭제
        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setMaxAge(0); // 즉시 만료
        cookie.setPath("/"); // 생성할 때와 동일한 Path로 맞춰야 함
        response.addCookie(cookie);

        return "redirect:/login?logoutSuccess=true";
    }

}
