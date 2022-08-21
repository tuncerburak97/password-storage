package com.tuncerburak.passwordstorage.builder;


import com.tuncerburak.passwordstorage.models.entity.User;
import com.tuncerburak.passwordstorage.models.payload.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBuilder {

    private final PasswordEncoder encoder;

    public User build(SignupRequest request){

        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(encoder.encode(request.getPassword()))
                .build();
    }

}
