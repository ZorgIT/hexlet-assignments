
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(content = @`
    <div class="container mt-5">
        @if(page.getName() != null)
            <h1>Добро пожаловать, ${page.getName().toString()}!</h1>
            <form action="${exercise.util.NamedRoutes.logoutPath()}" method="post" class="mt-4">
                <button type="submit" class="btn btn-danger">Выйти</button>
            </form>
        @else
            <h1>Добро пожаловать!</h1>
            <a href="${exercise.util.NamedRoutes.buildSessionPath()}" class="btn btn-primary mt-3">Войти</a>
        @endif
        <div class="row justify-content-center">
            <div class="col-md-8 text-center">
                @if(page.getName() != null)
                <h1>Добро пожаловать</h1>
                <form action="${exercise.util.NamedRoutes.logoutPath()}" method="post" class="mt-4">
                    <button type="submit" class="btn btn-danger">Выйти</button>
                </form>
                @else
                <h1>Добро пожаловать!</h1>
                <a href="${exercise.util.NamedRoutes.buildSessionPath()}" class="btn btn-primary mt-3">Войти</a>
                @endif
            </div>
        </div>
    </div>
`)