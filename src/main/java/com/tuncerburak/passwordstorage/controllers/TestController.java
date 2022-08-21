package com.tuncerburak.passwordstorage.controllers;


import com.tuncerburak.passwordstorage.models.entity.User;
import com.tuncerburak.passwordstorage.repository.UserRepository;
import com.tuncerburak.passwordstorage.security.services.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/all")
  public String allAccess() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName = authentication.getName();
    UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

    User currentUser = userRepository.findById(user.getId()).orElseThrow(()-> new UsernameNotFoundException(currentUserName));

    return currentUser.getUsername();
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    log.info("Access Approved");
    return "User Content.";
  }

  @GetMapping("/teacher")
  @PreAuthorize("hasRole('TEACHER')")
  public String moderatorAccess() {
    log.info("Access Approved");
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    log.info("Access Approved");
    return "Admin Board.";
  }
}
