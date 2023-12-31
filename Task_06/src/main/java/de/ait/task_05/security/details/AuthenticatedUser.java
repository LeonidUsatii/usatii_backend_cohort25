package de.ait.task_05.security.details;

import de.ait.task_05.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AuthenticatedUser implements UserDetails {

    private final User user;

    public AuthenticatedUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //в нашем случае права пользователя определяются его ролью в приложении (например, USER, ADMIN, MANAGER, TEACHER, STUDENT и т.д.)
        //нужно взять роль пользователя как обычную строку
        String role = user.getRole().toString();
        // оборачиваем ее в объект Spring Security
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        // Spring Security предполагает, что у пользователя может быть много прав (много ролей)
        // поэтому он просит коллекцию
        List<GrantedAuthority> authorities = new ArrayList<>(); // создаем список
        authorities.add(authority); // кладем туда нашу роль
        return authorities; // возвращаем как результат

        //return Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()));

    }

    @Override
    public String getPassword() { // в качестве пароля берем наш хэш
        return user.getPassword();
    }

    @Override
    public String getUsername() { // в качестве имени пользователя в нашем случае выступает email
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
