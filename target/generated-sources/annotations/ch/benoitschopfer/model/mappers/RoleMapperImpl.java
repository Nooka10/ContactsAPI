package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.entity.Role;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-30T23:39:22+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.8 (AdoptOpenJDK)"
)
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role stringToRole(String name) {
        if ( name == null ) {
            return null;
        }

        Role role = new Role();

        role.setName( name );

        return role;
    }

    @Override
    public String roleToString(Role role) {
        if ( role == null ) {
            return null;
        }

        String string = new String();

        return string;
    }
}
