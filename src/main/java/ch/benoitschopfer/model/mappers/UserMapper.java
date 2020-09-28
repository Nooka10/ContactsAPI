package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.DTO.UserToAddOrUpdate;
import ch.benoitschopfer.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
  User userToAddOrUpdateToUser(UserToAddOrUpdate userToAddOrUpdate);

  UserToAddOrUpdate userToUserToAddOrUpdate(User userToAdd);
}
