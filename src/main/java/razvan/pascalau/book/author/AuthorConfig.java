package razvan.pascalau.book.author;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import razvan.pascalau.book.api.Book;
import razvan.pascalau.book.api.BookRepository;

import java.util.List;

@Configuration
public class AuthorConfig {
    @Bean
    CommandLineRunner commandLineRunner2(AuthorRepository authorRepository){
        return args -> {
            Author author=new Author("Ion Creanga");
            Author author2=new Author("Mihai Eminescu");
            authorRepository.saveAll(List.of(author,author2));
        };
    }
}
