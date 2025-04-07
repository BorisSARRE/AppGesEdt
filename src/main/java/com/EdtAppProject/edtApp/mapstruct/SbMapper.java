package com.EdtAppProject.edtApp.mapstruct;

import com.EdtAppProject.edtApp.dto.EmploiDuTempsDto;
import com.EdtAppProject.edtApp.dto.FiliereDto;
import com.EdtAppProject.edtApp.dto.MatiereDto;
import com.EdtAppProject.edtApp.dto.SalleDto;
import com.EdtAppProject.edtApp.entite.EmploiDuTemps;
import com.EdtAppProject.edtApp.entite.Filiere;
import com.EdtAppProject.edtApp.entite.Matiere;
import com.EdtAppProject.edtApp.entite.Salle;
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

}
