package docvel.libSecurityTest.services;

import docvel.libSecurityTest.configs.LibraryProperties;
import docvel.libSecurityTest.entyties.Issue;
import docvel.libSecurityTest.repositories.BooksRepository;
import docvel.libSecurityTest.repositories.IssuesRepository;
import docvel.libSecurityTest.repositories.ReadersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssuesRepository issues;
    private final ReadersRepository readers;
    private final BooksRepository books;
    private final LibraryProperties properties;

    private void notFoundReaderById(long id){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format(properties.getNoReaderById(), id));
    }

    private void notFoundBookById(long id){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format(properties.getNoBook(), id));
    }

    private void notFoundIssueById(long id){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format(properties.getNoIssue(), id));
    }

    private void numberOfBooksExceeded(long readerId){
        if(countingNumOfBooks(readerId))
            throw new ResponseStatusException(HttpStatus
                    .valueOf(String.format(properties.getNumberOfBooksExceeded(),
                            readerId, properties.getMaxAllowedBooks())));
    }

    private void bookHasBeenReturned(long bookId){
        throw new ResponseStatusException(HttpStatus
                .valueOf(String.format(properties.getBookHasBeenReturned(), bookId)));
    }

    private boolean countingNumOfBooks(long readerId){
        int count = issues.findAll().stream()
                .filter(issue -> issue.getReader().getId() == readerId)
                .toList()
                .size();
        return count > properties.getMaxAllowedBooks();
    }

    public List<Issue> showAllIssues(){
        return issues.findAll();
    }

    public Issue showIssueById(long issueId){
        if(issues.findById(issueId).isPresent()){
            return issues.findById(issueId).get();
        }else notFoundIssueById(issueId);
        return null;
    }

    public void addNewIssue(Issue issue){
        if(readers.findById(issue.getReader().getId()).isEmpty())
            notFoundReaderById(issue.getReader().getId());
        if(books.findById(issue.getBook().getId()).isEmpty())
            notFoundBookById(issue.getBook().getId());
        if(!countingNumOfBooks(issue.getReader().getId())) {
            issue.setDateOfIssuance(LocalDate.now());
            issue.setDateOfReturn(null);
            issues.save(issue);
        }
        else numberOfBooksExceeded(issue.getReader().getId());
    }

    public Issue returnOfBook(long issueId){
        if(issues.findById(issueId).isEmpty())
            notFoundIssueById(issueId);
        else if(issues.findById(issueId).get().getDateOfReturn() == null) {
            Issue issue = issues.findById(issueId).get();
            issue.setDateOfReturn(LocalDate.now());
            issues.save(issue);
            return issue;
        }else bookHasBeenReturned(issueId);
        return null;
    }
}
