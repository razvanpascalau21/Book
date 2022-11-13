package razvan.pascalau.book.api;

import razvan.pascalau.book.author.Author;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.*;

@Entity(name ="Book")
@Table(name = "book",uniqueConstraints = {@UniqueConstraint(name = "book_name_unique",columnNames = "book_name")}
)
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence",
                       sequenceName = "book_sequence",
                       allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE,generator = "book_sequence")
    private Long id;
    @Column(name = "book_name",nullable = false,columnDefinition = "TEXT")
    private String bookName;
    @Column(name = "pages",nullable = false)
    private int pages;
    @ManyToMany
    @JoinTable(name = "book_author",joinColumns =@JoinColumn(name = "book_id"),inverseJoinColumns =@JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    public Book(){
    }

    public Book(String bookName, int pages) {
        this.bookName = bookName;
        this.pages = pages;
    }

    public Book(String bookName, int pages, Set<Author> authors) {
        this.bookName = bookName;
        this.pages = pages;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", pages=" + pages +
                '}';
    }
}
