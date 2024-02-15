package ru.tinkoff.notes.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tinkoff.notes.repository.AuthorRepository;
import ru.tinkoff.notes.repository.dto.Author;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthorRepository authorRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return User.withDefaultPasswordEncoder()
                .username(author.name())
                .password(author.password())
                .roles("AUTHOR")
                .build();
    }
}
