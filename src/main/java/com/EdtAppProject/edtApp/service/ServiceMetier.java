package com.EdtAppProject.edtApp.service;

import com.EdtAppProject.edtApp.dto.FiliereDto;
import com.EdtAppProject.edtApp.dto.SalleDto;
import com.EdtAppProject.edtApp.entite.Filiere;
import com.EdtAppProject.edtApp.entite.Salle;
import com.EdtAppProject.edtApp.mapstruct.SbMapper;
import com.EdtAppProject.edtApp.repository.FiliereRepository;
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
    private final FiliereRepository filiereRepository;
    private final SbMapper mapper;

    /*
     ************* Gestion des salles ****************
     */

    /**
     * Créer une salle.
     * @param filiereDto
     * @return SalleDto
     */
    public SalleDto creerSalle(SalleDto filiereDto){
        if (salleRepository.existsByIdOrNumeroSalle(filiereDto.getId(), filiereDto.getNumeroSalle())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La salle existe déjà !");
        }else {
            Salle salle = this.mapper.maps(filiereDto);
            return this.mapper.maps(this.salleRepository.save(salle));
        }
    }

    /**
     * Modifier une salle.
     * @param idSalle
     * @param filiereDto
     * @return SalleDto
     */
    public SalleDto modifierSalle(String idSalle, SalleDto filiereDto){
        if (salleRepository.existsByNumeroSalleAndIdNot(filiereDto.getNumeroSalle(),idSalle)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La salle existe déjà !");
        }else {
            Salle salle = this.salleRepository.getReferenceById(idSalle);
            salle.setNumeroSalle(filiereDto.getNumeroSalle());
            salle.setDisponibiliteSalle(filiereDto.getDisponibiliteSalle());
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

    /*
     ************* Gestion des Filières ****************
     */

    /**
     * Créer une filière.
     * @param filiereDto
     * @return FiliereDto
     */
    public FiliereDto creerFiliere(FiliereDto filiereDto){

            if (filiereRepository.existsByNomFiliereAndNiveau(filiereDto.getNomFiliere(),filiereDto.getNiveau())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La Filière existe déjà !");
            }else {
                Filiere filiere = this.mapper.maps(filiereDto);
                return this.mapper.maps(this.filiereRepository.save(filiere));
            }

    }

    /**
     * Modifier filiere.
     * @param idFiliere
     * @param filiereDto
     * @return FiliereDto
     */
    public FiliereDto modifierFiliere(String idFiliere, FiliereDto filiereDto){
        if (filiereRepository.existsByNomFiliereAndNiveau(filiereDto.getNomFiliere(),filiereDto.getNiveau())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La filiere existe déjà !");
        }else {
            Filiere filiere = this.filiereRepository.getReferenceById(idFiliere);
            filiere.setNomFiliere(filiereDto.getNomFiliere());
            filiere.setDescription(filiereDto.getDescription());
            filiere.setNiveau(filiereDto.getNiveau());
            return this.mapper.maps(this.filiereRepository.save(filiere));
        }
    }

    /**
     * Supprimer filière.
     * @param idFiliere
     */
    public void supprimerFiliere(String idFiliere){

        if (! filiereRepository.existsById(idFiliere)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette filière n'existe pas !");
        }else {
            filiereRepository.deleteById(idFiliere);
        }
    }

    /**
     * Lister toutes les filières.
     * @return List<FiliereDto>
     */
    public List<FiliereDto> listeFiliere(){
        List<Filiere> filieres = filiereRepository.findAll();
        return filieres.stream().map(this.mapper::maps).toList();
    }

}
