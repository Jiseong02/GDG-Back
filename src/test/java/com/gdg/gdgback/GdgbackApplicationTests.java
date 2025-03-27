package com.gdg.gdgback;

import com.gdg.gdgback.User.UserServiceImpl;
import com.gdg.gdgback.User.UserDocument;
import com.gdg.gdgback.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.mockito.Mockito.*;


// "test" 프로필 - ADC 인증 필요한 서비스는 제외하고 테스트.
@SpringBootTest
@ActiveProfiles("test")
public class GdgbackApplicationTests {
}