import model.Student;
import model.University;
import enums.StudyProfile;
import comparators.StudentComparator;
import comparators.UniversityComparator;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;
import util.ComparatorUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Демонстрация работы фабрики компараторов на тестовых данных студентов и университетов.
 */
public class TestComparators {

    /**
     * Генерирует тестовые коллекции и выводит результат сортировки с помощью разных компараторов.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        // Create test data
        List<Student> students = Arrays.asList(
            new Student().setFullName("Иванов Иван Иванович").setUniversityId("MSU").setCurrentCourseNumber(3).setAvgExamScore(4.5f),
            new Student().setFullName("Петров Петр Петрович").setUniversityId("SPbU").setCurrentCourseNumber(2).setAvgExamScore(4.8f),
            new Student().setFullName("Сидорова София Дмитриевна").setUniversityId("MSU").setCurrentCourseNumber(4).setAvgExamScore(4.2f)
        );

        List<University> universities = Arrays.asList(
            new University().setId("MSU").setFullName("Московский государственный университет").setShortName("МГУ").setYearOfFoundation(1755).setMainProfile(StudyProfile.MATHEMATICS),
            new University().setId("SPbU").setFullName("Санкт-Петербургский государственный университет").setShortName("СПбГУ").setYearOfFoundation(1724).setMainProfile(StudyProfile.PHYSICS),
            new University().setId("BMSTU").setFullName("Московский государственный технический университет имени Баумана").setShortName("МГТУ").setYearOfFoundation(1830).setMainProfile(StudyProfile.COMPUTER_SCIENCE)
        );

        // Test Student comparators
        System.out.println("=== Testing Student Comparators ===");
        
        StudentComparator nameComparator = ComparatorUtil.getStudentComparator(StudentComparatorType.FULL_NAME);
        System.out.println("\nStudents sorted by full name:");
        students.stream().sorted(nameComparator).forEach(System.out::println);

        StudentComparator scoreComparator = ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);
        System.out.println("\nStudents sorted by average exam score (descending):");
        students.stream().sorted(scoreComparator).forEach(System.out::println);

        // Test University comparators
        System.out.println("\n=== Testing University Comparators ===");
        
        UniversityComparator universityNameComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorType.FULL_NAME);
        System.out.println("\nUniversities sorted by full name:");
        universities.stream().sorted(universityNameComparator).forEach(System.out::println);

        UniversityComparator yearComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorType.YEAR_OF_FOUNDATION);
        System.out.println("\nUniversities sorted by year of foundation:");
        universities.stream().sorted(yearComparator).forEach(System.out::println);
    }
}
