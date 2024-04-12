package docvel.libSecurityTest.controllers;

import docvel.libSecurityTest.entyties.Book;
import docvel.libSecurityTest.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("addBook")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "book/addBook";
    }

    @PostMapping("saveBook")
    public String addNewBook(@ModelAttribute Book book){
        if(!book.getTitle().isEmpty() && !book.getAuthor().isEmpty())
            bookService.addNewBook(book);
        return "redirect:allBooks";
    }

    @GetMapping("deleteBook")
    public String deleteBook(@RequestParam Long bookId){
        if(bookId != null){
            bookService.deleteBook(bookId);
        }
        return "redirect:allBooks";
    }

    @GetMapping("updateBook")
    public String updateBook(@RequestParam Long bookId, Model model){
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "book/addBook";
    }

}