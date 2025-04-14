package com.EdtAppProject.edtApp.controller;

import com.EdtAppProject.edtApp.dto.CoursDto;
import com.EdtAppProject.edtApp.dto.DevoirDto;
import com.EdtAppProject.edtApp.dto.EmploiDuTempsDto;
import com.EdtAppProject.edtApp.dto.EnseignantDto;
import com.EdtAppProject.edtApp.dto.FiliereDto;
import com.EdtAppProject.edtApp.dto.IndisponibiliteDto;
import com.EdtAppProject.edtApp.dto.MatiereDto;
import com.EdtAppProject.edtApp.dto.SalleDto;
import com.EdtAppProject.edtApp.entite.Enseignant;
import com.EdtAppProject.edtApp.entite.Enum.EStatutEdt;
import com.EdtAppProject.edtApp.entite.Matiere;
import com.EdtAppProject.edtApp.service.ServiceMetier;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AppController {

    private final ServiceMetier serviceMetier;

    /**
     ****************** Endpoints salles *************
     */

    @PostMapping("/salle")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SalleDto> creerSalle(@RequestBody @Valid final SalleDto salleDto) {
        return new ResponseEntity<>(this.serviceMetier.creerSalle(salleDto), HttpStatus.CREATED);
    }

    @PutMapping("/salle/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SalleDto> modifierSalle(@PathVariable("id") final String idSalle,
                                                  @RequestBody @Valid final SalleDto salleDto) {
        return new ResponseEntity<>(this.serviceMetier.modifierSalle(idSalle, salleDto), HttpStatus.OK);
    }


    @DeleteMapping("/salle/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerSalle(@PathVariable("id") final String idSalle) {
        serviceMetier.supprimerSalle(idSalle);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/salle")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SalleDto>> listerSalle() {
        return new ResponseEntity<>(serviceMetier.listeSalle(), HttpStatus.OK);
    }

    /**
     *************  Endpoints Filières *******************
     */

    @PostMapping("/filiere")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FiliereDto> creerFiliere(@RequestBody @Valid final FiliereDto filiereDto) {
        return new ResponseEntity<>(this.serviceMetier.creerFiliere(filiereDto), HttpStatus.CREATED);
    }

    @PutMapping("/filiere/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FiliereDto> modifierfilere(@PathVariable("id") final String idFiliere,
                                                  @RequestBody @Valid final FiliereDto filiereDto) {
        return new ResponseEntity<>(this.serviceMetier.modifierFiliere(idFiliere, filiereDto), HttpStatus.OK);
    }

    @DeleteMapping("/filiere/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerFilere(@PathVariable("id") final String idFiliere) {
        serviceMetier.supprimerFiliere(idFiliere);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filiere")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FiliereDto>> listerFiliere() {
        return new ResponseEntity<>(serviceMetier.listeFiliere(), HttpStatus.OK);
    }

    /**
     ********** Endpoints pour Emploi du temps ***********************
     */

    @PostMapping("/edt")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmploiDuTempsDto> creerEdt(@RequestBody @Valid final EmploiDuTempsDto emploiDuTempsDto) {
        return new ResponseEntity<>(this.serviceMetier.creerEdt(emploiDuTempsDto), HttpStatus.CREATED);
    }

    @PutMapping("/edt/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmploiDuTempsDto> modifierEdt(@PathVariable("id") final String idEdt,
                                                     @RequestBody @Valid final EmploiDuTempsDto emploiDuTempsDto) {
        return new ResponseEntity<>(this.serviceMetier.modifierEdt(idEdt, emploiDuTempsDto), HttpStatus.OK);
    }

    @DeleteMapping("/edt/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerEdt(@PathVariable("id") final String idEdt) {
        serviceMetier.supprimerEdt(idEdt);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Rechercher l'emploi du temps par un nom de filière.
     * @param recherche
     * @return
     */
    @GetMapping("/edt")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<List<EmploiDuTempsDto>> listEdtFiliere(@RequestParam(value = "recherche", required = false)
                                                                 final String recherche) {
        return new ResponseEntity<>(serviceMetier.listEdtFiliere(recherche), HttpStatus.OK);
    }

    @PatchMapping("/edt/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> cloreEdt(@PathVariable("id") final String idEdt) {
        this.serviceMetier.cloreEdt(idEdt);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     ********** Endpoints pour Matiere ***********************
     */

    @PostMapping("/matiere")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MatiereDto> creerMatiere(@RequestBody @Valid final MatiereDto matiereDto) {
        return new ResponseEntity<>(this.serviceMetier.creerMatiere(matiereDto), HttpStatus.CREATED);
    }

    @GetMapping("/matiere")
    public ResponseEntity<List<MatiereDto>> getAllMatieres(){
        return new ResponseEntity<>(serviceMetier.listAllMatieres(),HttpStatus.OK);
    }

    @PutMapping("/matiere/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MatiereDto> modifierMatiere(@PathVariable("id") final String idMatiere,
                                                   @RequestBody @Valid final MatiereDto matiereDto) {
        return new ResponseEntity<>(this.serviceMetier.modifierMatiere(idMatiere, matiereDto), HttpStatus.OK);
    }

    @DeleteMapping("/matiere/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerMatiere(@PathVariable("id") final String idMatiere) {
        serviceMetier.supprimerMatiere(idMatiere);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/matiere/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MatiereDto>> listMatiereParFiliere(@PathVariable("id") final String idFiliere) {
        return new ResponseEntity<>(serviceMetier.listMatiereParFiliere(idFiliere), HttpStatus.OK);
    }

    /**
     * ***************** Endpoints Indisponibilites **************
     */

    /**
     * Créer une indisponibilité.
     * @param indisponibiliteDto
     * @return ResponseEntity<IndisponibiliteDto>
     */

    @PostMapping("/indisponibilites")
    @PreAuthorize("hasRole('ENSEIGNANT')")
    public ResponseEntity<IndisponibiliteDto> createIndisponibilite(@Valid @RequestBody final IndisponibiliteDto indisponibiliteDto) {
        IndisponibiliteDto createdDto = serviceMetier.createIndisponibilite(indisponibiliteDto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }


    /**
     * Récupérer une indisponibilité par son ID.
     * @param id
     * @return ResponseEntity<IndisponibiliteDto>
     */
    @GetMapping("/indisponibilites/{id}")
    @PreAuthorize("hasRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<IndisponibiliteDto> getIndisponibiliteById(@PathVariable("id") final String id) {
        IndisponibiliteDto dto = serviceMetier.getIndisponibiliteById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Récupérer toutes les indisponibilités.
     * @return ResponseEntity<List<IndisponibiliteDto>>
     */
    @GetMapping("/indisponibilites")
    @PreAuthorize("hasRole('ADMIN' ,'ENSEIGNANT')")
    public ResponseEntity<List<IndisponibiliteDto>> getAllIndisponibilites() {
        List<IndisponibiliteDto> dtos = serviceMetier.getAllIndisponibilites();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    /**
     * Mettre à jour une indisponibilité.
     * @param indisponibiliteDto
     * @return ResponseEntity<IndisponibiliteDto>
     */
    @PutMapping("/indisponibilites/{id}")
    @PreAuthorize("hasRole('ENSEIGNANT')")
    public ResponseEntity<IndisponibiliteDto> updateIndisponibilite(@PathVariable("id") final String idIndisponibilite,
                                                                    @Valid @RequestBody final IndisponibiliteDto indisponibiliteDto) {
        IndisponibiliteDto updatedDto = serviceMetier.updateIndisponibilite(idIndisponibilite, indisponibiliteDto);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    /**
     * Supprimer une indisponibilité.
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/indisponibilites/{id}")
    @PreAuthorize("hasRole('ENSEIGNANT')")
    public ResponseEntity<Void> deleteIndisponibilite(@PathVariable("id") final String id) {
        serviceMetier.deleteIndisponibilite(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * *************** Endpoint utilisateur ********************
     */
    @PostMapping("/enseignant")
    public ResponseEntity<EnseignantDto> createEnseignant(@Valid @RequestBody final EnseignantDto enseignantDto) {
        EnseignantDto enseignant = serviceMetier.creerEnseignant(enseignantDto);
        return new ResponseEntity<>(enseignant, HttpStatus.CREATED);
    }

    /**
     ********************* Endpoints Cours ********************
     */

    @PostMapping("/cours")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CoursDto> creerCours(@RequestBody @Valid final CoursDto coursDto) {
        return new ResponseEntity<>(this.serviceMetier.creerCours(coursDto), HttpStatus.CREATED);
    }

    @PutMapping("/cours/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CoursDto> modifierCours(@PathVariable("id") final String idCours,
                                                                    @Valid @RequestBody final CoursDto coursDto) {
        CoursDto cours = serviceMetier.modifierCours(idCours, coursDto);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }

    @DeleteMapping("/cours/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerCours(@PathVariable("id") final String idCours) {
        serviceMetier.supprimerCours(idCours);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cours/{id1}/{id2}")
    @PreAuthorize("hasAnyRole('ADMIN' ,'ENSEIGNANT')")
    public ResponseEntity<List<CoursDto>> listerCoursParEmploiDuTempsEtFiliere(@PathVariable("id1") final String idEmploiDuTemps,
                                                                               @PathVariable("id2") final String idFiliere) {
        List<CoursDto> coursDtos = serviceMetier.listerCoursParEmploiDuTempsEtFiliere(idEmploiDuTemps, idFiliere);
        return new ResponseEntity<>(coursDtos, HttpStatus.OK);
    }

    @PatchMapping("/cours1/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' ,'ETUDIANT')")
    public ResponseEntity<CoursDto> changerStatutACoursFait(@PathVariable("id") final String idCours) {
        return new ResponseEntity<>(this.serviceMetier.changerStatutACoursFait(idCours), HttpStatus.OK);
    }

    @PatchMapping("/cours2/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' ,'ENSEIGNANT')")
    public ResponseEntity<Void> marquerIndisponibilite(@PathVariable("id") final String idCour) {
        this.serviceMetier.marquerIndisponibilite(idCour);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     ************** Endpoint des devoirs *************************
     */

    @PostMapping("/devoir")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DevoirDto> creerDevoir(@RequestBody @Valid final DevoirDto devoirDto) {
        return new ResponseEntity<>(this.serviceMetier.creerDevoir(devoirDto), HttpStatus.CREATED);
    }

    @PutMapping("/devoir/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DevoirDto> modifierDevoir(@PathVariable("id") final String idDevoir,
                                                  @Valid @RequestBody final DevoirDto devoirDto) {
        DevoirDto dto = serviceMetier.modifierDevoir(idDevoir, devoirDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/devoir/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerDevoir(@PathVariable("id") final String idDevoir) {
        serviceMetier.supprimerDevoir(idDevoir);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/devoir/{id1}/{id2}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DevoirDto>> listerDevoirParEmploiDuTempsEtFiliere(@PathVariable("id1") final String idEmploiDuTemps,
                                                                               @PathVariable("id2") final String idFiliere) {
        List<DevoirDto> devoirDtos = serviceMetier.listerDevoirParEmploiDuTempsEtFiliere(idEmploiDuTemps, idFiliere);
        return new ResponseEntity<>(devoirDtos, HttpStatus.OK);
    }

    @PatchMapping("/devoir/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DevoirDto> annulerDevoir(@PathVariable("id") final String idDevoir) {
        return new ResponseEntity<>(this.serviceMetier.annulerDevoir(idDevoir), HttpStatus.OK);
    }

}
