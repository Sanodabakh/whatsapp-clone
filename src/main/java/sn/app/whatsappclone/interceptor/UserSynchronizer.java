package sn.app.whatsappclone.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import sn.app.whatsappclone.user.User;
import sn.app.whatsappclone.user.UserMapper;
import sn.app.whatsappclone.user.UserRepositorty;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSynchronizer {

   private final UserRepositorty userRepositorty;
   private final UserMapper userMapper;

    public void synchronizeWithIdp(Jwt token) {
        log.info("Synchronizing user with idp");
        getUserEmail(token).ifPresent(userEmail -> {
            log.info("Synchronizing user having email: {}", userEmail);
            //Optional<User> optUser = userRepositorty.findByEmail(userEmail);
            User user = userMapper.fromTokenAttributes(token.getClaims());
            //optUser.ifPresent(value -> user.setId(optUser.get().getId()));

            userRepositorty.save(user);
        });
    }

    private Optional<String> getUserEmail(Jwt token) {
        Map<String, Object > attributes = token.getClaims();
        if (attributes.containsKey("email")) {
            return Optional.of(attributes.get("email").toString());
        }
        return Optional.empty();
    }
}
