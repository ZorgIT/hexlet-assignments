package exercise;

import lombok.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

// BEGIN
@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    public Car unserialize(String line) {
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(line);
        try {
            Car car = mapper.readValue(reader, Car.class);
            return car;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // END
}
