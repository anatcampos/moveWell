package mindera.porto.moveWell.controller;

import mindera.porto.moveWell.dto.UserCreateDto;
import mindera.porto.moveWell.dto.UserDeleteDto;
import mindera.porto.moveWell.dto.UserUpdateDto;
import mindera.porto.moveWell.entity.User;
import mindera.porto.moveWell.entity.Video;
import mindera.porto.moveWell.repository.UserRepository;
import mindera.porto.moveWell.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserControllerTest extends BaseControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    @BeforeEach
    void setUp() {
        videoRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldGetUserById() throws Exception {
        // Criar um utilizador
        User user = new User();
        user.setName("Afonso");
        user.setUsername("afonsocc");
        user.setPassword("1234");
        user.setAge(43);
        user = userRepository.save(user);  // Salvar o utilizador na base de dados

        // Realizar a requisição para obter o utilizador pelo ID
        mockMvc.perform(get("/api/v1/user/{userId}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Afonso"))
                .andExpect(jsonPath("$.username").value("afonsocc"))
                .andExpect(jsonPath("$.age").value(43));
    }

    @Test
    void shouldReturnNotFoundForNonExistingUser() throws Exception {
        // Tentar realizar a requisição GET para o controlador com um ID inexistente
        mockMvc.perform(get("/api/v1/user/{userId}", 999L))  // 999L não existe no banco de dados
                .andExpect(status().isNotFound())  // Espera-se um status 404 (Não encontrado)
                .andExpect(content().string("Not found: 404 NOT_FOUND \"User does not exist.\""));  // Verificando o corpo do texto
    }

    @Test
    void shouldCreateUser() throws Exception {
        User user = new User();
        user.setName("Afonso");
        user.setUsername("afonsocc");
        user.setPassword("1234");
        user.setAge(43);

        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(notNullValue()));
    }

    @Test
    void shouldReturnConflictWhenUsernameIsDuplicate() throws Exception {
        // Criar e salvar um utilizador com o nome de utilizador "afonsocc"
        UserCreateDto userCreateDto1 = new UserCreateDto();
        userCreateDto1.setName("Afonso");
        userCreateDto1.setUsername("afonsocc");
        userCreateDto1.setPassword("1234");
        userCreateDto1.setAge(43);

        // Mapear o UserCreateDto para uma entidade User e salvar
        User user1 = new User();
        user1.setName(userCreateDto1.getName());
        user1.setUsername(userCreateDto1.getUsername());
        user1.setPassword(userCreateDto1.getPassword());
        user1.setAge(userCreateDto1.getAge());
        userRepository.save(user1);

        // Tentar criar outro utilizador com o mesmo nome de utilizador
        UserCreateDto userCreateDto2 = new UserCreateDto();
        userCreateDto2.setName("Rodolfo");
        userCreateDto2.setUsername("afonsocc");  // Nome de utilizador duplicado
        userCreateDto2.setPassword("5678");
        userCreateDto2.setAge(30);

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateDto2)))
                .andExpect(status().isConflict());  // Espera-se status 409 CONFLICT
    }

    @Test
    void shouldDeleteOwnUser() throws Exception {
        // Criar um utilizador para realizar o teste
        User user = new User();
        user.setName("Afonso");
        user.setUsername("afonsocc");
        user.setPassword("1234");
        user.setAge(43);
        user = userRepository.save(user);  // Salvar o utilizador na base de dados

        // Criar o objeto UserDeleteDto
        UserDeleteDto userDeleteDto = new UserDeleteDto();
        userDeleteDto.setUsername("afonsocc");
        userDeleteDto.setPassword("1234");

        // Realizar a requisição DELETE para excluir o utilizador
        mockMvc.perform(delete("/api/v1/user/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDeleteDto)))
                .andExpect(status().isOk());  // Espera-se sucesso ao deletar o próprio usuário
    }

    @Test
    void shouldReturnForbiddenWhenDeletingOtherUser() throws Exception {
        // Criar um utilizador para realizar o teste
        User user = new User();
        user.setName("Afonso");
        user.setUsername("afonsocc");
        user.setPassword("1234");
        user.setAge(43);
        user = userRepository.save(user);  // Salvar o utilizador na base de dados

        // Criar um segundo utilizador (não autorizado a deletar o primeiro)
        User anotherUser = new User();
        anotherUser.setName("Rodolfo");
        anotherUser.setUsername("rodolfo");
        anotherUser.setPassword("5678");
        anotherUser.setAge(30);
        anotherUser = userRepository.save(anotherUser);  // Salvar o segundo utilizador

        // Criar o objeto UserDeleteDto
        UserDeleteDto userDeleteDto = new UserDeleteDto();
        userDeleteDto.setUsername("rodolfo");
        userDeleteDto.setPassword("5678");

        // Tentar excluir o primeiro utilizador com as credenciais do segundo (não permitido)
        mockMvc.perform(delete("/api/v1/user/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDeleteDto)))
                .andExpect(status().isForbidden())  // Espera-se status 403 (FORBIDDEN)
                .andExpect(content().string("Forbidden: 403 FORBIDDEN \"You can only delete your own data.\""));
    }

    @Test
    void shouldReturnUnauthorizedWhenCredentialsAreInvalid() throws Exception {
        // Criar um utilizador para realizar o teste
        User user = new User();
        user.setName("Afonso");
        user.setUsername("afonsocc");
        user.setPassword("1234");
        user.setAge(43);
        user = userRepository.save(user);  // Salvar o utilizador na base de dados

        // Criar o objeto UserDeleteDto com credenciais erradas
        UserDeleteDto userDeleteDto = new UserDeleteDto();
        userDeleteDto.setUsername("wrongUser");
        userDeleteDto.setPassword("wrongPassword");

        // Tentar excluir o utilizador com credenciais erradas
        mockMvc.perform(delete("/api/v1/user/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDeleteDto)))
                .andExpect(status().isUnauthorized())  // Espera-se status 401 (UNAUTHORIZED)
                .andExpect(content().string("Unauthorized: 401 UNAUTHORIZED \"Invalid credentials. Please check your username and password.\""));
    }

    @Test
    void shouldUpdateOwnUser() throws Exception {
        // Criar um utilizador para realizar o teste
        User user = new User();
        user.setName("Afonso");
        user.setUsername("afonsocc");
        user.setPassword("1234");
        user.setAge(43);
        user = userRepository.save(user);  // Salvar o utilizador na base de dados

        // Criar o objeto UserUpdateDto com os novos dados
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setUsername("afonsocc");
        userUpdateDto.setPassword("1234");
        userUpdateDto.setNewUsername("afonsoUpdated");
        userUpdateDto.setNewPassword("newPassword");

        // Realizar a requisição PUT para atualizar o próprio utilizador
        mockMvc.perform(put("/api/v1/user/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateDto)))
                .andExpect(status().isOk());  // Espera-se sucesso ao atualizar o próprio usuário
    }

    @Test
    void shouldReturnForbiddenWhenUpdatingOtherUser() throws Exception {
        // Criar um utilizador para realizar o teste
        User user = new User();
        user.setName("Afonso");
        user.setUsername("afonsocc");
        user.setPassword("1234");
        user.setAge(43);
        user = userRepository.save(user);  // Salvar o utilizador na base de dados

        // Criar um segundo utilizador (não autorizado a editar o primeiro)
        User anotherUser = new User();
        anotherUser.setName("Rodolfo");
        anotherUser.setUsername("rodolfo");
        anotherUser.setPassword("5678");
        anotherUser.setAge(30);
        anotherUser = userRepository.save(anotherUser);  // Salvar o segundo utilizador

        // Criar o objeto UserUpdateDto
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setUsername("rodolfo");
        userUpdateDto.setPassword("5678");
        userUpdateDto.setNewUsername("rodolfoUpdated");
        userUpdateDto.setNewPassword("newPassword");

        // Tentar atualizar o primeiro utilizador com as credenciais do segundo (não permitido)
        mockMvc.perform(put("/api/v1/user/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateDto)))
                .andExpect(status().isForbidden())  // Espera-se status 403 (FORBIDDEN)
                .andExpect(content().string("Forbidden: 403 FORBIDDEN \"You can only edit your own data.\""));
    }

    @Test
    void shouldReturnUnauthorizedWhenCredentialsAreInvalidForUpdate() throws Exception {
        // Criar um utilizador para realizar o teste
        User user = new User();
        user.setName("Afonso");
        user.setUsername("afonsocc");
        user.setPassword("1234");
        user.setAge(43);
        user = userRepository.save(user);  // Salvar o utilizador na base de dados

        // Criar o objeto UserUpdateDto com credenciais erradas
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setUsername("wrongUser");
        userUpdateDto.setPassword("wrongPassword");
        userUpdateDto.setNewUsername("wrongUsername");
        userUpdateDto.setNewPassword("wrongPassword");

        // Tentar atualizar o utilizador com credenciais erradas
        mockMvc.perform(put("/api/v1/user/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateDto)))
                .andExpect(status().isUnauthorized())  // Espera-se status 401 (UNAUTHORIZED)
                .andExpect(content().string("Unauthorized: 401 UNAUTHORIZED \"Invalid credentials. Please check your username and password.\""));
    }




}
