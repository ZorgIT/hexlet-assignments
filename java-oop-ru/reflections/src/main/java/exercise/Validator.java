package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> failsValidate = new ArrayList<>();
        var fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    var value = field.get(address);

                    if (value == null || String.valueOf(value).isEmpty()) {
                        failsValidate.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return failsValidate;
    }
}
// END
