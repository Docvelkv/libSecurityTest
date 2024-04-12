package docvel.libSecurityTest.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.application.library")
public class LibraryProperties {

    private int maxAllowedBooks;
    private String noReaderById;
    private String noReaderByLogin;
    private String noBook;
    private String noIssue;
    private String numberOfBooksExceeded;
    private String bookHasBeenReturned;
}
