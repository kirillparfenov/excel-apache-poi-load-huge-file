package dev.manrihter.poi.controllers;

import dev.manrihter.poi.model.domain.Source;
import dev.manrihter.poi.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ExcelControllerImpl implements ExcelController {

    private final ExcelService excelService;

    @Autowired
    public ExcelControllerImpl(final ExcelService excelService) {
        this.excelService = excelService;
    }

    @Override
    public List<Source> uploadExcelFile(MultipartFile multipartFile) {
        return excelService.parseExcel(multipartFile);
    }
}
