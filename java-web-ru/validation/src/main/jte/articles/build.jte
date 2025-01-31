@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page( content = @`
    @if(page.getErrors() != null)
        <div class="alert alert-danger">
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        </div>
    @endif

    <form action="/articles" method="post">
        <div class="mb-3">
            <label for="title" class="form-label">Наименование статьи</label>
            <input type="text" class="form-control" id="title" name="title" required value="${page.getArticleTitle() != null ? page.getArticleTitle() : ""}">
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">Содержимое статьи</label>
            <input type="text" class="form-control" id="content" name="content" required value="${page.getContent() != null ? page.getContent() : ""}">
        </div>
        <input type="submit" value="Сохранить" />
    </form>
`)