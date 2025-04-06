package com.EdtAppProject.edtApp.service;

import com.EdtAppProject.edtApp.dto.EmploiDuTempsDto;
import com.EdtAppProject.edtApp.dto.FiliereDto;
import com.EdtAppProject.edtApp.dto.SalleDto;
import com.EdtAppProject.edtApp.entite.EmploiDuTemps;
import com.EdtAppProject.edtApp.entite.Enum.EStatutEdt;
import com.EdtAppProject.edtApp.entite.Filiere;
import com.EdtAppProject.edtApp.entite.Salle;
import com.EdtAppProject.edtApp.mapstruct.SbMapper;
import com.EdtAppProject.edtApp.repository.EmploiDuTempsRepository;
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
    private final EmploiDuTempsRepository emploiDuTempsRepository;
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

    /**
     ***************** Gestion des emplois du temps *******************
     */

    /**
     * Créer un emploi du temps
     * @param edt
     * @return EmploiDuTempsDto
     */
    public EmploiDuTempsDto creerEdt(EmploiDuTempsDto edt){

        if (edt.getDatePublication() != null && edt.getDateDebut() != null &&
        edt.getDatePublication().toLocalDate().isAfter(edt.getDateDebut())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La date de publication doit être antérieure " +
                    "à la date de début" );

        } else if (emploiDuTempsRepository.existsByDateDebutAndFiliere(edt.getDateDebut(),
                filiereRepository.getReferenceById(edt.getIdFiliere()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet emploi du temps est déjà établie pour "+
                    filiereRepository.getReferenceById(edt.getIdFiliere()).getNomFiliere() + " " +
                    filiereRepository.getReferenceById(edt.getIdFiliere()).getNiveau());

        }else {
            EmploiDuTemps emploiDuTemps = this.mapper.maps(edt);
            //emploiDuTemps.setDatePublication(LocalDateTime.now());  sera active dans la fonction publier un edt
            // Et l'on pourra mettre le statut à PUBLIE dans cette fonction.
            emploiDuTemps.setDateFin(edt.getDateDebut().plusDays(5));
            emploiDuTemps.setStatutEdt(EStatutEdt.BROUILLON);
            return this.mapper.maps(this.emploiDuTempsRepository.save(emploiDuTemps));
        }
    }

    /**
     * Modifier emploi du temps.
     * @param idEdt
     * @param emploiDuTempsDto
     * @return EmploiDuTempsDto
     */
    public EmploiDuTempsDto modifierEdt(String idEdt, EmploiDuTempsDto emploiDuTempsDto){

        if (!emploiDuTempsRepository.existsById(idEdt) || emploiDuTempsDto.getStatutEdt() != EStatutEdt.BROUILLON ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossible de modifier ! ");
        }else {
            if (emploiDuTempsDto.getDatePublication() != null && emploiDuTempsDto.getDateDebut() != null &&
                    emploiDuTempsDto.getDatePublication().toLocalDate().isAfter(emploiDuTempsDto.getDateDebut())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La date de publication doit être antérieure " +
                        "à la date de début" );

            } else if (emploiDuTempsRepository.existsByDateDebutAndFiliere(emploiDuTempsDto.getDateDebut(),
                    filiereRepository.getReferenceById(emploiDuTempsDto.getIdFiliere()))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet emploi du temps est déjà établie pour "+
                        filiereRepository.getReferenceById(emploiDuTempsDto.getIdFiliere()).getNomFiliere() + " " +
                        filiereRepository.getReferenceById(emploiDuTempsDto.getIdFiliere()).getNiveau());

            }else {

                EmploiDuTemps emploiDuTemps = this.emploiDuTempsRepository.getReferenceById(idEdt);
                emploiDuTemps.setDateDebut(emploiDuTempsDto.getDateDebut());
                emploiDuTemps.setDateFin(emploiDuTempsDto.getDateDebut().plusDays(5));
                emploiDuTemps.setStatutEdt(EStatutEdt.BROUILLON);
                emploiDuTemps.setFiliere(filiereRepository.getReferenceById(emploiDuTempsDto.getIdFiliere()));
                return this.mapper.maps(this.emploiDuTempsRepository.save(emploiDuTemps));
            }
        }

    }

    /**
     * Supprimer un emploi du temps.
     * @param idEdt
     */
    public void supprimerEdt(String idEdt){

            if (! emploiDuTempsRepository.existsById(idEdt)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette filière n'existe pas !");
            }else {
                emploiDuTempsRepository.deleteById(idEdt);
            }
    }


    /**
     * Lister les emplois du temps en fonction du nom de recherche de la filiere.
     * @param nomFiliere
     * @return List<EmploiDuTempsDto>
     */
    public List<EmploiDuTempsDto> listEdtFiliere(String nomFiliere){
        List<EmploiDuTemps> emploiDuTempsList = emploiDuTempsRepository.findByFiliereId(nomFiliere);
        return emploiDuTempsList.stream().map(this.mapper::maps).toList();
    }

    /**
     * Clore un edt.
     * @param idEdt
     */
    public void cloreEdt(String idEdt){
        if (! emploiDuTempsRepository.existsById(idEdt)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette filière n'existe pas !");
        } else if (emploiDuTempsRepository.getReferenceById(idEdt).getStatutEdt() != EStatutEdt.PUBLIE ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Cet emploi du temps ne peut pas être clos " +
                    "car il n'est pas encore publié ! ");
        } else {
            EmploiDuTemps emploiDuTemps = emploiDuTempsRepository.getReferenceById(idEdt);
            emploiDuTemps.setStatutEdt(EStatutEdt.CLOS);
            this.emploiDuTempsRepository.save(emploiDuTemps);

        }
    }


}
