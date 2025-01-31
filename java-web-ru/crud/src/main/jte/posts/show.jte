@import exercise.util.NamedRoutes
@import gg.jte.Content
@param exercise.dto.posts.PostPage page

@template.layout.page(
content = @`
    <h1>${page.getPost().getName()}</h1>
    <p>${page.getPost().getBody()}</p>

    <a href="${NamedRoutes.postsPath()}">Вернуться к списку постов</a>
`
)