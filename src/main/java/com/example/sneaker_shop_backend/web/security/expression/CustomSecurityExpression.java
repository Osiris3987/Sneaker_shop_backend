package com.example.sneaker_shop_backend.web.security.expression;

import com.example.sneaker_shop_backend.domain.entity.user.Role;
import com.example.sneaker_shop_backend.service.UserService;
import com.example.sneaker_shop_backend.web.security.JwtEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("customSecurityExpression")
@RequiredArgsConstructor
public class CustomSecurityExpression {

    private final UserService userService;
    public boolean canAccessUser(final Long id) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();

        return userId.equals(id) || hasAnyRole(authentication, Role.ADMIN);
    }

    public boolean canAccessCart(final Long cartId){
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();
        return userService.isCartOwner(userId, cartId);
    }

    private boolean hasAnyRole(final Authentication authentication,
                               final Role... roles) {
        for (Role role : roles) {
            SimpleGrantedAuthority authority
                    = new SimpleGrantedAuthority(role.name());
            if (authentication.getAuthorities().contains(authority)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAdminRole(){
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return hasAnyRole(authentication, Role.ADMIN);
    }
}
