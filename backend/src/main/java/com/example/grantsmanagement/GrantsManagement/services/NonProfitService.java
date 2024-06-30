package com.example.grantsmanagement.GrantsManagement.services;

import com.example.grantsmanagement.GrantsManagement.dto.CreateNonProfitDto;
import com.example.grantsmanagement.GrantsManagement.dto.EditTemplateDto;
import com.example.grantsmanagement.GrantsManagement.models.NonProfit;
import com.example.grantsmanagement.GrantsManagement.repositories.NonProfitRepository;
import com.example.grantsmanagement.GrantsManagement.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NonProfitService {

    @Autowired
    private NonProfitRepository nonProfitRepository;

    public ResponseEntity<Page<NonProfit>> createNonProfit (CreateNonProfitDto createNonProfitDto) {
        NonProfit nonProfit = new NonProfit(createNonProfitDto.getName(),createNonProfitDto.getAddress(),createNonProfitDto.getEmail(),createNonProfitDto.getFoundationId(), new Date(), StringUtils.getDefaultEmailTemplate());
        nonProfitRepository.save(nonProfit);
        return getAllNonProfits(createNonProfitDto.getFoundationId(), 1, 5);
    }

    public ResponseEntity<Page<NonProfit>> getAllNonProfits (Long foundationId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("createdAt").descending());
        Page<NonProfit> nonProfits = nonProfitRepository.findAllNonProfitsByFoundationId(foundationId, pageable);
        return ResponseEntity.ok(nonProfits);
    }

    public List<NonProfit> getNonProfitsByNonProfitIds (List<Long> nonProfitIds) {
        List<NonProfit> nonProfits = nonProfitRepository.findAllById(nonProfitIds);
        return nonProfits;
    }

    public ResponseEntity<Page<NonProfit>> updateTemplate(EditTemplateDto editTemplateDto) {
        nonProfitRepository.updateTemplate(editTemplateDto.getNonProfitId(), editTemplateDto.getTemplate());
        return getAllNonProfits(editTemplateDto.getFoundationId(), 1, 5);
    }
}
