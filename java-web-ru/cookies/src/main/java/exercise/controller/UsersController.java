package exercise.controller;

import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;

public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    public static void create(Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");

        // Генерация токена
        var token = Security.generateToken();

        // Создание пользователя
        var user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);

        // Установка токена в куки
        ctx.cookie("token", token);

        // Редирект на страницу пользователя
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User not found"));

        // Проверка токена
        var token = ctx.cookie("token");
        if (!user.getToken().equals(token)) {
            ctx.redirect(NamedRoutes.buildUserPath());
            return;
        }

        // Отображение данных пользователя
        var page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }
}