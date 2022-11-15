package razvan.pascalau.book.api;

import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import razvan.pascalau.book.author.Author;
import razvan.pascalau.book.author.AuthorRepository;

import java.util.HashSet;
import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner commandLineRunner10(BookRepository bookRepository, AuthorRepository authorRepository, BookService bookService){
        return args -> {
            Book book=new Book("Amintiri din copilarie",120,new HashSet<>());
            Book book2=new Book("Poezi",100,new HashSet<>());

            Author author=new Author("Ion Creanga");
            Author author2=new Author("Mihai Eminescu");

            book.setAuthors(Set.of(author));
            book2.setAuthors(Set.of(author2,author));

            bookRepository.saveAll(List.of(book2,book));
        };
    }
}
