package comparators;

import model.Student;

public class StudentAvgExamScoreComparator implements StudentComparator {
    
    @Override
    public int compare(Student s1, Student s2) {
        // Descending order - compare s2 to s1
        return Float.compare(s2.getAvgExamScore(), s1.getAvgExamScore());
    }
}