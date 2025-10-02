package comparators;

import model.Student;

/**
 * Компаратор студентов по среднему баллу экзаменов в порядке убывания.
 */
public class StudentAvgExamScoreComparator implements StudentComparator {
    
    /**
     * Сравнивает двух студентов по среднему баллу, начиная с более высоких значений.
     *
     * @param s1 первый студент
     * @param s2 второй студент
     * @return отрицательное значение, если s1 имеет меньший балл, положительное — если больший, ноль при равенстве
     */
    @Override
    public int compare(Student s1, Student s2) {
        return Float.compare(s2.getAvgExamScore(), s1.getAvgExamScore());
    }
}
