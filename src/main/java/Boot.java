import io.XlsReader;
import io.XlsWriter;
import io.XmlWriter;
import io.JsonWriter;
import model.Root;
import model.Statistics;
import model.Student;
import model.University;
import comparators.StudentComparator;
import comparators.UniversityComparator;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;
import util.ComparatorUtil;
import util.StatisticsUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
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

        // Создаём корневой объект для XML и JSON экспорта
        LocalDateTime processedAt = LocalDateTime.now();
        Root rootData = new Root(students, universities, statistics, processedAt);
        logger.info("Created root object for XML/JSON export with " + 
                   students.size() + " students, " + 
                   universities.size() + " universities, " + 
                   statistics.size() + " statistics entries");

        // Генерируем XML файл
        try {
            XmlWriter.writeXml(rootData);
            logger.info("XML file generation completed successfully");
        } catch (JAXBException e) {
            logger.log(Level.SEVERE, "Failed to generate XML file: " + e.getMessage(), e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write XML file to disk: " + e.getMessage(), e);
        }

        // Генерируем JSON файл
        try {
            JsonWriter.writeJson(rootData);
            logger.info("JSON file generation completed successfully");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write JSON file to disk: " + e.getMessage(), e);
        }

        logger.info("Application processing completed successfully");
    }
}
