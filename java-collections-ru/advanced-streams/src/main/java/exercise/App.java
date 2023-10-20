package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    public static String  getForwardedVariables(final String inputStr) {
        List environments = Arrays.stream(inputStr.split("\n"))
                .filter(line -> line.startsWith("environment="))
                .map(line -> line.replace("environment=", "").trim())
                .flatMap(line -> Arrays.stream(line.split(",")))
                .map(line -> line.trim().replaceAll("\"| |,", ""))
                .collect(Collectors.toList());

        List<String> result = (ArrayList) environments.stream()
                .filter(list -> list.toString().startsWith("X_FORWARDED_"))
                .map(list -> list.toString().replaceFirst("X_FORWARDED_", ""))
                .collect(Collectors.toList());

        return result.size() == 1 ? result.get(0) : result.stream()
                .collect(Collectors.joining(","));

    }
}

//END
