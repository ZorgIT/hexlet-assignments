@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
content = @`
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Posts</h1>
        <a href="${NamedRoutes.buildPostPath()}" class="btn btn-primary">New Post</a>
    </div>

    @if (page.getPosts().isEmpty())
        <p>No posts here... yet.</p>
    @else
        @for (var post : page.getPosts())
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${post.getName()}</h5>
                    <p class="card-text">${post.getBody()}</p>
                    <a href="${NamedRoutes.postPath(post.getId())}" class="btn btn-primary">View</a>
                </div>
            </div>
        @endfor
    @endif
`, page = page
)