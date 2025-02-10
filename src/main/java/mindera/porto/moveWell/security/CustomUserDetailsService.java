package mindera.porto.moveWell.security;

import mindera.porto.moveWell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;  // Supondo que você tenha um repositório de usuários

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar usuário no banco de dados
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Retornar um UserDetails com as informações do usuário
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())  // Assumindo que você já tenha senha criptografada no banco de dados
                .roles(user.getRole().name())  // Assumindo que a Role é armazenada de forma simples
                .build();
    }
}
