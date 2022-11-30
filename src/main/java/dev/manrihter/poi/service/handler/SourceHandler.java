package dev.manrihter.poi.service.handler;

import dev.manrihter.poi.model.domain.Source;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

import java.util.ArrayList;
import java.util.List;

public class SourceHandler implements XSSFSheetXMLHandler.SheetContentsHandler {
    private boolean firstCellOfRow;
    private int currentRow = -1;
    private int currentCol = -1;
    private Source source;
    private final List<Source> result = new ArrayList<>();

    @Override
    public void startRow(int rowNum) {
        source = new Source();

        firstCellOfRow = true;
        currentRow = rowNum;
        currentCol = -1;
    }

    @Override
    public void endRow(int rowNum) {
        result.add(source);
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        if (firstCellOfRow)
            firstCellOfRow = false;

        if (cellReference == null)
            cellReference = new CellAddress(currentRow, currentCol).formatAsString();

        int thisCol = (new CellReference(cellReference)).getCol();

        if (formattedValue == null) return;

        currentCol = thisCol;

        if (currentCol == 0)
            source.setTitle(formattedValue);

        if (currentCol == 1)
            source.setCategory(formattedValue);

        if (currentCol == 2)
            source.setRegion(formattedValue);

        if (currentCol == 4)
            source.setLink(formattedValue);

        if (currentCol == 6)
            source.setLevel(formattedValue);
    }

    public List<Source> getResult() {
        return this.result;
    }
}
