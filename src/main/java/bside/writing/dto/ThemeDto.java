package bside.writing.dto;

import bside.writing.domain.theme.Theme;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class ThemeDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Request{
        @JsonProperty("theme_count")
        private int count;

        @JsonProperty("theme_category")
        private String category;
    }

    private Long id;

    @JsonProperty("theme_name")
    private String name;

    @JsonProperty("theme_category")
    private String category;

    public ThemeDto(Theme entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.category = entity.getCategory();
    }

    public Theme toEntity(){
        return Theme.builder()
                .id(id)
                .name(name)
                .category(category)
                .build();
    }
}
