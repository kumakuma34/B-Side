package bside.writing.badge.badgeRepo;

import bside.writing.Repository.BadgeRepository;
import bside.writing.domain.badge.Badge;
import bside.writing.dto.BadgeResponseDto;
import bside.writing.enums.BadgeCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@Transactional
public class badgeRepositoryTest {

    @Autowired
    BadgeRepository badgeRepository;


}
