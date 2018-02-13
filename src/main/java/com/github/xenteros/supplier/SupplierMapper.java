package com.github.xenteros.supplier;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {


    @Mappings({
            @Mapping(source = "product.name", target = "productName")
    })
    SupplierDTO toSupplierDTO(Supplier supplier);

    List<SupplierDTO> toSupplierDTO(Collection<Supplier> suppliers);
}
