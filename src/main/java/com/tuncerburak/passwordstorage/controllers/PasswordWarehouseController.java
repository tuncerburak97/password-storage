package com.tuncerburak.passwordstorage.controllers;

import com.tuncerburak.passwordstorage.models.entity.PasswordWarehouse;
import com.tuncerburak.passwordstorage.models.payload.request.LoginRequest;
import com.tuncerburak.passwordstorage.models.payload.request.PasswordWarehouseCreateRequest;
import com.tuncerburak.passwordstorage.models.payload.request.PasswordWarehouseFindByUsernameRequest;
import com.tuncerburak.passwordstorage.models.payload.request.PasswordWarehouseUpdateRequest;
import com.tuncerburak.passwordstorage.models.payload.response.PasswordWarehouseCreateResponse;
import com.tuncerburak.passwordstorage.models.payload.response.PasswordWarehouseFindByUsernameResponse;
import com.tuncerburak.passwordstorage.models.payload.response.PasswordWarehouseUpdateResponse;
import com.tuncerburak.passwordstorage.service.PasswordWarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
@Slf4j
public class PasswordWarehouseController {

    private final PasswordWarehouseService passwordWarehouseService;


    @RequestMapping(
            value = "/", method = RequestMethod.POST,
            consumes =  {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody PasswordWarehouseCreateRequest request){

      var user = SecurityContextHolder.getContext().getAuthentication();
        return passwordWarehouseService.save(request);
    }

    @RequestMapping(
            value = "/list/{userName}", method = RequestMethod.GET,
            consumes =  {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin(origins = "*", maxAge = 3600)
    public List<PasswordWarehouseFindByUsernameResponse> findByUserName(
            @Valid @PathVariable String userName){
        return passwordWarehouseService.findAllByUserName(userName);
    }

    @RequestMapping(
            value = "/", method = RequestMethod.PUT,
            consumes =  {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<?> update(@Valid @RequestBody PasswordWarehouseUpdateRequest request) throws Exception {
        return passwordWarehouseService.update(request);
    }



}
