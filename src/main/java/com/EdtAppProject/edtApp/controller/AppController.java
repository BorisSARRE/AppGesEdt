package com.EdtAppProject.edtApp.controller;

import com.EdtAppProject.edtApp.dto.SalleDto;
import com.EdtAppProject.edtApp.service.ServiceMetier;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PutMapping("/salle/{id}")
    public ResponseEntity<SalleDto> modifierSalle(@PathVariable("id") final String idSalle,
                                                  @RequestBody @Valid final SalleDto salleDto) {
        return new ResponseEntity<>(this.serviceMetier.modifierSalle(idSalle, salleDto), HttpStatus.OK);
    }

    @DeleteMapping("/salle/{id}")
    public ResponseEntity<Void> supprimerSalle(@PathVariable("id") final String idSalle) {
        serviceMetier.supprimerSalle(idSalle);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/salle")
    public ResponseEntity<List<SalleDto>> listerSalle() {
        return new ResponseEntity<>(serviceMetier.listeSalle(), HttpStatus.OK);
    }
}
