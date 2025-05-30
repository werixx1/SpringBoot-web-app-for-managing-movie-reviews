package com.example.reviewsmanager.controller;

import com.example.reviewsmanager.dto.RaportDTO;
import com.example.reviewsmanager.service.RaportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/database")
public class RaportController
{
    private final RaportService raportService;

    public RaportController(RaportService raportService)
    {
        this.raportService = raportService;
    }

    @GetMapping("/raport")
    public ResponseEntity<RaportDTO> getDatabaseRaport()
    {
        return ResponseEntity.ok(raportService.getDatabaseRaport());
    }
}


