package razvan.pascalau.book.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import razvan.pascalau.book.author.Author;
import razvan.pascalau.book.author.AuthorRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired//this.bookRepository=new BookRepository
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public void addNewBook(Book book){
        Optional<Book> bookByName=bookRepository.findByBookName(book.getBookName());
        if(bookByName.isPresent()){
            throw new IllegalStateException("Book with name "+book.getBookName()+ "exist!");
        }
        bookRepository.save(book);
    }

    public Book getBook(long id){
        //boolean exists = bookRepository.existsById(id);
        //if(!exists){
        //    throw new IllegalStateException("book with id="+id+" doesn't exist!");
        //}
        //return bookRepository.findById(id);
        return bookRepository.findById(id).orElseThrow(()->new IllegalStateException("book with id="+id+" doesn't exist!"));
    }

    @Transactional
    public void updateBook(long id,String name,Integer pages){
        Book book=bookRepository.findById(id).orElseThrow(()->new IllegalStateException("book with id="+id+" doesn't exist!"));
        if(name!=null&&name.length()>0&& !Objects.equals(book.getBookName(),name)){
            Optional<Book> bookByName=bookRepository.findByBookName(book.getBookName());
            if(bookByName.isPresent()){
                throw new IllegalStateException("Book with name "+book.getBookName()+ "exist!");
            }
            book.setBookName(name);
        }

        if(pages>0&& !Objects.equals(book.getPages(),pages)){
            book.setPages(pages);
        }
    }

    public void deleteBook(long id){
        if(!bookRepository.existsById(id)){
            throw new IllegalStateException("book with id="+id+" doesn't exist!");
        }
        bookRepository.deleteById(id);
    }

    public void deleteAllBook(){
        bookRepository.deleteAll();
    }

    public void addAuthorToBook(Book bookName, Author authorName){
        Optional<Book> book = bookRepository.findByBookName(bookName.getBookName());
        Author author = authorRepository.findByAuthor(authorName.getAuthor());
        book.get().getAuthors().add(author);
    }
}
