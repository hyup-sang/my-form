package com.forms.api.forms.service;

import com.forms.api.forms.domain.Forms;
import com.forms.api.forms.domain.FormsStatus;
import com.forms.api.forms.domain.repository.FormsRepository;
import com.forms.api.forms.dto.request.CreateFormsRequest;
import com.forms.api.member.domain.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FormsService {
    private final FormsRepository formsRepository;

    public FormsService(FormsRepository formsRepository) {
        this.formsRepository = formsRepository;
    }

    public void createForms(CreateFormsRequest createFormsRequest) {
        Forms forms = Forms.builder()
//                        .member(member)
                        .title(createFormsRequest.getTitle())
                        .description(createFormsRequest.getDescription())
                        .access(createFormsRequest.getAccess())
                        .status(FormsStatus.READY)
                        .startedAt(createFormsRequest.getStartedAt())
                        .endedAt(createFormsRequest.getEndedAt())
                        .createdAt(LocalDateTime.now())
                        .modifiedAt(LocalDateTime.now())
                        .build();

        formsRepository.save(forms);
    }

}
