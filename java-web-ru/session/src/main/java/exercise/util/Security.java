package exercise.util;

import com.google.common.hash.Hashing;
import io.javalin.http.Context;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Security {
    public static String encrypt(String password) {
        var hashed = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        return hashed;
    }

    private static boolean isPublicRoute(Context ctx) {
        // Список публичных маршрутов, которые не требуют аутентификации
        var publicRoutes = List.of(
                NamedRoutes.buildSessionPath(), // Страница входа (GET)
                NamedRoutes.loginPath(),        // Обработка входа (POST)
                "/"                             // Главная страница
        );

        // Проверяем, является ли текущий маршрут публичным
        return publicRoutes.contains(ctx.path());
    }

    public static boolean checkAuthentication(Context ctx) {
        var currentUser = ctx.sessionAttribute("currentUser");
        if (currentUser == null && !isPublicRoute(ctx)) {
            ctx.redirect(NamedRoutes.buildSessionPath());
            return false; // Не авторизован
        }
        return true; // Авторизован
    }
}
