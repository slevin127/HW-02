package comparators;

import model.University;
import org.apache.commons.lang3.StringUtils;

/**
 * Компаратор университетов по краткому названию.
 */
public class UniversityShortNameComparator implements UniversityComparator {
    
    /**
     * Сравнивает университеты по сокращённому названию.
     *
     * @param u1 первый университет
     * @param u2 второй университет
     * @return результат лексикографического сравнения кратких названий
     */
    @Override
    public int compare(University u1, University u2) {
        return StringUtils.compare(u1.getShortName(), u2.getShortName());
    }
}
