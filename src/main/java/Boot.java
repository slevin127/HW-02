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
import util.JsonUtil;
import util.StatisticsUtil;

import java.io.IOException;
import java.util.List;

/**
 * Точка входа демонстрационного приложения: считывает данные, строит статистику, выводит информацию и формирует отчёт.
 */
public class Boot {

    /**
     * Запускает обработку данных: читает XLSX, сортирует коллекции, строит статистику и сохраняет отчёт.
     *
     * @param args аргументы командной строки (не используются)
     * @throws IOException если чтение исходных файлов или запись отчёта завершается ошибкой
     */
    public static void main(String[] args) throws IOException {

        List<University> universities =
                XlsReader.readXlsUniversities("src/main/resources/universityInfo.xlsx");

        // Получаем компаратор университетов по полному названию
        UniversityComparator universityComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorType.FULL_NAME);

        System.out.println("Universities sorted by full name:");
        universities.stream()
                .sorted(universityComparator)
                .forEach(System.out::println);

        List<Student> students =
                XlsReader.readXlsStudents("src/main/resources/universityInfo.xlsx");

        // Получаем компаратор студентов по среднему баллу
        StudentComparator studentComparator = ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);

        System.out.println("\nStudents sorted by average exam score (descending):");
        students.stream()
                .sorted(studentComparator)
                .forEach(System.out::println);

        // Статистика по профилям обучения
        System.out.println("\n=== STUDY PROFILE STATISTICS ===");
        List<Statistics> statistics = StatisticsUtil.calculateStatistics(students, universities);
        statistics.forEach(System.out::println);

        String reportPath = "target/statistics-report.xlsx";
        XlsWriter.writeStatistics(statistics, reportPath);
        System.out.println("Statistics report generated: " + reportPath);

        System.out.println("\n=== JSON SERIALIZATION/DESERIALIZATION DEMO ===");

        System.out.println("\n--- Serializing Universities Collection ---");
        String universitiesJson = JsonUtil.serializeUniversityList(universities);
        System.out.println(universitiesJson);

        System.out.println("\n--- Serializing Students Collection ---");
        String studentsJson = JsonUtil.serializeStudentList(students);
        System.out.println(studentsJson);

        System.out.println("\n--- Deserializing Collections ---");
        List<University> deserializedUniversities = JsonUtil.deserializeUniversityList(universitiesJson);
        List<Student> deserializedStudents = JsonUtil.deserializeStudentList(studentsJson);

        System.out.println("Original universities count: " + universities.size());
        System.out.println("Deserialized universities count: " + deserializedUniversities.size());
        System.out.println("Universities deserialization correct: " + (universities.size() == deserializedUniversities.size()));

        System.out.println("Original students count: " + students.size());
        System.out.println("Deserialized students count: " + deserializedStudents.size());
        System.out.println("Students deserialization correct: " + (students.size() == deserializedStudents.size()));

        System.out.println("\n--- Stream API Individual Serialization/Deserialization ---");

        System.out.println("\nUniversities individual processing:");
        universities.stream()
                .limit(3)
                .map(JsonUtil::serializeUniversity)
                .peek(json -> System.out.println("Serialized: " + json))
                .map(JsonUtil::deserializeUniversity)
                .forEach(university -> System.out.println("Deserialized: " + university));

        System.out.println("\nStudents individual processing:");
        students.stream()
                .limit(3)
                .map(JsonUtil::serializeStudent)
                .peek(json -> System.out.println("Serialized: " + json))
                .map(JsonUtil::deserializeStudent)
                .forEach(student -> System.out.println("Deserialized: " + student));
    }
}
