package com.gdg.gdgback;

import com.gdg.gdgback.User.UserService;
import com.gdg.gdgback.User.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.User.UserDocument;
import com.gdg.gdgback.User.Exception.UserAlreadyExistsException;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import com.gdg.gdgback.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


// "test" 프로필 - ADC 인증 필요한 서비스는 제외하고 테스트.
@SpringBootTest
@ActiveProfiles("test")
class GdgbackApplicationTests {
	@MockitoBean
	UserRepository mockedRepository = mock(UserRepository.class);

	@Autowired
	UserService userService;

	@BeforeEach
	void setMockedRepository() {
		// 모조 리포지토리에 "exist"라는 아이디를 가진 유저가 저장되어 있다고 가정한다.
		UserDocument mockedUser = UserDocument.builder()
				.id("exist")
				.name("옥찌")
				.build();

		when(mockedRepository.findById(anyString())).thenReturn(Optional.empty());
		when(mockedRepository.existsById(anyString())).thenReturn(false);
		when(mockedRepository.findById("exist")).thenReturn(Optional.of(mockedUser));
		when(mockedRepository.existsById("exist")).thenReturn(true);
	}
	@Test
	void createUser() {
		UserCreateRequestDto userCreateRequestDto = UserCreateRequestDto.builder()
				.id("notExist")
				.name("빵빵이")
				.build();

		assertDoesNotThrow(() -> userService.createUser(userCreateRequestDto));
	}
	@Test
	void createExistingUser() {
		UserCreateRequestDto userCreateRequestDto = UserCreateRequestDto.builder()
				.id("exist")
				.name("옥찌")
				.build();
		assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(userCreateRequestDto));
	}
	@Test
	void readUser() {
		String id = "exist";
		assertDoesNotThrow(() -> userService.readUser(id));
	}
	@Test
	void readNotExistingUser() {
		String id = "notExist";
		assertThrows(UserNotExistsException.class, () -> userService.readUser(id));
	}

	/*
	@Autowired
	CounselingService counselingService;

	@Test
	void CounselingRequest() {
		PromptDto promptDto = new PromptDto("안녕하세요!");
		assertDoesNotThrow(() -> {
			String textReply = counselingService.respondByText(promptDto);
			byte[] voiceReply = counselingService.respondByVoice(promptDto);
			System.out.println(textReply);
		});
	}
	 */
}