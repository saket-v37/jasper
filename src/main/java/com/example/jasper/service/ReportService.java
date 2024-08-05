package com.example.jasper.service;

import com.example.jasper.dto.Report;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public byte[] generateReport(List<Report> dataList) throws JRException {
        // Load the JRXML file from the resources directory
        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/reports/report_template.jrxml"));

        // Create a data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

        // Parameters map
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Sample Report");

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export the report to PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
