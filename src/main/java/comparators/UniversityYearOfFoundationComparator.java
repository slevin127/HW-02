package comparators;

import model.University;

/**
 * Компаратор университетов по году основания в порядке возрастания.
 */
public class UniversityYearOfFoundationComparator implements UniversityComparator {
    
    /**
     * Сравнивает университеты по году основания.
     *
     * @param u1 первый университет
     * @param u2 второй университет
     * @return отрицательное значение, если u1 основан раньше, положительное — если позже, ноль при совпадении
     */
    @Override
    public int compare(University u1, University u2) {
        return Integer.compare(u1.getYearOfFoundation(), u2.getYearOfFoundation());
    }
}
