package com.example.MSA_test.user.controller;
import com.example.MSA_test.common.dto.UserDto;
import com.example.MSA_test.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/users") @RequiredArgsConstructor
public class UserController {
    private final UserService svc;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto dto){
        return ResponseEntity.ok(svc.create(dto));
    }

    @GetMapping
    public List<UserDto> list(){ return svc.list(); }
}