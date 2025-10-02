package comparators;

import model.Student;

/**
 * Компаратор студентов по номеру текущего курса в порядке возрастания.
 */
public class StudentCurrentCourseNumberComparator implements StudentComparator {
    
    /**
     * Сравнивает студентов по номеру курса.
     *
     * @param s1 первый студент
     * @param s2 второй студент
     * @return отрицательное значение, если s1 обучается на более раннем курсе, положительное — если на более позднем
     */
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getCurrentCourseNumber(), s2.getCurrentCourseNumber());
    }
}
