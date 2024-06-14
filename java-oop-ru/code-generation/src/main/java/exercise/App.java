package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

// BEGIN
public class App {
    public static void save(Path path, Car car) {
        try {
            Files.writeString(path, car.serialize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car extract(Path path) {
        try {
            Car car = new Car();
            String fileContent = Files.readString(path);
            return car.unserialize(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
// END
