package comparators;

import model.University;
import org.apache.commons.lang3.StringUtils;

/**
 * Компаратор университетов по строковому идентификатору.
 */
public class UniversityIdComparator implements UniversityComparator {
    
    /**
     * Сравнивает университеты по идентификатору.
     *
     * @param u1 первый университет
     * @param u2 второй университет
     * @return результат лексикографического сравнения идентификаторов
     */
    @Override
    public int compare(University u1, University u2) {
        return StringUtils.compare(u1.getId(), u2.getId());
    }
}
