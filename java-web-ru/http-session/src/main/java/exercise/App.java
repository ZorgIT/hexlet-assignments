package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("users", ctx -> {
            var pageNum =
                    ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            var perPageNum =
                    ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
            List<Map<String, String>> result = new ArrayList<>();
            var lowerBound = pageNum * perPageNum - perPageNum;
            var upperBound = lowerBound + perPageNum;
            for (int i = lowerBound; i < upperBound; i++) {
                result.add(USERS.get(i));
            }
            ctx.json(result);
        });
        // END
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
