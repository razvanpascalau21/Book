package razvan.pascalau.book.api;

import razvan.pascalau.book.author.Author;

public class AuthorToBookForm {
    private Book book;
    private Author author;

    public AuthorToBookForm(){}

    public AuthorToBookForm(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "AuthorToBookForm{" +
                "book=" + book +
                ", author=" + author +
                '}';
    }
}
