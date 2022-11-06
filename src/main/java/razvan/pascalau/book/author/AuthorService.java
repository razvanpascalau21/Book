package razvan.pascalau.book.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import razvan.pascalau.book.api.Book;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public void addNewAuthor(Author author){
        Author authorByName=authorRepository.findByAuthor(author.getAuthor());
//        if(authorByName.isPresent()){
//            throw new IllegalStateException("Author with name "+author.getAuthor()+ "exist!");
//        }
        authorRepository.save(author);
    }

    public Author getAuthor(long id){
        return authorRepository.findById(id).orElseThrow(()->new IllegalStateException("author with id="+id+" doesn't exist!"));
    }

    @Transactional
    public void updateAuthor(long id,String authorName){
        Author author=authorRepository.findById(id).orElseThrow(()->new IllegalStateException("author with id="+id+" doesn't exist!"));
        if(authorName!=null&&authorName.length()>0&& !Objects.equals(author.getAuthor(),authorName)){
            Author authorByName=authorRepository.findByAuthor(author.getAuthor());
//            if(authorByName.isPresent()){
//                throw new IllegalStateException("Author with name "+author.getAuthor()+ "exist!");
//            }
            author.setAuthor(authorName);
        }
    }


    public void deleteAuthor(long id){
        if(!authorRepository.existsById(id)){
            throw new IllegalStateException("author with id="+id+" doesn't exist!");
        }
        authorRepository.deleteById(id);
    }
}
