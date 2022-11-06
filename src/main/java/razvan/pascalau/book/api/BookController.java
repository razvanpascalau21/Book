package razvan.pascalau.book.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {
private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    private List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping(path = "{bookId}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    private Book getBook(@PathVariable("bookId") Long id){
        return bookService.getBook(id);
    }

    @PostMapping
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    private void addBook(@RequestBody Book book){
        bookService.addNewBook(book);
    }


    @DeleteMapping(path = "{bookId}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    private void deleteById(@PathVariable("bookId") Long id){
        bookService.deleteBook(id);
    }

    @DeleteMapping()
    @RequestMapping(path = "delete")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    private void deleteAllBook(){
        bookService.deleteAllBook();
    }

    @PutMapping(path = "{bookId}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    private void update(@PathVariable ("bookId") Long id,@RequestParam(required = false) String name,
                        @RequestParam(required = false,defaultValue ="0") Integer pages){
        bookService.updateBook(id,name,pages);
    }

    @RequestMapping("author")
    @PostMapping
    private void addAuthorToBook(@RequestBody AuthorToBookForm authorToBookForm){
        bookService.addAuthorToBook(authorToBookForm.getBook(),authorToBookForm.getAuthor());
    }

}
