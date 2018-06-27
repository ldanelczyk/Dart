package pl.dels.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.dels.service.UserService;

@Controller
public class UserController {

	private UserService userService;

	@Autowired
	private void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	private String loginForm() {
		return "login";
	}

	@GetMapping("/logout")
	private String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@GetMapping("/register")
	private String register() {
		return "register";
	}

	@PostMapping("/register")
	private String addUser(@RequestParam String username, @RequestParam String password, @RequestParam String email,
			@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phoneNumber) {

		if (userService.checkDuplicate(username, email)) {
			userService.saveUserInDatabase(username, password, email, firstName, lastName, phoneNumber);
			return "redirect:success";
		} else {
			return "redirect:problem";
		}
	}

	@GetMapping("/success")
	private String registerOk() {
		return "registerOk";
	}

	@GetMapping("/problem")
	private String registerNoOk() {
		return "registerNoOk";
	}
}
