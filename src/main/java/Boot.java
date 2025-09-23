import io.XlsReader;
import model.Student;
import model.University;
import comparators.StudentComparator;
import comparators.UniversityComparator;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;
import util.ComparatorUtil;
import util.JsonUtil;

import java.io.IOException;
import java.util.List;

public class Boot {

    public static void main(String[] args) throws IOException {

        List<University> universities =
                XlsReader.readXlsUniversities("src/main/resources/universityInfo.xlsx");

        // Get university comparator by type
        UniversityComparator universityComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorType.FULL_NAME);

        // Use Stream API with sorting and method reference for output
        System.out.println("Universities sorted by full name:");
        universities.stream()
                .sorted(universityComparator)
                .forEach(System.out::println);

        List<Student> students =
                XlsReader.readXlsStudents("src/main/resources/universityInfo.xlsx");

        // Get student comparator by type
        StudentComparator studentComparator = ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);

        // Use Stream API with sorting and method reference for output
        System.out.println("\nStudents sorted by average exam score (descending):");
        students.stream()
                .sorted(studentComparator)
                .forEach(System.out::println);

        // JSON Operations
        System.out.println("\n=== JSON SERIALIZATION/DESERIALIZATION DEMO ===");

        // Serialize collections to JSON
        System.out.println("\n--- Serializing Universities Collection ---");
        String universitiesJson = JsonUtil.serializeUniversityList(universities);
        System.out.println(universitiesJson);

        System.out.println("\n--- Serializing Students Collection ---");
        String studentsJson = JsonUtil.serializeStudentList(students);
        System.out.println(studentsJson);

        // Deserialize JSON back to collections
        System.out.println("\n--- Deserializing Collections ---");
        List<University> deserializedUniversities = JsonUtil.deserializeUniversityList(universitiesJson);
        List<Student> deserializedStudents = JsonUtil.deserializeStudentList(studentsJson);

        // Compare sizes to verify correctness
        System.out.println("Original universities count: " + universities.size());
        System.out.println("Deserialized universities count: " + deserializedUniversities.size());
        System.out.println("Universities deserialization correct: " + (universities.size() == deserializedUniversities.size()));

        System.out.println("Original students count: " + students.size());
        System.out.println("Deserialized students count: " + deserializedStudents.size());
        System.out.println("Students deserialization correct: " + (students.size() == deserializedStudents.size()));

        // Stream API operations for individual objects
        System.out.println("\n--- Stream API Individual Serialization/Deserialization ---");

        System.out.println("\nUniversities individual processing:");
        universities.stream()
                .limit(3) // Process only first 3 for brevity
                .map(JsonUtil::serializeUniversity)
                .peek(json -> System.out.println("Serialized: " + json))
                .map(JsonUtil::deserializeUniversity)
                .forEach(university -> System.out.println("Deserialized: " + university));

        System.out.println("\nStudents individual processing:");
        students.stream()
                .limit(3) // Process only first 3 for brevity
                .map(JsonUtil::serializeStudent)
                .peek(json -> System.out.println("Serialized: " + json))
                .map(JsonUtil::deserializeStudent)
                .forEach(student -> System.out.println("Deserialized: " + student));
    }
}
