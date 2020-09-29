package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.other.LoginRequest;
import ch.benoitschopfer.model.other.RegisterRequest;
import ch.benoitschopfer.model.other.UserUpdate;
import ch.benoitschopfer.model.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
  User loginRequestToUser(LoginRequest loginRequest);

  User registerRequestToUser(RegisterRequest registerRequest);

  User userUpdateToUser(UserUpdate userUpdate);

  UserUpdate userToUserUpdate(User userToAdd);
}
