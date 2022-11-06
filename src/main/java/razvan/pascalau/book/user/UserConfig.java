package razvan.pascalau.book.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import razvan.pascalau.book.role.Role;
import razvan.pascalau.book.role.RoleRepository;
import razvan.pascalau.book.role.RoleService;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner4(UserService userService, RoleService roleService){
        return args -> {
            User user=new User("Ion","Ion","ion@yahoo.com","password");
            User user2=new User("Iona","Ioana","iona@yahoo.com","password");
            userService.saveUser(user);
            userService.saveUser(user2);
            userService.addRoleToUser(user,roleService.role(1));
            userService.addRoleToUser(user2,roleService.role(2));
        };
    }
}
