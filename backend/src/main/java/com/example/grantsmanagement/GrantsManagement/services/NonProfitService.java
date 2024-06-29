package com.example.grantsmanagement.GrantsManagement.services;

import com.example.grantsmanagement.GrantsManagement.dto.CreateNonProfitDto;
import com.example.grantsmanagement.GrantsManagement.dto.EditTemplateDto;
import com.example.grantsmanagement.GrantsManagement.models.NonProfit;
import com.example.grantsmanagement.GrantsManagement.repositories.NonProfitRepository;
import com.example.grantsmanagement.GrantsManagement.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NonProfitService {

    @Autowired
    private NonProfitRepository nonProfitRepository;

    public ResponseEntity<List<NonProfit>> createNonProfit (CreateNonProfitDto createNonProfitDto) {
        NonProfit nonProfit = new NonProfit(createNonProfitDto.getName(),createNonProfitDto.getAddress(),createNonProfitDto.getEmail(),createNonProfitDto.getFoundationId(), new Date(), StringUtils.getDefaultEmailTemplate());
        nonProfitRepository.save(nonProfit);
        return getAllNonProfits(createNonProfitDto.getFoundationId());
    }

    public ResponseEntity<List<NonProfit>> getAllNonProfits (Long foundationId) {
        List<NonProfit> nonProfits = nonProfitRepository.findAllNonProfitsByFoundationId(foundationId);
        return ResponseEntity.ok(nonProfits);
    }

    public List<NonProfit> getNonProfitsByNonProfitIds (List<Long> nonProfitIds) {
        List<NonProfit> nonProfits = nonProfitRepository.findAllById(nonProfitIds);
        return nonProfits;
    }

    public ResponseEntity<List<NonProfit>> updateTemplate(EditTemplateDto editTemplateDto) {
        nonProfitRepository.updateTemplate(editTemplateDto.getNonProfitId(), editTemplateDto.getTemplate());
        return getAllNonProfits(editTemplateDto.getFoundationId());
    }
}
