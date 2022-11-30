package dev.manrihter.poi.service;

import dev.manrihter.poi.model.domain.Source;
import dev.manrihter.poi.service.handler.ExcelStreamReading;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ExcelServiceImpl implements ExcelService {
    private static final Logger log = LoggerFactory.getLogger(ExcelServiceImpl.class);

    @Override
    @SneakyThrows
    public List<Source> parseExcel(MultipartFile file) {
        List<Source> parsedData = new ArrayList<>();
        OPCPackage excelPackage = null;
        File temp = null;
        try {
            temp = File.createTempFile(UUID.randomUUID().toString(), ".xlsx");
            FileUtils.writeByteArrayToFile(temp, file.getBytes());

            excelPackage = OPCPackage.open(temp.getPath(), PackageAccess.READ);
            ExcelStreamReading excelReader = new ExcelStreamReading(excelPackage);

            parsedData = excelReader.process();
        } catch (Exception e) {
            log.error("Ошибка: {} ", e.getMessage());
        } finally {
            if (Objects.nonNull(excelPackage))
                excelPackage.revert();

            if (Objects.nonNull(temp))
                FileUtils.delete(temp);
        }
        return parsedData;
    }
}
