package bside.writing.Repository;

import javax.persistence.AttributeConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime,String> {

    @Override
    public String convertToDatabaseColumn(LocalDateTime attribute) {

        if(attribute == null) {
            return "";
        } else {
            return attribute.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dbData) {
        return null;
    }


}
