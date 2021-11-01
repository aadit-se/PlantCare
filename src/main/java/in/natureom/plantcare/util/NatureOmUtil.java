package in.natureom.plantcare.util;

import java.util.List;
import java.util.Objects;

public class NatureOmUtil {

    /**
     * This method will check for any kind of Object is valid or not
     * List, Set, Map, Object
     * @param object
     * @return
     */
    public static boolean isObjectValid(Object object) {
        if(Objects.nonNull(object)) {
            if (object instanceof List) {
                List list = (List) object;
                if(list.isEmpty())
                    return false;
            }
            return true;
        }
        return false;
    }
}
