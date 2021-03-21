package bside.writing.domain.theme;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Table(name = "theme")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Theme implements Serializable {

    @Id
    @Column(name = "theme_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theme_name", length = 10, nullable = false)
    private String name;

    @Column(name = "theme_category", length = 10, nullable = false)
    private String category;
    
}
