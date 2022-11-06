package razvan.pascalau.book.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import razvan.pascalau.book.api.Book;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "author",uniqueConstraints = {@UniqueConstraint(name = "author_unique",columnNames = "author")})
public class Author {
    @Id
    @SequenceGenerator(name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,generator = "author_sequence")
    private Long id;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String author;
    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books=new HashSet<>();

    public Author(){}
    public Author(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", author='" + author + '\'' +
                '}';
    }
}
