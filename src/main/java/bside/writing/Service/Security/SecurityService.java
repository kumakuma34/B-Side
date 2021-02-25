package bside.writing.Service.Security;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    String createToken(String subject, long ttlMillis);
    String getEmail(String token);
    String getName(String token);
}
