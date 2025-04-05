package com.EdtAppProject.edtApp.mapstruct;

import com.EdtAppProject.edtApp.dto.FiliereDto;
import com.EdtAppProject.edtApp.dto.SalleDto;
import com.EdtAppProject.edtApp.entite.Filiere;
import com.EdtAppProject.edtApp.entite.Salle;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
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


}
