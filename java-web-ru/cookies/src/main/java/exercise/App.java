package exercise;

import io.javalin.Javalin;
import exercise.controller.UsersController;
import exercise.util.NamedRoutes;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        // Маршрут для регистрации пользователя
        app.post(NamedRoutes.usersPath(), UsersController::create);

        // Маршрут для отображения страницы регистрации
        app.get(NamedRoutes.buildUserPath(), UsersController::build);

        // Маршрут для отображения данных пользователя
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
