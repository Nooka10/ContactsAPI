package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.entity.User;
import ch.benoitschopfer.model.other.LoginRequest;
import ch.benoitschopfer.model.other.RegisterRequest;
import ch.benoitschopfer.model.other.UserUpdate;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-30T23:39:22+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.8 (AdoptOpenJDK)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User loginRequestToUser(LoginRequest loginRequest) {
        if ( loginRequest == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( loginRequest.getEmail() );
        user.setPassword( loginRequest.getPassword() );

        return user;
    }

    @Override
    public User registerRequestToUser(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( registerRequest.getEmail() );
        user.setPassword( registerRequest.getPassword() );

        return user;
    }

    @Override
    public User userUpdateToUser(UserUpdate userUpdate) {
        if ( userUpdate == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( userUpdate.getEmail() );
        user.setPassword( userUpdate.getPassword() );

        return user;
    }

    @Override
    public UserUpdate userToUserUpdate(User userToAdd) {
        if ( userToAdd == null ) {
            return null;
        }

        UserUpdate userUpdate = new UserUpdate();

        userUpdate.setEmail( userToAdd.getEmail() );
        userUpdate.setPassword( userToAdd.getPassword() );

        return userUpdate;
    }
}
