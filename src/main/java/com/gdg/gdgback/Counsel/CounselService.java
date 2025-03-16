package com.gdg.gdgback.Counsel;

import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.AgentService;
import com.gdg.gdgback.Counsel.DTO.Request.CounselCreateRequestDto;
import com.gdg.gdgback.Counsel.DTO.Request.CounselDeleteRequestDto;
import com.gdg.gdgback.Counsel.DTO.Request.CounselEndRequestDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselCreateResponseDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadByUserIdResponseDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadListResponseDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import com.gdg.gdgback.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Profile("!test")
@Service
@EnableScheduling
public class CounselService {
    private final CounselRepository counselRepository;
    private final UserRepository userRepository;

    private final AgentService agentService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    CounselService(CounselRepository counselRepository, UserRepository userRepository, AgentService agentService) {
        this.counselRepository = counselRepository;
        this.userRepository = userRepository;

        this.agentService = agentService;
    }

    public CounselCreateResponseDto createCounsel(CounselCreateRequestDto createRequestDto) throws UserNotExistsException, IOException {
        if(!userRepository.existsById(createRequestDto.getUserId())) {
            throw new UserNotExistsException();
        }

        CounselDocument counselDocument = CounselDocument.builder()
                .userId(createRequestDto.getUserId())
                .build();

        return CounselCreateResponseDto.builder()
                .id(counselRepository.save(counselDocument).getId())
                .content(
                    agentService.getTextResponse(AgentTextRequestDto.builder().content("저는 지금 공황을 겪고 있어요.").build())
                )
                .build();
    }

    public CounselReadResponseDto readCounsel(String id) throws CounselNotExistsException {
        CounselDocument counselDocument = counselRepository.findById(id)
                .orElseThrow(CounselNotExistsException::new);

        return CounselMapper.map(counselDocument);
    }

    public CounselReadListResponseDto readCounselList() {
        return CounselMapper.map(counselRepository.findAll());
    }

    public CounselReadByUserIdResponseDto readCounselByUserId(String id) throws UserNotExistsException {
        if(!userRepository.existsById(id)) {
            throw new UserNotExistsException();
        }

        return CounselMapper.mapToCounselReadByUserIdResponseDto(counselRepository.findAllByUserId(id));
    }

    public void deleteCounsel(CounselDeleteRequestDto deleteRequestDto) throws CounselNotExistsException {
        CounselDocument counselDocument = counselRepository.findById(deleteRequestDto.getId())
                .orElseThrow(CounselNotExistsException::new);

        counselRepository.delete(counselDocument);
    }

    @Scheduled(cron = "0 0 2 * * ?")
    private void deleteCounselsOverTimeLimit() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(30);

        Query query = new Query(Criteria.where("endTime").is(null)
                .and("startTime").lt(threshold));

        mongoTemplate.remove(query, CounselDocument.class);
    }

    public void endCounsel(CounselEndRequestDto counselEndRequestDto) throws CounselNotExistsException {
        if(!counselRepository.existsById(counselEndRequestDto.getId())) {
            throw new CounselNotExistsException();
        }

        Query query = new Query(Criteria.where("id").is(counselEndRequestDto.getId()));
        Update update = new Update().set("endTime", LocalDateTime.now());

        mongoTemplate.updateFirst(query, update, CounselDocument.class);
    }
}
