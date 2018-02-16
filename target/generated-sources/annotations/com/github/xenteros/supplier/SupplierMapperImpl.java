package com.github.xenteros.supplier;

import com.github.xenteros.product.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-02-16T11:48:15+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public SupplierDTO toSupplierDTO(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDTO supplierDTO = new SupplierDTO();

        String name = supplierProductName( supplier );
        if ( name != null ) {
            supplierDTO.setProductName( name );
        }
        supplierDTO.setId( supplier.getId() );
        supplierDTO.setName( supplier.getName() );

        return supplierDTO;
    }

    @Override
    public List<SupplierDTO> toSupplierDTO(Collection<Supplier> suppliers) {
        if ( suppliers == null ) {
            return null;
        }

        List<SupplierDTO> list = new ArrayList<SupplierDTO>( suppliers.size() );
        for ( Supplier supplier : suppliers ) {
            list.add( toSupplierDTO( supplier ) );
        }

        return list;
    }

    private String supplierProductName(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }
        Product product = supplier.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
