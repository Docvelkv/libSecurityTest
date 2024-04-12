package docvel.libSecurityTest.services;

import docvel.libSecurityTest.entyties.Book;
import docvel.libSecurityTest.repositories.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BooksRepository books;

    public List<Book> getAllBooks(){
        return books.findAll();
    }

    public void addNewBook(Book book){
        books.save(book);
    }

    public void deleteBook(long bookId){
        books.deleteById(bookId);
    }

    public Book getBookById(long bookId){
        if(books.findById(bookId).isPresent()){
            return books.findById(bookId).get();
        }
        return null;
    }

    @EventListener(ContextRefreshedEvent.class)
    private void createBooksAtStartup(){
        Book book1 = new Book();
        book1.setAuthor("Айтматов Чингиз");
        book1.setTitle("Плаха");
        addNewBook(book1);

        Book book2 = new Book();
        book2.setAuthor("Гоголь Николай");
        book2.setTitle("Мёртвые души");
        addNewBook(book2);

        Book book3 = new Book();
        book3.setAuthor("Дюма Александр");
        book3.setTitle("Граф Монте-Кристо");
        addNewBook(book3);

        Book book4 = new Book();
        book4.setAuthor("Оруэлл Джордж");
        book4.setTitle("1984");
        addNewBook(book4);

        Book book5 = new Book();
        book5.setAuthor("Шекспир Уильям");
        book5.setTitle("Гамлет");
        addNewBook(book5);
    }
}

