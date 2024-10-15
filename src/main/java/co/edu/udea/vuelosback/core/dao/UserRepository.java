package co.edu.udea.vuelosback.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.udea.vuelosback.core.models.User;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

    User findByEmail(@Param(("email")) String email);
}
