package razvan.pascalau.book.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import razvan.pascalau.book.user.User;
import razvan.pascalau.book.user.UserRepository;

import java.util.Objects;
import java.util.Optional;


public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(username);
       // user.orElseThrow(()->new UsernameNotFoundException("User"+ username+" not found!"));
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("User"+ username+" not found!");
        }
        return new MyUserDetails(user);
    }
}
