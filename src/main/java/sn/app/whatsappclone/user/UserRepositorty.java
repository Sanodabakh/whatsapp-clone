package sn.app.whatsappclone.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositorty extends JpaRepository<User, Long> {


    @Query(name = UserConstants.FIND_USER_BY_EMAIL)
    Optional<User> findByEmail(@Param("email") String userEmail) ;

    @Query(name = UserConstants.FIND_USER_BY_PUBLIC_ID)
    Optional<User> findUserByPublicId(@Param("publicId") String publicId);

    @Query(name = UserConstants.FIND_ALL_USER_EXCEPT_SELF)
    List<User> findAllUserExceptSelf(@Param("publicId") String senderId);
}
