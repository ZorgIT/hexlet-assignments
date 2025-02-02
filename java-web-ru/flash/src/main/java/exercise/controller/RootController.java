package exercise.controller;

import exercise.dto.BasePage;
import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {
    public static void index(Context ctx) {
        var page = new BasePage(); // Создаем объект BasePage
        ctx.render("index.jte", model("page", page)); // Передаем его в шаблон
    }
}
