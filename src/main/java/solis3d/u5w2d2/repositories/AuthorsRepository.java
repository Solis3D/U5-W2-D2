package solis3d.u5w2d2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solis3d.u5w2d2.entities.Author;

public interface AuthorsRepository extends JpaRepository<Author, Long> {
    boolean existsByEmail(String email);
}
