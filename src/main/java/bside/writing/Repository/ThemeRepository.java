package bside.writing.Repository;

import bside.writing.domain.theme.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Query(value = "SELECT * FROM theme order by rand() limit 2", nativeQuery = true)
    List<Theme> findByName();
}
// order by rand() limit ?1