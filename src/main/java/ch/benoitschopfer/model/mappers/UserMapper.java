package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.DTO.UserToAdd;
import ch.benoitschopfer.model.DTO.UserToUpdate;
import ch.benoitschopfer.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
  User userToAddToUser(UserToAdd userToAdd);

  UserToAdd userToUserToAdd(User userToAdd);

  User userToUpdateToUser(UserToUpdate userToAdd);

  UserToUpdate userToUserToUpdate(User user);

}
