package com.tuncerburak.passwordstorage.controllers;

import com.tuncerburak.passwordstorage.models.payload.request.ShowPasswordHistoryRequest;
import com.tuncerburak.passwordstorage.models.payload.response.ShowPasswordHistoryResponse;
import com.tuncerburak.passwordstorage.service.PasswordHistoryService;
import com.tuncerburak.passwordstorage.service.PasswordWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/warehouse/history")
@RequiredArgsConstructor
public class PasswordHistoryController {


    private final PasswordHistoryService passwordHistoryService;


    @RequestMapping(
            value = "/{userName}", method = RequestMethod.GET,
            consumes =  {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin(origins = "*", maxAge = 3600)
    List<ShowPasswordHistoryResponse> show(@PathVariable String userName){
        return passwordHistoryService.show(userName);
    }
}
