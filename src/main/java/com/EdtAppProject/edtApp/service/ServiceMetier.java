package com.EdtAppProject.edtApp.service;

import com.EdtAppProject.edtApp.dto.SalleDto;
import com.EdtAppProject.edtApp.entite.Salle;
import com.EdtAppProject.edtApp.mapstruct.SbMapper;
import com.EdtAppProject.edtApp.repository.SalleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class ServiceMetier {

    private final SalleRepository salleRepository;
    private final SbMapper mapper;

    /*
     ************* Gestion des salles ****************
     */

    /**
     * Créer une salle
     * @param salleDto
     * @return SalleDto
     */
    public SalleDto creerSalle(SalleDto salleDto){
        if (salleRepository.existsByIdOrNumeroSalle(salleDto.getId(), salleDto.getNumeroSalle())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La salle existe déjà !");
        }else {
            Salle salle = this.mapper.maps(salleDto);
            return this.mapper.maps(this.salleRepository.save(salle));
        }
    }
}
