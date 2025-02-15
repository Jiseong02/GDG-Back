package com.gdg.gdgback;

import com.gdg.gdgback.DTO.PromptDto;
import com.gdg.gdgback.DTO.UserCreationDto;
import com.gdg.gdgback.Document.UserDocument;
import com.gdg.gdgback.Repository.UserRepository;
import com.gdg.gdgback.Service.CounselingService;
import com.gdg.gdgback.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class GdgbackApplicationTests {
	@Autowired
	UserService userService;

	@MockitoBean
	UserRepository mockedRepository = mock(UserRepository.class);
	@MockitoBean
	CounselingService counselingService;

	@BeforeEach
	void setMockedRepository() {
		// 모조 리포지토리에 "exist"라는 아이디를 가진 유저가 저장되어 있다고 가정한다.
		UserDocument mockedUser = new UserDocument("exist", "홍길동");

		when(mockedRepository.findById(anyString())).thenReturn(Optional.empty());
		when(mockedRepository.findById("exist")).thenReturn(Optional.of(mockedUser));
	}
	@Test
	void addUser() {
		UserCreationDto userCreationDto = new UserCreationDto("notExist", "빵빵이");
		userService.addUser(userCreationDto);
	}
	@Test
	void addExistingUser() {
		UserCreationDto userCreationDto = new UserCreationDto("exist", "옥찌");
		assertThrows(IllegalArgumentException.class, () -> userService.addUser(userCreationDto));
	}
	@Test
	void getUser() {
		String id = "exist";
		userService.getUserById(id);
	}
	@Test
	void GetNotExistingUser() {
		String id = "notExist";
		assertThrows(IllegalArgumentException.class, () -> userService.getUserById(id));
	}

	// 아래의 테스트는 토큰을 소모함!!! 자주 실행하지 말 것!!!
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
