@import exercise.dto.posts.PostPage

@param PostPage page

@template.layout.page(
content = @`
    <h1>${page.getPost().getName()}</h1>
    <p>${page.getPost().getBody()}</p>
    <a href="${exercise.util.NamedRoutes.postsPath()}">Back to Posts</a>
`, page = page
)