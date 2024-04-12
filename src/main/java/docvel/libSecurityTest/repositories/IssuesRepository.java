package docvel.libSecurityTest.repositories;

import docvel.libSecurityTest.entyties.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuesRepository extends JpaRepository<Issue, Long> {
}
