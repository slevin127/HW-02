package comparators;

import model.Student;
import org.apache.commons.lang3.StringUtils;

/**
 * Компаратор студентов по идентификатору университета.
 */
public class StudentUniversityIdComparator implements StudentComparator {
    
    /**
     * Сравнивает студентов по строковому идентификатору университета.
     *
     * @param s1 первый студент
     * @param s2 второй студент
     * @return результат лексикографического сравнения идентификаторов
     */
    @Override
    public int compare(Student s1, Student s2) {
        return StringUtils.compare(s1.getUniversityId(), s2.getUniversityId());
    }
}
