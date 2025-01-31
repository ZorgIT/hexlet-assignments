@import exercise.util.NamedRoutes
@import gg.jte.Content
@param exercise.dto.posts.PostsPage page

@template.layout.page(
content = @`
    <h1>Список постов</h1>

    <ul>
        @for (var post : page.getPosts())
            <li>
                <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
            </li>
        @endfor
    </ul>

    <!-- Пагинация -->
    <div>
        @if (page.getCurrentPage() > 1)
            <a href="${NamedRoutes.postsPath()}?page=${page.getCurrentPage() - 1}">Назад</a>
        @endif

        @if (page.getPosts().size() == 5)
            <a href="${NamedRoutes.postsPath()}?page=${page.getCurrentPage() + 1}">Вперед</a>
        @endif
    </div>
`
)