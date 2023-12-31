package de.ait.task_05.security.details;

import de.ait.task_05.models.User;
import de.ait.task_05.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userOptional = usersRepository.findByEmail(email); // находим пользователя по email-у

        if (userOptional.isPresent()) { // если пользователь найден
            User user = userOptional.get(); // получаем непосредственно объект пользователя
            AuthenticatedUser authenticatedUser = new AuthenticatedUser(user); // кладем пользователя в объект для Spring Security
            return authenticatedUser; // возвращаем результат работы метода - пользователь из базы данных, адаптированный для Spring Security
        } else {
            // если пользователя не нашли - возвращаем ошибку
            throw new UsernameNotFoundException(("User with email <" + email + "> not found"));
        }


//        User user = usersRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User with email <" + email + "> not found"));
//
//        return new AuthenticatedUser(user);
    }
}
