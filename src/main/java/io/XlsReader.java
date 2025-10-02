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

/**
 * Утилита чтения XLSX-файлов с исходными данными по университетам и студентам.
 */
public class XlsReader {

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

        List<University> universities = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("�?�?��?��?�?��'��'�<");
        if (sheet == null) {
            throw new IllegalArgumentException("�>��?�' '�?�?��?��?�?��'��'�<' �?�'�?�?�'�?�'�?�?��' �? �"�����>��: " + filePath);
        }

        Iterator<Row> rows = sheet.iterator();
        rows.next();

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

        List<Student> students = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("���'�?�?��?�'�<");
        if (sheet == null) {
            throw new IllegalArgumentException("�>��?�' '���'�?�?��?�'�<' �?�'�?�?�'�?�'�?�?��' �? �"�����>��: " + filePath);
        }

        Iterator<Row> rows = sheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            Student student = new Student();
            students.add(student);
            student.setUniversityId(currentRow.getCell(0).getStringCellValue());
            student.setFullName(currentRow.getCell(1).getStringCellValue());
            student.setCurrentCourseNumber((int)currentRow.getCell(2).getNumericCellValue());
            student.setAvgExamScore((float)currentRow.getCell(3).getNumericCellValue());
        }

        return students;
    }
}
