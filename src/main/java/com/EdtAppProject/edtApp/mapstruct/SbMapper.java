package com.EdtAppProject.edtApp.mapstruct;

import com.EdtAppProject.edtApp.dto.*;
import com.EdtAppProject.edtApp.entite.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SbMapper {

    @Mappings({})
    SalleDto maps(Salle entity);

    @InheritInverseConfiguration
    Salle maps(SalleDto dto);

    @Mappings({})
    FiliereDto maps(Filiere entity);

    @InheritInverseConfiguration
    Filiere maps(FiliereDto dto);

    @Mappings({
            @Mapping(target = "idFiliere", source = "filiere.id")
    })
    EmploiDuTempsDto maps(EmploiDuTemps entity);

    @InheritInverseConfiguration
    EmploiDuTemps maps(EmploiDuTempsDto dto);

    @Mappings({
            @Mapping(target = "idFiliere", source = "filiere.id")
    })
    MatiereDto maps(Matiere entity);

    @InheritInverseConfiguration
    Matiere maps(MatiereDto dto);

    @Mappings({
            @Mapping(target = "idEnseignant", source = "enseignant.id")
    })
    IndisponibiliteDto maps(Indisponibilite entity);

    @InheritInverseConfiguration
    Indisponibilite maps(IndisponibiliteDto dto);

}
