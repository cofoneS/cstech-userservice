package com.cstech.userservice.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cstech.userservice.app.model.CustomResponseModel;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/health-check")
public class HealthController extends CustomController{

    @GetMapping
    public @NotNull CustomResponseModel<Boolean> healthCheck() {
        return doResponseOk();
    }
        
}
