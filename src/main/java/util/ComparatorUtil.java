package util;

import comparators.*;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;

/**
 * Утилита фабричных методов для получения готовых компараторов студентов и университетов.
 */
public class ComparatorUtil {
    
    /**
     * Прячет конструктор и запрещает создавать экземпляры утилитного класса.
     */
    private ComparatorUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Возвращает компаратор студентов, соответствующий указанному типу сортировки.
     *
     * @param type требуемый тип сравнения студентов
     * @return реализация {@link StudentComparator}
     * @throws IllegalArgumentException если тип компаратора не поддерживается
     */
    public static StudentComparator getStudentComparator(StudentComparatorType type) {
        switch (type) {
            case FULL_NAME:
                return new StudentFullNameComparator();
            case UNIVERSITY_ID:
                return new StudentUniversityIdComparator();
            case CURRENT_COURSE_NUMBER:
                return new StudentCurrentCourseNumberComparator();
            case AVG_EXAM_SCORE:
                return new StudentAvgExamScoreComparator();
            default:
                throw new IllegalArgumentException("Unknown comparator type: " + type);
        }
    }
    
    /**
     * Возвращает компаратор университетов для указанного способа сортировки.
     *
     * @param type требуемый тип сравнения университетов
     * @return реализация {@link UniversityComparator}
     * @throws IllegalArgumentException если тип компаратора не поддерживается
     */
    public static UniversityComparator getUniversityComparator(UniversityComparatorType type) {
        switch (type) {
            case ID:
                return new UniversityIdComparator();
            case FULL_NAME:
                return new UniversityFullNameComparator();
            case SHORT_NAME:
                return new UniversityShortNameComparator();
            case YEAR_OF_FOUNDATION:
                return new UniversityYearOfFoundationComparator();
            case MAIN_PROFILE:
                return new UniversityMainProfileComparator();
            default:
                throw new IllegalArgumentException("Unknown comparator type: " + type);
        }
    }
}
