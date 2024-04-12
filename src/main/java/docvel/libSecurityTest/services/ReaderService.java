package docvel.libSecurityTest.services;

import docvel.libSecurityTest.entyties.Reader;
import docvel.libSecurityTest.repositories.ReadersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReadersRepository readers;

    public List<Reader> getAllReaders() {
        return readers.findAll();
    }

    public void addNewReader(Reader reader) {
        readers.save(reader);
    }

    public void deleteReader(long readerId) {
        readers.deleteById(readerId);
    }

    public Reader getReaderById(long readerId) {
        if (readers.findById(readerId).isPresent()) {
            return readers.findById(readerId).get();
        }
        return null;
    }

    public Optional<Reader> getReaderByLogin(String login) {
        return readers.findByLogin(login);
    }

    @EventListener(ContextRefreshedEvent.class)
    private void createReadersAtStartup() {
        Reader reader = new Reader();
        reader.setName("Максим");
        reader.setLogin("maxim");
        reader.setPassword("maxim");
        reader.setRole("READER");

        Reader admin = new Reader();
        admin.setName("Иван");
        admin.setLogin("ivan");
        admin.setPassword("ivan");
        admin.setRole("ADMIN");

        addNewReader(admin);
        addNewReader(reader);
    }
}
