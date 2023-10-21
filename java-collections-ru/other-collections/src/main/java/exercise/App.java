package exercise;

import java.util.*;

// BEGIN
class App {
    public static LinkedHashMap<String, String>  genDiff(Map<String, ?> dict1,
                            Map<String, ?> dict2) {
        //Объединенное множество ключей из двух словарей
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(dict1.keySet());
        allKeys.addAll(dict2.keySet());

        //Сортировка в алфавитном порядке
        List<String> sortedKeys = new ArrayList<>(allKeys);
        Collections.sort(sortedKeys);

        LinkedHashMap<String, String> diffMap = new LinkedHashMap<>();
        for (String key: sortedKeys
             ) {
            Object value1 = dict1.get(key);
            Object value2 = dict2.get(key);

            if (value1 == null && value2 != null) {
                diffMap.put(key, "added");
            } else if (value1 != null && value2 == null) {
                diffMap.put(key, "deleted");
            } else if (value1 != null && value2 != null) {
                if (value1.equals(value2)) {
                    diffMap.put(key, "unchanged");
                } else {
                    diffMap.put(key, "changed");
                }
            }
        }
        return diffMap;
    }

//
//    public static boolean isAdded(Map<String, Object> map1,
//                                  Map.Entry<String, Object> entry2) {
//        //Ключ отсутствовал в первом массиве, но был добавлен во второй
//        return false;
//    }
//
//    public static boolean isDeleted(Map.Entry<String, Object> entry1,
//                                  Map<String, Object> map2) {
//        //Ключ был в первом массиве, но отсутствует во втором
//        return false;
//    }
//
//    public static boolean isChanged(Map.Entry<String, Object> entry1,
//                                  Map.Entry<String, Object> entry2) {
//        //ключ присутствовал и в первом и во втором массиве, но значения
//        // отличаются
//        return false;
//    }
//
//    public static boolean isUnchanged(Map.Entry<String, Object> entry1,
//                                    Map.Entry<String, Object> entry2) {
//        //ключ присутствовал и в первом и во втором массиве с одинаковыми
//        // значениями
//        return entry1 == entry2;
//    }
}

//END
