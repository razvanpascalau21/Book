package razvan.pascalau.book.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import razvan.pascalau.book.api.Book;

import java.util.List;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }

    @GetMapping(path = "{authorId}")
    public Author getAuthor(@PathVariable("authorId") Long id){
        return authorService.getAuthor(id);
    }

    @PostMapping()
    private void addBook(@RequestBody Author author){
        authorService.addNewAuthor(author);
    }

    @DeleteMapping(path = "{authorId}")
    private void deleteById(@PathVariable("authorId") Long id){
        authorService.deleteAuthor(id);
    }

    @PutMapping(path = "{authorId}")
    private void update(@PathVariable ("authorId") Long id,@RequestParam String author){
        authorService.updateAuthor(id,author);
    }

}
