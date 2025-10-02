package comparators;

import model.Student;
import org.apache.commons.lang3.StringUtils;

/**
 * Компаратор студентов по полному имени с использованием лексикографического порядка.
 */
public class StudentFullNameComparator implements StudentComparator {
    
    /**
     * Сравнивает студентов по строковому представлению полного имени.
     *
     * @param s1 первый студент
     * @param s2 второй студент
     * @return результат лексикографического сравнения полных имён
     */
    @Override
    public int compare(Student s1, Student s2) {
        return StringUtils.compare(s1.getFullName(), s2.getFullName());
    }
}
