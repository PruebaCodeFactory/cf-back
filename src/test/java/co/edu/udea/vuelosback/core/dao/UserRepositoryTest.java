package co.edu.udea.vuelosback.core.dao;

import co.edu.udea.vuelosback.core.models.AplicationRole;
import co.edu.udea.vuelosback.core.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        UUID id = UUID.randomUUID();
        User user = User.builder()
                .id(id)
                .fullName("Jhonatan Granda")
                .phoneNumber("3111111111")
                .aplicationRole(AplicationRole.usuario)
                .password("123")
                .email("jhonas@udea.edu.co")
                .build();
        testEntityManager.merge(user);
    }

    @Test
    public void findByEmailFound() {
        User user = userRepository.findByEmail("jhonas@udea.edu.co");
        assertEquals(user.getEmail(), "jhonas@udea.edu.co");
        System.out.println("Usuario " +  user.getId());
    }

    @Test
    public void findByEmailNotFound(){
        User user = userRepository.findByEmail("jhonatan@udea.edu.c");
        assertEquals(user, "Usuario no encontrado");
    }
}