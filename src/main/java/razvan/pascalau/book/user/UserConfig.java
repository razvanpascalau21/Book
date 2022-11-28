package razvan.pascalau.book.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import razvan.pascalau.book.role.Role;
import razvan.pascalau.book.role.RoleRepository;
import razvan.pascalau.book.role.RoleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class UserConfig {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Bean
    CommandLineRunner commandLineRunner4(UserRepository userRepository){
        return args -> {
            User user=new User("Ion","Ion","ion@yahoo.com",encoder.encode("password"),new HashSet<>());
            User user2=new User("Iona","Ioana","iona@yahoo.com",encoder.encode("password"),new HashSet<>());
            Role role=new Role("ROLE_ADMIN");
            Role role2=new Role("ROLE_USER");
            user.setRoles(Set.of(role));
            user2.setRoles(Set.of(role2));
            userRepository.saveAll(List.of(user,user2));
        };
    }
}
