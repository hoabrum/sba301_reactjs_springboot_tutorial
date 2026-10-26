package com.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test", description = "Role-based access check endpoints")
@RequestMapping("/api/test")
public class TestController {

    @Operation(summary = "Public content")
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @Operation(summary = "USER, MANAGER or ADMIN content")
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @Operation(summary = "MANAGER or ADMIN content")
    @GetMapping("/manager")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String managerAccess() {
        return "Manager Board.";
    }

    @Operation(summary = "ADMIN content")
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
