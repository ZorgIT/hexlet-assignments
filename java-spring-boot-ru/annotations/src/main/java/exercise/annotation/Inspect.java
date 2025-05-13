package exercise.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// BEGIN
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Inspect {

}
// END


/*

@Retention(RetentionPolicy.RUNTIME) //жизненный цикл аннотации (как долго
// остается с кодом. В указанном случае - будет жить во время выполнения
@Target(ElementType.METHOD) // Где применяется аннотация (в методах)
public @interface LogExecutionTime {
    boolean enabled();
    long threshold() default 0; // пороговое в мс
}
 */