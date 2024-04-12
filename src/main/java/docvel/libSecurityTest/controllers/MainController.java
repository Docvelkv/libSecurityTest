package docvel.libSecurityTest.controllers;

import docvel.libSecurityTest.services.BookService;
import docvel.libSecurityTest.services.IssueService;
import docvel.libSecurityTest.services.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BookService bookService;
    private final ReaderService readerService;
    private final IssueService issueService;

    @GetMapping("library")
    public String library(){
        return "library";
    }

    @GetMapping("allBooks")
    public String getAllBooks(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "book/allBooks";
    }

    @GetMapping("allReaders")
    public String getAllReaders(Model model){
        model.addAttribute("readers", readerService.getAllReaders());
        return "reader/allReaders";
    }

    @GetMapping("allIssues")
    public String getAllIssues(Model model){
        model.addAttribute("issues", issueService.showAllIssues());
        return "issue/allIssues";
    }
}
