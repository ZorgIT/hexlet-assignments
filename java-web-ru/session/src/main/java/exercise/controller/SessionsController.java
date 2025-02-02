package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;

import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        var page = new LoginPage("Вход", null);
        ctx.render("build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var password = ctx.formParam("password");

        var user = UsersRepository.getEntities().stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (user == null || !encrypt(password).equals(user.getPassword())) {
            var page = new LoginPage(name, "Wrong username or password");
            System.out.println(page.getError());
            ctx.render("build.jte", model("page", page)); // Ensure "page" is passed
            return;
        }

        ctx.sessionAttribute("currentUser", user.getName());
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void destroy(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
