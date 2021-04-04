package bside.writing.Repository;

import bside.writing.domain.badge.Badge;
import bside.writing.domain.theme.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Query(value = "SELECT * FROM theme order by rand() limit ?1", nativeQuery = true)
    List<Theme> getNRandomTheme(int n);
    
    Optional<Theme> findByName(final String name);

}