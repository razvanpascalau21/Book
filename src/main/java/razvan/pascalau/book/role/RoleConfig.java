package razvan.pascalau.book.role;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import razvan.pascalau.book.author.Author;
import razvan.pascalau.book.author.AuthorRepository;

import java.util.List;
@Configuration
public class RoleConfig {
    @Bean
    CommandLineRunner commandLineRunner3(RoleRepository roleRepository){
        return args -> {
            Role role=new Role("ADMIN");
            Role role2=new Role("USER");
            roleRepository.saveAll(List.of(role,role2));
        };
    }
}
