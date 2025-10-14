package comparators;

import model.University;
import org.apache.commons.lang3.StringUtils;

/**
 * Компаратор университетов по названию основного профиля обучения.
 */
public class UniversityMainProfileComparator implements UniversityComparator {
    
    /**
     * Сравнивает университеты по имени профильного направления.
     *
     * @param u1 первый университет
     * @param u2 второй университет
     * @return результат лексикографического сравнения названий профилей
     */
    @Override
    public int compare(University u1, University u2) {
        return StringUtils.compare(u1.getMainProfile().getProfileName(), u2.getMainProfile().getProfileName());
    }
}
