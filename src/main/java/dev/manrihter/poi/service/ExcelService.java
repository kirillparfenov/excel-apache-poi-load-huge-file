package dev.manrihter.poi.service;

import dev.manrihter.poi.model.domain.Source;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelService {
    List<Source> parseExcel(MultipartFile file);
}
