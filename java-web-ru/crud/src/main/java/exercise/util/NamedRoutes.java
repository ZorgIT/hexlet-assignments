package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String postsPath() {
        return "/posts";
    }

    // Перегрузка для строки (например, "{id}")
    public static String postPath(String id) {
        return "/posts/" + id;
    }

    // Перегрузка для числа (например, 3)
    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }
}