package bside.writing.Repository;

import bside.writing.domain.theme.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Query(value = "SELECT * FROM theme order by rand() limit ?1", nativeQuery = true)
    List<Theme> getNRandomTheme(int n);
}