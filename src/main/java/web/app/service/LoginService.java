package web.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.app.entity.User;
import web.app.repos.UserRepo;
import web.app.security.AuthenticatedUser;

import java.util.Optional;

@Service
public class LoginService  implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("USER: LOGIN = "+username+ " "+  userRepo.existsById(username));


        Optional<User> optionalUser = userRepo.findById(username);

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        User user =optionalUser.get();
        System.out.println("USER: LOGIN = "+user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return new AuthenticatedUser(user);
    }

    public String getRoleByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> optionalUser = userRepo.findById(username);

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        User user =optionalUser.get();
        return user.getRole();
    }
}
