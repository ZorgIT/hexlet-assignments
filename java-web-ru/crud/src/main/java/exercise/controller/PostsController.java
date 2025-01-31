package exercise.controller;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import static io.javalin.rendering.template.TemplateUtil.model;

public class PostsController {

    // Обработчик для просмотра списка постов с пагинацией
    public static void index(Context ctx) {
        var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var pageSize = 5; // Количество постов на странице
        var posts = PostRepository.findAll(page, pageSize);
        var postsPage = new PostsPage(posts, page);
        ctx.render("posts/index.jte", model("page", postsPage));
    }

    // Обработчик для просмотра конкретного поста
    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        var postPage = new PostPage(post);
        ctx.render("posts/show.jte", model("page", postPage));
    }
}