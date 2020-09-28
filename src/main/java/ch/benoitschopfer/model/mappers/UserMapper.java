package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.DTO.UserAddOrUpdate;
import ch.benoitschopfer.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
  User userAddOrUpdateToUser(UserAddOrUpdate userAddOrUpdate);

  UserAddOrUpdate userToUserAddOrUpdate(User userToAdd);
}
