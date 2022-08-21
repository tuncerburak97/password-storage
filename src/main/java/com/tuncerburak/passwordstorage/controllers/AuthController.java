package com.tuncerburak.passwordstorage.controllers;

import com.tuncerburak.passwordstorage.builder.UserBuilder;
import com.tuncerburak.passwordstorage.models.common.ERole;
import com.tuncerburak.passwordstorage.models.entity.Role;
import com.tuncerburak.passwordstorage.models.entity.User;
import com.tuncerburak.passwordstorage.models.payload.request.LoginRequest;
import com.tuncerburak.passwordstorage.models.payload.request.SignupRequest;
import com.tuncerburak.passwordstorage.models.payload.response.JwtResponse;
import com.tuncerburak.passwordstorage.models.payload.response.MessageResponse;
import com.tuncerburak.passwordstorage.models.payload.response.SigninResponse;
import com.tuncerburak.passwordstorage.models.payload.response.SignupResponse;
import com.tuncerburak.passwordstorage.repository.RoleRepository;
import com.tuncerburak.passwordstorage.repository.UserRepository;
import com.tuncerburak.passwordstorage.security.jwt.JwtUtils;
import com.tuncerburak.passwordstorage.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final JwtUtils jwtUtils;
  private final UserBuilder userBuilder;

  @RequestMapping(
          value = "/signin", method = RequestMethod.POST,
          consumes =  {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    if(userRepository.existsByUsername(loginRequest.getUsername()) == null){
      SigninResponse response = new SigninResponse("","",false,"","User not found");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    try {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      SigninResponse response = new SigninResponse(jwt,userDetails.getUsername(),true,userDetails.getFullName(),"User successfully logged in");
      return ResponseEntity.ok(response);
    }
    catch (Exception e){
      return ResponseEntity.badRequest().body(new SigninResponse(null,null,false,"",e.getMessage()));
    }

}

  @RequestMapping(value = "/signup", method = RequestMethod.POST,
          consumes =  {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest request) {

    if (userRepository.existsByUsername(request.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(request.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }


    SignupResponse response = new SignupResponse();
    User user = userBuilder.build(request);
    Set<Role> roles = new HashSet<>();

    {
      Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    }

    user.setRoles(roles);
    userRepository.save(user);
    response.setMessage( "User registered successfully");
    response.setSuccess(true);
   return ResponseEntity.ok().body(response);
  }

  @GetMapping("/response")
  private String response(){
    return "Get Response";
  }
}
