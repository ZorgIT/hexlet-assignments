package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    List<Integer> integers;

//    @BeforeAll
//    static void prepare() {
//        System.out.println("Prepare workaround for test");
//    }

    @BeforeEach
    void initIntegerList() {
        this.integers = new ArrayList<>();
        this.integers.add(10);
        this.integers.add(20);
        this.integers.add(30);
        this.integers.add(40);
        this.integers.add(50);
        this.integers.add(60);
    }
    @Test
    void testTake() {
        // BEGIN
        //Основная функциональность:
        List<Integer> actual1 = App.take(integers,1);
        assertThat(actual1.size()).isEqualTo(1);

        List<Integer> actual3 = App.take(integers,4);
        assertThat(actual3.get(3)).isEqualTo(integers.get(3));

        List<Integer> actual4 = App.take(integers,4);
        assertThat(actual4.get(3)).isEqualTo(40);

        //Пограничные случаи:
        List<Integer> actual5 = App.take(integers,0);
        assertThat(actual5.isEmpty()).isEqualTo(true);

        List<Integer> actual6 = App.take(integers,5);
        assertThat(actual6.get(4)).isEqualTo(integers.get(4));
        // END
    }
}
