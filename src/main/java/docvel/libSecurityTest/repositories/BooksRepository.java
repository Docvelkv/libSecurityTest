package docvel.libSecurityTest.repositories;

import docvel.libSecurityTest.entyties.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Long> {

}