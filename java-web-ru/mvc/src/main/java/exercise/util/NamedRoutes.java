package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String postsPath() {
        return "/posts";
    }

    public static String buildPostPath() {
        return "/posts/build";
    }

    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }

    public static String postPath(String id) {
        return "/posts/" + id;
    }

    // BEGIN
    public static String editPostPath(Long id) {
        return editPostPath(String.valueOf(id));
    }

    public static String editPostPath(String id) {
        return "/posts/" + id + "/edit";
    }

    public static String updatePostPath(Long id) {
        return updatePostPath(String.valueOf(id));
    }

    public static String updatePostPath(String id) {
        return "/posts/" + id;
    }
    
    // END
}
