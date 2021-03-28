package bside.writing.domain.theme;

import bside.writing.templateClass.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "theme")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Theme extends BaseTimeEntity implements Serializable {

    @Id
    @Column(name = "theme_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theme_name", length = 10, nullable = false)
    private String name;

    @Column(name = "theme_category", length = 10)
    private String category;

}

