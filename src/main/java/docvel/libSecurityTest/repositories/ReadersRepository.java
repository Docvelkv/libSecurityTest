package docvel.libSecurityTest.repositories;

import docvel.libSecurityTest.entyties.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReadersRepository extends JpaRepository<Reader, Long> {

    Optional<Reader> findByLogin(String login);
}
