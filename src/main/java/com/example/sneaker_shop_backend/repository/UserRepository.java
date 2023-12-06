package com.example.sneaker_shop_backend.repository;

import com.example.sneaker_shop_backend.domain.entity.user.User;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(
            value = """
             SELECT exists(
                           SELECT 1
                           FROM users
                           WHERE id = :userId AND cart_id = :cartId
                          )
            """, nativeQuery = true)
    boolean isCartOwner(@Param("userId") Long userId,
                        @Param("cartId") Long cartId);
    @Timed("saveUserInDb")
    User save(User user);
}
