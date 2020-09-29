package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
  Role stringToRole(String name);

  String roleToString(Role role);
}
