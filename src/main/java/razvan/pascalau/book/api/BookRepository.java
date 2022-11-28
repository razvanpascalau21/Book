package razvan.pascalau.book.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  @Query("select  b from Book b "
      + "left join fetch b.authors "
      + "where b.bookName = :name")
  Book findByBookName(@Param("name") String name);


}
