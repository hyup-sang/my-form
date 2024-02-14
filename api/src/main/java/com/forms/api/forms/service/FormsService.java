package com.forms.api.forms.service;

import com.forms.api.forms.domain.Forms;
import com.forms.api.forms.domain.FormsStatus;
import com.forms.api.forms.domain.repository.FormsRepository;
import com.forms.api.forms.dto.request.CreateFormsRequest;
import com.forms.api.forms.dto.request.ModifyFormsRequest;
import com.forms.api.member.domain.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FormsService {
    private final FormsRepository formsRepository;

    public FormsService(FormsRepository formsRepository) {
        this.formsRepository = formsRepository;
    }

    public void createForms(Member member, CreateFormsRequest createFormsRequest) {
        Forms forms = Forms.builder()
                        .member(member)
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

    public Forms findForms(Long formsId) {
        return formsRepository
            .findById(formsId)
            .orElseThrow(() -> new RuntimeException("not exists"));
    }

    public List<Forms> findFormsList(Member member) {
        return formsRepository.findByMemberId(member.getId());
    }

    public void modifyForms(Long formsId, ModifyFormsRequest modifyFormsRequest) {
        Forms forms = this.findForms(formsId);

        forms.setTitle(modifyFormsRequest.getTitle());
        forms.setDescription(modifyFormsRequest.getDescription());
        forms.setAccess(modifyFormsRequest.getAccess());
        forms.setStatus(modifyFormsRequest.getStatus());
        forms.setStartedAt(modifyFormsRequest.getStartedAt());
        forms.setEndedAt(modifyFormsRequest.getEndedAt());
        forms.setModifiedAt(LocalDateTime.now());

        formsRepository.save(forms);
    }

    public void deleteForms(Long formsId) {
        formsRepository.deleteById(formsId);
    }
}
