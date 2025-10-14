import model.Student;
import model.University;
import enums.StudyProfile;
import util.JsonUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Набор демонстрационных сценариев для проверки сериализации и десериализации моделей в JSON.
 */
public class JsonTest {
    
    /**
     * Выполняет последовательность тестов по преобразованию объектов и коллекций в JSON и обратно.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        System.out.println("=== JSON SERIALIZATION/DESERIALIZATION TEST ===");
        
        // Create test data
        Student student1 = new Student()
                .setFullName("John Doe")
                .setUniversityId("UNIV001")
                .setCurrentCourseNumber(3)
                .setAvgExamScore(85.5f);
                
        Student student2 = new Student()
                .setFullName("Jane Smith")
                .setUniversityId("UNIV002")
                .setCurrentCourseNumber(2)
                .setAvgExamScore(92.0f);
        
        University university1 = new University()
                .setId("UNIV001")
                .setFullName("Harvard University")
                .setShortName("Harvard")
                .setYearOfFoundation(1636)
                .setMainProfile(StudyProfile.LINGUISTICS);
                
        University university2 = new University()
                .setId("UNIV002")
                .setFullName("Massachusetts Institute of Technology")
                .setShortName("MIT")
                .setYearOfFoundation(1861)
                .setMainProfile(StudyProfile.PHYSICS);
        
        List<Student> students = Arrays.asList(student1, student2);
        List<University> universities = Arrays.asList(university1, university2);
        
        // Test individual object serialization
        System.out.println("\n--- Individual Student Serialization ---");
        String studentJson = JsonUtil.serializeStudent(student1);
        System.out.println(studentJson);
        
        System.out.println("\n--- Individual University Serialization ---");
        String universityJson = JsonUtil.serializeUniversity(university1);
        System.out.println(universityJson);
        
        // Test collection serialization
        System.out.println("\n--- Students Collection Serialization ---");
        String studentsJson = JsonUtil.serializeStudentList(students);
        System.out.println(studentsJson);
        
        System.out.println("\n--- Universities Collection Serialization ---");
        String universitiesJson = JsonUtil.serializeUniversityList(universities);
        System.out.println(universitiesJson);
        
        // Test deserialization
        System.out.println("\n--- Deserialization Test ---");
        Student deserializedStudent = JsonUtil.deserializeStudent(studentJson);
        University deserializedUniversity = JsonUtil.deserializeUniversity(universityJson);
        List<Student> deserializedStudents = JsonUtil.deserializeStudentList(studentsJson);
        List<University> deserializedUniversities = JsonUtil.deserializeUniversityList(universitiesJson);
        
        System.out.println("Original student: " + student1);
        System.out.println("Deserialized student: " + deserializedStudent);
        System.out.println("Student deserialization correct: " + student1.getFullName().equals(deserializedStudent.getFullName()));
        
        System.out.println("\nOriginal university: " + university1);
        System.out.println("Deserialized university: " + deserializedUniversity);
        System.out.println("University deserialization correct: " + university1.getFullName().equals(deserializedUniversity.getFullName()));
        
        System.out.println("\nOriginal students count: " + students.size());
        System.out.println("Deserialized students count: " + deserializedStudents.size());
        System.out.println("Students collection deserialization correct: " + (students.size() == deserializedStudents.size()));
        
        System.out.println("Original universities count: " + universities.size());
        System.out.println("Deserialized universities count: " + deserializedUniversities.size());
        System.out.println("Universities collection deserialization correct: " + (universities.size() == deserializedUniversities.size()));
        
        // Test Stream API operations
        System.out.println("\n--- Stream API Individual Processing ---");
        System.out.println("Students:");
        students.stream()
                .map(JsonUtil::serializeStudent)
                .peek(json -> System.out.println("Serialized: " + json))
                .map(JsonUtil::deserializeStudent)
                .forEach(student -> System.out.println("Deserialized: " + student));
        
        System.out.println("\nUniversities:");
        universities.stream()
                .map(JsonUtil::serializeUniversity)
                .peek(json -> System.out.println("Serialized: " + json))
                .map(JsonUtil::deserializeUniversity)
                .forEach(university -> System.out.println("Deserialized: " + university));
        
        System.out.println("\n=== TEST COMPLETED SUCCESSFULLY ===");
    }
}
