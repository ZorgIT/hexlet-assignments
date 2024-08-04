package exercise;

import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;import exercise.model.User;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

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
            var header = "Список пользователей";
            var usersPage = new UsersPage(USERS, header);
            ctx.render("users/index.jte", model("uPage", usersPage));
        });

        app.get("/users/{id}", ctx -> {
            var userId = ctx.pathParamAsClass("id", Integer.class).get();
            if (userId < 0 || userId >= USERS.size()) {
                throw new NotFoundResponse("User not found");
            }
            var user = USERS.get(userId);

            var uPage = new UserPage(user);
            ctx.render("users/show.jte", model("uPage", uPage));
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
