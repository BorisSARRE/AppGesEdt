package com.EdtAppProject.edtApp.controller;

import com.EdtAppProject.edtApp.dto.SalleDto;
import com.EdtAppProject.edtApp.service.ServiceMetier;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
@RestController
public class AppController {

    private final ServiceMetier serviceMetier;

    @PostMapping("/salle")
    public ResponseEntity<SalleDto> creerSalle(@RequestBody @Valid final SalleDto salleDto) {
        return new ResponseEntity<>(this.serviceMetier.creerSalle(salleDto), HttpStatus.CREATED);
    }
}
