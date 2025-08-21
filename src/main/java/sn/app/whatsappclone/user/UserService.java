package sn.app.whatsappclone.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositorty userRepositorty;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsersExceptSelf(Authentication connectedUser){
        return userRepositorty.findAllUserExceptSelf(connectedUser.getName())
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }
}
