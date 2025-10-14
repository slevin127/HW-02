import io.XlsReader;
import io.XlsWriter;
import model.Statistics;
import model.Student;
import model.University;
import comparators.StudentComparator;
import comparators.UniversityComparator;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;
import util.ComparatorUtil;
import util.StatisticsUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;

/**
 * Точка входа демонстрационного приложения: считывает данные, строит статистику, выводит информацию и формирует отчёт.
 */
public class Boot {

    private static final Logger logger = Logger.getLogger(Boot.class.getName());

    /**
     * Запускает обработку данных: читает XLSX, сортирует коллекции, строит статистику и сохраняет отчёт.
     *
     * @param args аргументы командной строки (не используются)
     * @throws IOException если чтение исходных файлов или запись отчёта завершается ошибкой
     */
    public static void main(String[] args) throws IOException {
        
        // Configure logging from properties file
        try (InputStream configFile = Boot.class.getClassLoader().getResourceAsStream("logging.properties")) {
            if (configFile != null) {
                LogManager.getLogManager().readConfiguration(configFile);
            }
        } catch (Exception e) {
            System.err.println("Could not load logging configuration: " + e.getMessage());
        }

        logger.info("Starting application data processing");

        List<University> universities =
                XlsReader.readXlsUniversities("src/main/resources/universityInfo.xlsx");
        logger.info("Successfully loaded " + universities.size() + " universities from file");

        // Получаем компаратор университетов по полному названию
        UniversityComparator universityComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorType.FULL_NAME);

        logger.info("Universities sorted by full name:");
        universities.stream()
                .sorted(universityComparator)
                .forEach(university -> logger.info(university.toString()));

        List<Student> students =
                XlsReader.readXlsStudents("src/main/resources/universityInfo.xlsx");
        logger.info("Successfully loaded " + students.size() + " students from file");

        // Получаем компаратор студентов по среднему баллу
        StudentComparator studentComparator = ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);

        logger.info("Students sorted by average exam score (descending):");
        students.stream()
                .sorted(studentComparator)
                .forEach(student -> logger.info(student.toString()));

        // Статистика по профилям обучения
        logger.info("=== STUDY PROFILE STATISTICS ===");
        List<Statistics> statistics = StatisticsUtil.calculateStatistics(students, universities);
        logger.info("Calculated statistics for " + statistics.size() + " study profiles");
        statistics.forEach(stat -> logger.info(stat.toString()));

        String reportPath = "target/statistics-report.xlsx";
        XlsWriter.writeStatistics(statistics, reportPath);
        logger.info("Statistics report generated: " + reportPath);
    }
}
