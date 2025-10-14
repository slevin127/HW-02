package io;

import enums.StudyProfile;
import model.Student;
import model.University;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Утилита чтения XLSX-файлов с исходными данными по университетам и студентам.
 */
public class XlsReader {

    private static final Logger logger = Logger.getLogger(XlsReader.class.getName());

    /**
     * Скрытый конструктор предотвращает создание экземпляров утилитного класса.
     */
    private XlsReader() {
    }

    /**
     * Считывает сведения об университетах из XLSX-файла.
     *
     * @param filePath путь к Excel-файлу
     * @return список университетов, построенный из строк таблицы
     * @throws IOException если файл недоступен для чтения или повреждён
     * @throws IllegalArgumentException если лист с университетами не найден
     */
    public static List<University> readXlsUniversities(String filePath) throws IOException {
        logger.info("Starting to read universities from file: " + filePath);

        List<University> universities = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Университеты");
            if (sheet == null) {
                logger.severe("Sheet with universities not found in file: " + filePath);
                throw new IllegalArgumentException("Sheet with universities not found in file: " + filePath);
            }

            Iterator<Row> rows = sheet.iterator();
            rows.next(); // Skip header row

            int rowCount = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                University university = new University();
                universities.add(university);
                university.setId(currentRow.getCell(0).getStringCellValue());
                university.setFullName(currentRow.getCell(1).getStringCellValue());
                university.setShortName(currentRow.getCell(2).getStringCellValue());
                university.setYearOfFoundation((int)currentRow.getCell(3).getNumericCellValue());
                university.setMainProfile(StudyProfile.valueOf(
                        StudyProfile.class, currentRow.getCell(4).getStringCellValue()));
                rowCount++;
            }

            logger.info("Successfully read " + universities.size() + " universities from file");
            inputStream.close();
            workbook.close();
            
        } catch (IOException e) {
            logger.severe("IOException occurred while reading universities file: " + e.getMessage());
            throw e;
        }

        return universities;
    }

    /**
     * Считывает сведения о студентах из XLSX-файла.
     *
     * @param filePath путь к Excel-файлу
     * @return список студентов из табличных данных
     * @throws IOException если файл недоступен для чтения или повреждён
     * @throws IllegalArgumentException если лист со студентами не найден
     */
    public static List<Student> readXlsStudents(String filePath) throws IOException {
        logger.info("Starting to read students from file: " + filePath);

        List<Student> students = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Студенты");
            if (sheet == null) {
                logger.severe("Sheet with students not found in file: " + filePath);
                throw new IllegalArgumentException("Sheet with students not found in file: " + filePath);
            }

            Iterator<Row> rows = sheet.iterator();
            rows.next(); // Skip header row

            int rowCount = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Student student = new Student();
                students.add(student);
                student.setUniversityId(currentRow.getCell(0).getStringCellValue());
                student.setFullName(currentRow.getCell(1).getStringCellValue());
                student.setCurrentCourseNumber((int)currentRow.getCell(2).getNumericCellValue());
                student.setAvgExamScore((float)currentRow.getCell(3).getNumericCellValue());
                rowCount++;
            }

            logger.info("Successfully read " + students.size() + " students from file");
            inputStream.close();
            workbook.close();
            
        } catch (IOException e) {
            logger.severe("IOException occurred while reading students file: " + e.getMessage());
            throw e;
        }

        return students;
    }
}
