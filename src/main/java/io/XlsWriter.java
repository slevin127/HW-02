package io;

import model.Statistics;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Утилита генерации XLSX-отчёта со статистикой по профилям обучения.
 */
public final class XlsWriter {

    private static final String SHEET_NAME = "Statistics";
    private static final String[] HEADERS = {
            "Study Profile",
            "Average Exam Score",
            "Student Count",
            "University Count",
            "University Names"
    };

    /**
     * Скрытый конструктор предотвращает создание экземпляров утилитного класса.
     */
    private XlsWriter() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Создаёт XLSX-файл со статистикой и записывает его по указанному пути.
     *
     * @param statistics список статистик по профилям обучения
     * @param filePath   путь к результирующему файлу
     * @throws IOException если не удаётся создать файл или записать данные
     */
    public static void writeStatistics(List<Statistics> statistics, String filePath) throws IOException {
        List<Statistics> safeStatistics = statistics == null
                ? Collections.emptyList()
                : statistics.stream().filter(Objects::nonNull).collect(Collectors.toList());

        Path path = Path.of(filePath);
        Path parent = path.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet(SHEET_NAME);
            createHeaderRow(sheet);
            populateDataRows(sheet, safeStatistics);
            autosizeColumns(sheet);

            try (FileOutputStream outputStream = new FileOutputStream(path.toFile())) {
                workbook.write(outputStream);
            }
        }
    }

    /**
     * Заполняет первую строку листа заголовками и применяет стили оформления.
     *
     * @param sheet рабочий лист книги Excel
     */
    private static void createHeaderRow(XSSFSheet sheet) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Font headerFont = sheet.getWorkbook().createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerStyle.setFont(headerFont);

        for (int columnIndex = 0; columnIndex < HEADERS.length; columnIndex++) {
            Cell cell = headerRow.createCell(columnIndex);
            cell.setCellValue(HEADERS[columnIndex]);
            cell.setCellStyle(headerStyle);
        }
    }

    /**
     * Заполняет строками данных лист Excel на основании переданного списка статистик.
     *
     * @param sheet       рабочий лист, в который необходимо внести данные
     * @param statistics  очищенный список статистик без {@code null}
     */
    private static void populateDataRows(XSSFSheet sheet, List<Statistics> statistics) {
        int rowIndex = 1;
        for (Statistics stats : statistics) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(stats.getStudyProfile() == null
                    ? ""
                    : stats.getStudyProfile().getProfileName());

            Cell avgCell = row.createCell(1);
            if (stats.getAvgExamScore() != null) {
                avgCell.setCellValue(stats.getAvgExamScore().doubleValue());
            } else {
                avgCell.setCellValue("");
            }

            row.createCell(2).setCellValue(stats.getStudentCount());
            row.createCell(3).setCellValue(stats.getUniversityCount());
            row.createCell(4).setCellValue(String.join(", ", stats.getUniversityNames()));
        }
    }

    /**
     * Подбирает оптимальную ширину для каждого столбца отчёта.
     *
     * @param sheet рабочий лист книги Excel
     */
    private static void autosizeColumns(XSSFSheet sheet) {
        for (int columnIndex = 0; columnIndex < HEADERS.length; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
}
