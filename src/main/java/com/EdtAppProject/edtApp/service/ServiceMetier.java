package com.EdtAppProject.edtApp.service;

import com.EdtAppProject.edtApp.dto.CoursDto;
import com.EdtAppProject.edtApp.dto.DevoirDto;
import com.EdtAppProject.edtApp.dto.EmploiDuTempsDto;
import com.EdtAppProject.edtApp.dto.EnseignantDto;
import com.EdtAppProject.edtApp.dto.FiliereDto;
import com.EdtAppProject.edtApp.dto.IndisponibiliteDto;
import com.EdtAppProject.edtApp.dto.MatiereDto;
import com.EdtAppProject.edtApp.dto.SalleDto;
import com.EdtAppProject.edtApp.entite.Cours;
import com.EdtAppProject.edtApp.entite.Devoir;
import com.EdtAppProject.edtApp.entite.EmploiDuTemps;
import com.EdtAppProject.edtApp.entite.Enseignant;
import com.EdtAppProject.edtApp.entite.Enum.EDisponibiliteProf;
import com.EdtAppProject.edtApp.entite.Enum.ESatutDevoir;
import com.EdtAppProject.edtApp.entite.Enum.EStatutCours;
import com.EdtAppProject.edtApp.entite.Enum.EStatutEdt;
import com.EdtAppProject.edtApp.entite.Enum.EStatutMatiere;
import com.EdtAppProject.edtApp.entite.Filiere;
import com.EdtAppProject.edtApp.entite.Indisponibilite;
import com.EdtAppProject.edtApp.entite.Matiere;
import com.EdtAppProject.edtApp.entite.Salle;
import com.EdtAppProject.edtApp.mapstruct.SbMapper;
import com.EdtAppProject.edtApp.repository.CoursRepository;
import com.EdtAppProject.edtApp.repository.DevoirRepository;
import com.EdtAppProject.edtApp.repository.EmploiDuTempsRepository;
import com.EdtAppProject.edtApp.repository.EnseignantRepository;
import com.EdtAppProject.edtApp.repository.FiliereRepository;
import com.EdtAppProject.edtApp.repository.IndisponibiliteRepository;
import com.EdtAppProject.edtApp.repository.MatiereRepository;
import com.EdtAppProject.edtApp.repository.SalleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class ServiceMetier {

    private final SalleRepository salleRepository;
    private final FiliereRepository filiereRepository;
    private final EmploiDuTempsRepository emploiDuTempsRepository;
    private final MatiereRepository matiereRepository;
    private final IndisponibiliteRepository indisponibiliteRepository;
    private final EnseignantRepository enseignantRepository;
    private final CoursRepository coursRepository;
    private final DevoirRepository devoirRepository;
    private final SbMapper mapper;

    /*
     ************* Gestion des salles ****************
     */

    /**
     * Créer une salle.
     *
     * @param filiereDto
     * @return SalleDto
     */
    public SalleDto creerSalle(final SalleDto filiereDto) {
        if (salleRepository.existsByIdOrNumeroSalle(filiereDto.getId(), filiereDto.getNumeroSalle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La salle existe déjà !");
        } else {
            Salle salle = this.mapper.maps(filiereDto);
            return this.mapper.maps(this.salleRepository.save(salle));
        }
    }

    /**
     * Modifier une salle.
     *
     * @param idSalle
     * @param filiereDto
     * @return SalleDto
     */
    public SalleDto modifierSalle(final String idSalle, final SalleDto filiereDto) {
        if (salleRepository.existsByNumeroSalleAndIdNot(filiereDto.getNumeroSalle(), idSalle)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La salle existe déjà !");
        } else {
            Salle salle = this.salleRepository.getReferenceById(idSalle);
            salle.setNumeroSalle(filiereDto.getNumeroSalle());
            salle.setDisponibiliteSalle(filiereDto.getDisponibiliteSalle());
            return this.mapper.maps(this.salleRepository.save(this.salleRepository.save(salle)));
        }
    }

    /**
     * Supprimer une salle0.
     *
     * @param idSalle
     */
    public void supprimerSalle(final String idSalle) {

        if (!salleRepository.existsById(idSalle)) {
            throw new ResponseStatusException(HttpStatus.OK, "Cette salle n'existe pas !");
        } else {
            salleRepository.deleteById(idSalle);
        }
    }

    /**
     * Lister les salles.
     *
     * @return List<SalleDto>
     */
    public List<SalleDto> listeSalle() {
        List<Salle> salles = salleRepository.findAll();
        return salles.stream().map(this.mapper::maps).toList();
    }

    /*
     ************* Gestion des Filières ****************
     */

    /**
     * Créer une filière.
     *
     * @param filiereDto
     * @return FiliereDto
     */
    public FiliereDto creerFiliere(final FiliereDto filiereDto) {

        if (filiereRepository.existsByNomFiliereAndNiveau(filiereDto.getNomFiliere(), filiereDto.getNiveau())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La Filière existe déjà !");
        } else {
            Filiere filiere = this.mapper.maps(filiereDto);
            return this.mapper.maps(this.filiereRepository.save(filiere));
        }

    }

    /**
     * Modifier filiere.
     *
     * @param idFiliere
     * @param filiereDto
     * @return FiliereDto
     */
    public FiliereDto modifierFiliere(final String idFiliere, final FiliereDto filiereDto) {
        if (filiereRepository.existsByNomFiliereAndNiveau(filiereDto.getNomFiliere(), filiereDto.getNiveau())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La filiere existe déjà !");
        } else {
            Filiere filiere = this.filiereRepository.getReferenceById(idFiliere);
            filiere.setNomFiliere(filiereDto.getNomFiliere());
            filiere.setDescription(filiereDto.getDescription());
            filiere.setNiveau(filiereDto.getNiveau());
            return this.mapper.maps(this.filiereRepository.save(filiere));
        }
    }

    /**
     * Supprimer filière.
     *
     * @param idFiliere
     */
    public void supprimerFiliere(final String idFiliere) {

        if (!filiereRepository.existsById(idFiliere)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette filière n'existe pas !");
        } else {
            filiereRepository.deleteById(idFiliere);
        }
    }

    /**
     * Lister toutes les filières.
     *
     * @return List<FiliereDto>
     */
    public List<FiliereDto> listeFiliere() {
        List<Filiere> filieres = filiereRepository.findAll();
        return filieres.stream().map(this.mapper::maps).toList();
    }

    /**
     ***************** Gestion des emplois du temps *******************
     */

    /**
     * Créer un emploi du temps
     *
     * @param edt
     * @return EmploiDuTempsDto
     */
    public EmploiDuTempsDto creerEdt(final EmploiDuTempsDto edt) {

        if (edt.getDatePublication() != null && edt.getDateDebut() != null &&
                edt.getDatePublication().toLocalDate().isAfter(edt.getDateDebut())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La date de publication doit être antérieure " +
                    "à la date de début");

        } else if (emploiDuTempsRepository.existsByDateDebutAndFiliere(edt.getDateDebut(),
                filiereRepository.getReferenceById(edt.getIdFiliere()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet emploi du temps est déjà établie pour " +
                    filiereRepository.getReferenceById(edt.getIdFiliere()).getNomFiliere() + " " +
                    filiereRepository.getReferenceById(edt.getIdFiliere()).getNiveau());

        } else {
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
     *
     * @param idEdt
     * @param emploiDuTempsDto
     * @return EmploiDuTempsDto
     */
    public EmploiDuTempsDto modifierEdt(final String idEdt, final EmploiDuTempsDto emploiDuTempsDto) {

        if (!emploiDuTempsRepository.existsById(idEdt) || emploiDuTempsDto.getStatutEdt() != EStatutEdt.BROUILLON) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossible de modifier ! ");
        } else {
            if (emploiDuTempsDto.getDatePublication() != null && emploiDuTempsDto.getDateDebut() != null &&
                    emploiDuTempsDto.getDatePublication().toLocalDate().isAfter(emploiDuTempsDto.getDateDebut())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La date de publication doit être antérieure " +
                        "à la date de début");

            } else if (emploiDuTempsRepository.existsByDateDebutAndFiliere(emploiDuTempsDto.getDateDebut(),
                    filiereRepository.getReferenceById(emploiDuTempsDto.getIdFiliere()))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet emploi du temps est déjà établie pour " +
                        filiereRepository.getReferenceById(emploiDuTempsDto.getIdFiliere()).getNomFiliere() + " " +
                        filiereRepository.getReferenceById(emploiDuTempsDto.getIdFiliere()).getNiveau());

            } else {

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
     *
     * @param idEdt
     */
    public void supprimerEdt(final String idEdt) {

        if (!emploiDuTempsRepository.existsById(idEdt)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet emploi du temps n'existe pas !");
        } else if (emploiDuTempsRepository.getReferenceById(idEdt).getStatutEdt() != EStatutEdt.BROUILLON) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CCet emploi du tempse ne peut pas " +
                    "être supprimé !");
        } else {
            emploiDuTempsRepository.deleteById(idEdt);
        }
    }


    /**
     * Lister les emplois du temps en fonction du nom de recherche de la filiere.
     *
     * @param nomFiliere
     * @return List<EmploiDuTempsDto>
     */
    public List<EmploiDuTempsDto> listEdtFiliere(final String nomFiliere) {
        if (nomFiliere == null || nomFiliere.isEmpty()) {
            return (emploiDuTempsRepository.findAll()).stream().map(this.mapper::maps).toList();
        } else {
            return (emploiDuTempsRepository.findByFiliereNomFiliereContainingIgnoreCase(nomFiliere)).stream()
                    .map(this.mapper::maps).toList();
        }
    }

    /**
     * Clore un edt.
     *
     * @param idEdt
     * @return ResponseStatusException
     */
    public ResponseStatusException cloreEdt(final String idEdt) {

        Optional<EmploiDuTemps> emploiDuTemps = emploiDuTempsRepository.findById(idEdt);

        if (emploiDuTemps.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette filière n'existe pas !");
        } else if (emploiDuTempsRepository.getReferenceById(idEdt).getStatutEdt() != EStatutEdt.PUBLIE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Cet emploi du temps ne peut pas être clos " +
                    "car il n'est pas encore publié ! ");
        } else {
            List<EmploiDuTempsDto> emploi = emploiDuTemps.stream().map(c -> {
                c.setStatutEdt(EStatutEdt.CLOS);
                this.emploiDuTempsRepository.save(c);
                return this.mapper.maps(c);
            }).toList();

            return new ResponseStatusException(HttpStatus.OK);
        }
    }


    /**
     ****************** Gestion des matieres ************************
     */



    /**
     * Créer une matiere.
     *
     * @param matiereDto
     * @return MatiereDto
     */
    public MatiereDto creerMatiere(final MatiereDto matiereDto) {

        String intituleNettoye = nettoyerChaineDeCaharactere(matiereDto.getIntitule());

        if (matiereRepository.existsByIntituleAndFiliereId(intituleNettoye, matiereDto.getIdFiliere())) {
            String nomFiliere = filiereRepository.findById(matiereDto.getIdFiliere())
                    .map(Filiere::getNomFiliere)
                    .orElse("inconnue");

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ce Module existe déjà pour la filière " + nomFiliere);
        } else {
            Matiere matiere = this.mapper.maps(matiereDto);
            matiere.setIntitule(intituleNettoye);
            matiere.setStatutMatiere(EStatutMatiere.NON_DEBUTE);
            return this.mapper.maps(this.matiereRepository.save(matiere));
        }
    }

    public static String nettoyerChaineDeCaharactere(String input) {
        // Supprimer les espaces
        String sansEspace = input.replaceAll("\\s+", " ");

        // Normaliser la chaîne pour enlever les accents
        String chaineNormalise = Normalizer.normalize(sansEspace, Normalizer.Form.NFD);
        // Supprimer les accents
        String resultat = chaineNormalise.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        return resultat;
    }


    /**
     * Modifier un module.
     *
     * @param idMatiere
     * @param matiereDto
     * @return MatiereDto
     */
    public MatiereDto modifierMatiere(final String idMatiere, final MatiereDto matiereDto) {

        Optional<Matiere> matiere = matiereRepository.findById(idMatiere);
        String intituleNettoye = nettoyerChaineDeCaharactere(matiereDto.getIntitule());

        if (matiere.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ce module n'existe pas");
        } else {

            if (matiereRepository.existsByIntituleAndFiliereId(intituleNettoye, matiereDto.getIdFiliere())) {
                String nomFiliere = filiereRepository.findById(matiereDto.getIdFiliere())
                        .map(Filiere::getNomFiliere)
                        .orElse("inconnue");

                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Ce Module existe déjà pour la filière " + nomFiliere);
            } else {
                List<MatiereDto> matiereDtos = matiere.stream().map(c -> {
                    c.setIntitule(intituleNettoye);
                    c.setVolumeHoraire(matiereDto.getVolumeHoraire());
                    c.setStatutMatiere(matiereDto.getStatutMatiere());
                    c.setSemestre(matiereDto.getSemestre());
                    c.setFiliere(this.filiereRepository.getReferenceById(matiereDto.getIdFiliere()));
                    return this.mapper.maps(this.matiereRepository.save(c));

                }).toList();
            }

        }

        return matiereDto;
    }

    /**
     * Supprimer une matiere.
     *
     * @param idMatiere
     * @return ResponseStatusException
     */
    public ResponseStatusException supprimerMatiere(final String idMatiere) {

        Optional<Matiere> matiere = matiereRepository.findById(idMatiere);
        if (matiere.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ce module n'existe pas");
        } else {
            this.matiereRepository.deleteById(idMatiere);
            return new ResponseStatusException(HttpStatus.OK);
        }
    }

    /**
     * Lister les matieres par filière.
     *
     * @param idFiliere
     * @return List<MatiereDto>
     */
    public List<MatiereDto> listMatiereParFiliere(final String idFiliere) {

        if (!filiereRepository.existsById(idFiliere)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette filière n'existe pas");
        } else {
            List<Matiere> matiereDtos = this.matiereRepository.findByFiliereId(idFiliere);
            return matiereDtos.stream().map(this.mapper::maps).toList();
        }
    }

    /**
     * Lister tous les modules
     *
     * @return List<MatiereDto>
     */

    public List<MatiereDto> listAllMatieres(){
        List<Matiere> matieres = matiereRepository.findAll();
        return matieres.stream()
                .map(mapper::maps)
                .collect(Collectors.toList());
    }


    /**
     * ******************** Gestion des Indisponibilites ******************
     */

    /**
     * Créer une indisponibilité.
     *
     * @param indisponibiliteDto
     * @return IndisponibiliteDto
     */
    public IndisponibiliteDto createIndisponibilite(IndisponibiliteDto indisponibiliteDto) {
        Enseignant enseignant = enseignantRepository.findById(indisponibiliteDto.getIdEnseignant())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enseignant non trouvé avec l'Id: "
                        + indisponibiliteDto.getIdEnseignant()));

        if (this.indisponibiliteRepository.existsByDateDebutAndDateFinAndEnseignantId(indisponibiliteDto.getDateDebut(),
                indisponibiliteDto.getDateFin(), indisponibiliteDto.getIdEnseignant())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet indisponibilité existe déjà pour cet enseignent");

        } else if (indisponibiliteDto.getDateDebut() != null && indisponibiliteDto.getDateFin() != null &&
                indisponibiliteDto.getDateDebut().isAfter(indisponibiliteDto.getDateFin())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La date de début doit être antérieure " +
                    "à la date de fin");
        } else {

            Indisponibilite indisponibilite = mapper.maps(indisponibiliteDto);
            Indisponibilite savedIndisponibilite = indisponibiliteRepository.save(indisponibilite);
            return mapper.maps(savedIndisponibilite);
        }


    }

    /**
     * Récupérer une indisponibilité par son ID.
     *
     * @param id L'identifiant de l'indisponibilité
     * @return IndisponibiliteDto
     */
    public IndisponibiliteDto getIndisponibiliteById(String id) {
        Indisponibilite indisponibilite = indisponibiliteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Indisponibilité non trouvée avec l'Id: " + id));
        return mapper.maps(indisponibilite);
    }

    /**
     * Récupérer toutes les indisponibilités.
     *
     * @return List<IndisponibiliteDto>
     */
    public List<IndisponibiliteDto> getAllIndisponibilites() {
        List<Indisponibilite> indisponibilites = indisponibiliteRepository.findAll();
        return indisponibilites.stream()
                .map(mapper::maps)
                .collect(Collectors.toList());
    }

    /**
     * Mettre à jour une indisponibilité.
     *
     * @param indisponibiliteDto Les nouvelles informations de l'indisponibilité
     * @return IndisponibiliteDto
     */
    public IndisponibiliteDto updateIndisponibilite(final String idIndisponibilite, final IndisponibiliteDto indisponibiliteDto) {

        Indisponibilite existingIndisponibilite = indisponibiliteRepository.findById(idIndisponibilite)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Indisponibilité non trouvée avec l'Id: " + idIndisponibilite));

        Enseignant enseignant = enseignantRepository.findById(indisponibiliteDto.getIdEnseignant())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enseignant non trouvé avec l'Id: " +
                        indisponibiliteDto.getIdEnseignant()));


        if (this.indisponibiliteRepository.existsByDateDebutAndDateFinAndEnseignantId(indisponibiliteDto.getDateDebut(),
                indisponibiliteDto.getDateFin(), indisponibiliteDto.getIdEnseignant())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet indisponibilité existe déjà pour cet enseignent");

        } else if (indisponibiliteDto.getDateDebut() != null && indisponibiliteDto.getDateFin() != null &&
                indisponibiliteDto.getDateDebut().isAfter(indisponibiliteDto.getDateFin())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La date de début doit être antérieure " +
                    "à la date de fin");
        } else {
            Indisponibilite updatedIndisponibilite = this.indisponibiliteRepository.getReferenceById(idIndisponibilite);

            updatedIndisponibilite.setDateDebut(indisponibiliteDto.getDateDebut());
            updatedIndisponibilite.setDateFin(indisponibiliteDto.getDateFin());
            updatedIndisponibilite.setMotif(indisponibiliteDto.getMotif());
            updatedIndisponibilite.setEnseignant(enseignantRepository.getReferenceById(indisponibiliteDto.getIdEnseignant()));
            return mapper.maps(this.indisponibiliteRepository.save(updatedIndisponibilite));
        }

    }

    /**
     * Supprimer une indisponibilité.
     *
     * @param id L'identifiant de l'indisponibilité à supprimer
     */
    public void deleteIndisponibilite(String id) {
        Indisponibilite indisponibilite = indisponibiliteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Indisponibilité non trouvée avec l'Id: " + id));
        indisponibiliteRepository.delete(indisponibilite);
    }

    /**
     * creer des utilisateur ficifs pour les test.
     */

    public EnseignantDto creerEnseignant(EnseignantDto enseignantDto) {
        Enseignant enseignant = this.mapper.maps(enseignantDto);
        return this.mapper.maps(this.enseignantRepository.save(enseignant));
    }


    /**
     ***************** Gestions des cours *************************
     */

    /**
     * Creer un cours.
     *
     * @param coursDto
     * @return CoursDto
     */
    public CoursDto creerCours(final CoursDto coursDto) {

        if (this.coursRepository.existsByDateAndCrenauAndMatiereId(coursDto.getDate(), coursDto.getCrenau(),
                coursDto.getIdMatiere())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ce cours est déjà programmé pour le " + coursDto.getDate() + " pour " + coursDto.getCrenau().getPlageHoraire());

        } else if (this.coursRepository.existsByDateAndCrenauAndSalleId(coursDto.getDate(), coursDto.getCrenau(),
                coursDto.getIdSalle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Salle déjà occupée. Veuillez changer de salle !");

        } else if (this.devoirRepository.existsByDateAndCrenau(coursDto.getDate(), coursDto.getCrenau())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Un devoir est déjà programmé pour le " + coursDto.getDate() + " pour " + coursDto.getCrenau().getPlageHoraire());

        } else {
            EmploiDuTemps emploiDuTemps = emploiDuTempsRepository.getReferenceById(coursDto.getIdEmploiDuTemps());
            if (coursDto.getDate().isBefore(emploiDuTemps.getDateDebut()) ||
                    coursDto.getDate().isAfter(emploiDuTemps.getDateFin())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La date du cours n'est pas comprise dans l'emploi du temps correspondant");
            } else {
                Cours cours = this.mapper.maps(coursDto);
                cours.setStatutCours(EStatutCours.PLANIFIE);
                cours.setDisponibiliteProf(EDisponibiliteProf.DISPONIBLE);
                return this.mapper.maps(this.coursRepository.save(cours));
            }
        }
    }

    /**
     * Modifier un cours.
     * @param idCours
     * @param coursDto
     * @return CoursDto
     */
    public CoursDto modifierCours(final String idCours, final CoursDto coursDto) {

        Cours cours = this.coursRepository.findById(idCours)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cours non trouvé"));

        if (this.coursRepository.existsByDateAndCrenauAndMatiereId(coursDto.getDate(), coursDto.getCrenau(),
                coursDto.getIdMatiere())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ce cours est déjà programmé pour le " + coursDto.getDate() + " pour " + coursDto.getCrenau().getPlageHoraire());
        } else if (this.coursRepository.existsByDateAndCrenauAndSalleId(coursDto.getDate(), coursDto.getCrenau(),
                coursDto.getIdSalle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Salle déjà occupée. Veuillez changer de salle !");
        } else if (this.devoirRepository.existsByDateAndCrenau(coursDto.getDate(), coursDto.getCrenau())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Un devoir est déjà programmé pour le " + coursDto.getDate() + " pour " + coursDto.getCrenau().getPlageHoraire());
        } else {
            EmploiDuTemps emploiDuTemps = emploiDuTempsRepository.getReferenceById(coursDto.getIdEmploiDuTemps());

            if (coursDto.getDate().isBefore(emploiDuTemps.getDateDebut()) ||
                    coursDto.getDate().isAfter(emploiDuTemps.getDateFin())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La date du cours n'est pas comprise dans l'emploi du temps correspondant");
            } else {
                cours.setDate(coursDto.getDate());
                cours.setCrenau(coursDto.getCrenau());
                cours.setId(idCours);
                cours.setMatiere(this.matiereRepository.getReferenceById(coursDto.getIdMatiere()));
                cours.setEmploiDuTemps(this.emploiDuTempsRepository.getReferenceById(coursDto.getIdEmploiDuTemps()));
                cours.setStatutCours(EStatutCours.PLANIFIE);
                cours.setDisponibiliteProf(EDisponibiliteProf.DISPONIBLE);

                return this.mapper.maps(this.coursRepository.save(cours));
            }

        }
    }


    /**
     * Supprimer cours.
     * @param idCours
     * @return ResponseStatusException
     */
    public ResponseStatusException supprimerCours(final String idCours) {

        Optional<Cours> cours = coursRepository.findById(idCours);
        if (cours.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ce module n'existe pas");
        } else {
            this.coursRepository.deleteById(idCours);
            return new ResponseStatusException(HttpStatus.OK);
        }
    }


    /**
     * Lister les cours par emploi du temps et par filiere.
     * @param idEmploiDuTemps
     * @param idFiliere
     * @return List<CoursDto>
     */
    public List<CoursDto> listerCoursParEmploiDuTempsEtFiliere(String idEmploiDuTemps, String idFiliere) {

        List<Cours> coursList = this.coursRepository.findByEmploiDuTempsIdAndMatiereFiliereId(idEmploiDuTemps, idFiliere);

        return coursList.stream()
                .map(this.mapper::maps)
                .collect(Collectors.toList());
    }

    /**
     * Mettre le statut d'un cours à FAIT.
     * @param idCours
     * @return CoursDto
     */
    public CoursDto changerStatutACoursFait(final String idCours) {

        Cours cours = this.coursRepository.findById(idCours)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cours non trouvé"));

            cours.setStatutCours(EStatutCours.FAIT);
            return this.mapper.maps(this.coursRepository.save(cours));

    }


    /**
     * Marquer indisponibilite.
     * @param idCours
     * @return ResponseStatusException
     */
    public ResponseStatusException marquerIndisponibilite(final String idCours) {

        Cours cours = this.coursRepository.findById(idCours)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cours non trouvé"));

        cours.setDisponibiliteProf(EDisponibiliteProf.INDISPONIBLE);
        cours.setStatutCours(EStatutCours.ANNULE);
        this.coursRepository.save(cours);

        return new ResponseStatusException(HttpStatus.OK);

    }

    /**
     *************** Gestions des devoirs ***********************
     */

    public DevoirDto creerDevoir(DevoirDto devoirDto){

        if (this.devoirRepository.existsByDateAndCrenauAndDureeAndMatiereId(devoirDto.getDate(), devoirDto.getCrenau(),
                devoirDto.getDuree() ,devoirDto.getIdMatiere())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ce devoir est déjà programmé pour le " + devoirDto.getDate() + " pour " + devoirDto.getCrenau().getPlageHoraire());

        } else if (this.devoirRepository.existsByDateAndCrenauAndSalleId(devoirDto.getDate(), devoirDto.getCrenau(),
                devoirDto.getIdSalle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Salle déjà occupée. Veuillez changer de salle !");

        } else if (this.coursRepository.existsByDateAndCrenau(devoirDto.getDate(), devoirDto.getCrenau())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Un Cours est déjà programmé pour le " + devoirDto.getDate() + " pour " + devoirDto.getCrenau().getPlageHoraire());

        } else {
            EmploiDuTemps emploiDuTemps = emploiDuTempsRepository.getReferenceById(devoirDto.getIdEmploiDuTemps());
            if (devoirDto.getDate().isBefore(emploiDuTemps.getDateDebut()) ||
                    devoirDto.getDate().isAfter(emploiDuTemps.getDateFin())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La date du devoir n'est pas comprise dans l'emploi du temps correspondant");
            } else {
                Devoir devoir = this.mapper.maps(devoirDto);
                devoir.setStatutDevoir(ESatutDevoir.NON_FAIT);
                return this.mapper.maps(this.devoirRepository.save(devoir));
            }
        }

    }

    /**
     * Créer un devoir.
     * @param idDevoir
     * @param devoirDto
     * @return DevoirDto
     */
    public DevoirDto modifierDevoir(String idDevoir, DevoirDto devoirDto){

        Devoir devoir = this.devoirRepository.findById(idDevoir)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cours non trouvé"));

        if (this.devoirRepository.existsByDateAndCrenauAndDureeAndMatiereId(devoirDto.getDate(), devoirDto.getCrenau(),
                devoirDto.getDuree(), devoirDto.getIdMatiere())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ce devoir est déjà programmé pour le " + devoirDto.getDate() + " pour " + devoirDto.getCrenau().getPlageHoraire());

        } else if (this.devoirRepository.existsByDateAndCrenauAndSalleId(devoirDto.getDate(), devoirDto.getCrenau(),
                devoirDto.getIdSalle())) {
            devoir.setDuree(devoirDto.getDuree());
            this.devoirRepository.save(devoir);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Salle déjà occupée pour un devoir. Veuillez changer de salle. Cependant la durée du devoir a été mise à jour !");


        } else if (this.coursRepository.existsByDateAndCrenau(devoirDto.getDate(), devoirDto.getCrenau())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Un Cours est déjà programmé pour le " + devoirDto.getDate() + " pour " + devoirDto.getCrenau().getPlageHoraire());

        } else {
            EmploiDuTemps emploiDuTemps = emploiDuTempsRepository.getReferenceById(devoirDto.getIdEmploiDuTemps());

            if (devoirDto.getDate().isBefore(emploiDuTemps.getDateDebut()) ||
                    devoirDto.getDate().isAfter(emploiDuTemps.getDateFin())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La date du devoir n'est pas comprise dans l'emploi du temps correspondant");
            } else {
                devoir.setDate(devoirDto.getDate());
                devoir.setCrenau(devoirDto.getCrenau());
                devoir.setId(idDevoir);
                devoir.setDuree(devoirDto.getDuree());
                devoir.setMatiere(this.matiereRepository.getReferenceById(devoirDto.getIdMatiere()));
                devoir.setEmploiDuTemps(this.emploiDuTempsRepository.getReferenceById(devoirDto.getIdEmploiDuTemps()));
                devoir.setStatutDevoir(ESatutDevoir.NON_FAIT);

                return this.mapper.maps(this.devoirRepository.save(devoir));
            }
        }

    }

    /**
     * Supprimer devoir.
     * @param idDevoir
     * @return ResponseStatusException
     */
    public ResponseStatusException supprimerDevoir(final String idDevoir) {

        Optional<Devoir> devoir = devoirRepository.findById(idDevoir);
        if (devoir.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ce module n'existe pas");
        } else {
            this.devoirRepository.deleteById(idDevoir);
            return new ResponseStatusException(HttpStatus.OK);
        }
    }

    /**
     * Lister les devoirs par emploi du temps.
     * @param idEmploiDuTemps
     * @param idFiliere
     * @return List<CoursDto>
     */
    public List<DevoirDto> listerDevoirParEmploiDuTempsEtFiliere(String idEmploiDuTemps, String idFiliere) {

        List<Devoir> devoirList = this.devoirRepository.findByEmploiDuTempsIdAndMatiereFiliereId(idEmploiDuTemps, idFiliere);

        return devoirList.stream().map(this.mapper::maps).toList();
    }

    /**
     * Annuler devoir.
     * @param idDevoir
     * @return DevoirDto
     */
    public DevoirDto annulerDevoir(final String idDevoir) {

        Devoir devoir = this.devoirRepository.findById(idDevoir)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cours non trouvé"));

        devoir.setStatutDevoir(ESatutDevoir.ANNULE);
        return this.mapper.maps(this.devoirRepository.save(devoir));

    }

}
