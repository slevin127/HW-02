import io.XlsReader;
import model.Student;
import model.University;
import comparators.StudentComparator;
import comparators.UniversityComparator;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;
import util.ComparatorUtil;

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
    }
}
