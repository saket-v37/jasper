package com.example.jasper.controller;

import com.example.jasper.dto.Report;
import com.example.jasper.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport() {
        try {
            List<Report> dataList = Arrays.asList(new Report("title 1"), new Report("title 2"));
            byte[] pdfContent = reportService.generateReport(dataList);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=report.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(pdfContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
