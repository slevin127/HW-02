package comparators;

import model.University;
import org.apache.commons.lang3.StringUtils;

public class UniversityMainProfileComparator implements UniversityComparator {
    
    @Override
    public int compare(University u1, University u2) {
        return StringUtils.compare(u1.getMainProfile().getProfileName(), u2.getMainProfile().getProfileName());
    }
}