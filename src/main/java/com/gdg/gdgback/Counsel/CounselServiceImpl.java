package com.gdg.gdgback.Counsel;

import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.AgentService;
import com.gdg.gdgback.Counsel.DTO.Request.*;
import com.gdg.gdgback.Counsel.DTO.Response.*;
import com.gdg.gdgback.Global.Validator;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
@EnableScheduling
public class CounselServiceImpl implements CounselService {
    private final CounselRepository counselRepository;
    private final AgentService agentService;

    private final Validator validator;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    CounselServiceImpl(CounselRepository counselRepository, AgentService agentService, Validator validator) {
        this.counselRepository = counselRepository;
        this.agentService = agentService;
        this.validator = validator;
    }

    @Override
    public CounselCreateResponseDto createCounsel(HttpSession session, CounselCreateRequestDto createRequestDto) {
        validator.validateUserExists(createRequestDto.getUserId());

        session.setAttribute("Dialogue", "[Dialogue]");
        CounselDocument counselDocument = CounselMapper.map(createRequestDto);

        String id = counselRepository.save(counselDocument).getId();
        String response = agentService.replyByText(session, AgentTextRequestDto.builder().counselId(id).content("지금 공황이 오는 것 같아요. 최대한 짧게 뭐라도 말해주세요.").build());
        return CounselCreateResponseDto.builder()
                .id(id)
                .content(response)
                .build();
    }

    @Override
    public CounselReadResponseDto readCounsel(String id) {
        CounselDocument counselDocument = counselRepository.findById(id)
                .orElseThrow(() -> new CounselNotExistsException(id));

        return CounselMapper.map(counselDocument);
    }

    @Override
    public CounselReadListResponseDto readCounselList() {
        List<CounselDocument> counselDocumentList = counselRepository.findAll();

        return CounselMapper.map(counselDocumentList);
    }

    @Override
    public CounselReadListResponseDto readCounselByUserId(String id) {
        validator.validateUserExists(id);

        List<CounselDocument> counselDocumentList = counselRepository.findAllByUserId(id);
        return CounselMapper.map(counselDocumentList);
    }

    @Override
    public CounselReadListResponseDto readCounselByUserIdAndYearMonth(String id, YearMonth yearMonth) {
        validator.validateUserExists(id);
        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

        Query query = new Query()
                .addCriteria(Criteria.where("userId").is(id))
                .addCriteria(Criteria.where("createdAt").gte(start).lte(end));

        List<CounselDocument> counselDocumentList = mongoTemplate.find(query, CounselDocument.class);
        return CounselMapper.map(counselDocumentList);
    }

    @Override
    public void deleteCounsel(CounselDeleteRequestDto deleteRequestDto) {
        CounselDocument counselDocument = counselRepository.findById(deleteRequestDto.getId())
                .orElseThrow(() -> new CounselNotExistsException(deleteRequestDto.getId()));

        counselRepository.delete(counselDocument);
    }

    @Scheduled(cron = "0 0 2 * * ?")
    @Override
    public void deleteCounselsOverTimeLimit() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(30);

        Query query = new Query(Criteria.where("endTime").is(null).and("startTime").lt(threshold));

        mongoTemplate.remove(query, CounselDocument.class);
    }

    @Override
    public void endCounsel(CounselEndRequestDto counselEndRequestDto) {
        validateCounselExists(counselEndRequestDto.getId());

        Query query = new Query(Criteria.where("id").is(counselEndRequestDto.getId()));
        Update update = new Update().set("endTime", LocalDateTime.now());

        mongoTemplate.updateFirst(query, update, CounselDocument.class);
    }

    @Override
    public void validateCounselExists(String id) {
        if(!counselRepository.existsById(id)) {
            throw new CounselNotExistsException(id);
        }
    }
}
