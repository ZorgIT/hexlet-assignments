package exercise;

import io.javalin.Javalin;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;


public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", ctx -> {
            var term =
                    ctx.queryParamAsClass("term", String.class).getOrDefault(
                            "");
            var filteredUsers =
                        USERS.stream()
                                .filter(user -> user.getFirstName()
                                        .toLowerCase().contains(term.toLowerCase()))
                                .sorted(Comparator.comparingLong(User::getId))
                                .collect(Collectors.toList());

            filteredUsers.forEach(System.out::println);
            var page = new UsersPage(filteredUsers, term);
            ctx.render("users/index.jte", model("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
