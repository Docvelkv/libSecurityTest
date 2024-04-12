package docvel.libSecurityTest.security;

import docvel.libSecurityTest.configs.LibraryProperties;
import docvel.libSecurityTest.entyties.Reader;
import docvel.libSecurityTest.services.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    private final ReaderService service;
    private final LibraryProperties properties;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Reader reader = service.getReaderByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException(String.format(properties.getNoReaderByLogin(), login)));
        return new User(reader.getLogin(),
                reader.getPassword(),
                List.of(new SimpleGrantedAuthority(reader.getRole())));
    }
}
