package comparators;

import model.University;
import org.apache.commons.lang3.StringUtils;

/**
 * Компаратор университетов по полному названию.
 */
public class UniversityFullNameComparator implements UniversityComparator {
    
    /**
     * Сравнивает университеты по строковому значению полного названия.
     *
     * @param u1 первый университет
     * @param u2 второй университет
     * @return результат лексикографического сравнения
     */
    @Override
    public int compare(University u1, University u2) {
        return StringUtils.compare(u1.getFullName(), u2.getFullName());
    }
}
