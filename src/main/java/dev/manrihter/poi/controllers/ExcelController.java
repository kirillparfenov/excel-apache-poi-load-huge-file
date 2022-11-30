package dev.manrihter.poi.controllers;

import dev.manrihter.poi.model.domain.Source;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelController {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Source> uploadExcelFile(@RequestParam(name = "file")MultipartFile multipartFile);
}
