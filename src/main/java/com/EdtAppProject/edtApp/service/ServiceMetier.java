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

import java.util.List;

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
     * Créer une salle.
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

    /**
     * Modifier une salle.
     * @param idSalle
     * @param salleDto
     * @return SalleDto
     */
    public SalleDto modifierSalle(String idSalle, SalleDto salleDto){
        if (salleRepository.existsByNumeroSalle(salleDto.getNumeroSalle())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La salle existe déjà !");
        }else {
            Salle salle = this.salleRepository.getReferenceById(idSalle);
            salle.setNumeroSalle(salleDto.getNumeroSalle());
            salle.setDisponibiliteSalle(salleDto.getDisponibiliteSalle());
            return this.mapper.maps(this.salleRepository.save(this.salleRepository.save(salle)));
        }
    }

    /**
     * Supprimer une salle0.
     * @param idSalle
     */
    public void supprimerSalle(String idSalle){

        if (! salleRepository.existsById(idSalle)){
            throw new ResponseStatusException(HttpStatus.OK, "Cette salle n'existe pas !");
        }else {
            salleRepository.deleteById(idSalle);
        }
    }

    /**
     * Lister les salles.
     * @return List<SalleDto>
     */
    public List<SalleDto> listeSalle(){
       List<Salle> salles = salleRepository.findAll();
       return salles.stream().map(this.mapper::maps).toList();
    }
}
