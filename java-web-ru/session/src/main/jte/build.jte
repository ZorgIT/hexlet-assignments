@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(content = @`
    <meta charset="UTF-8">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h1 class="mb-4">${page.getName()}</h1>

                @if(page.getError() != null)
                    <div class="alert alert-danger">
                        <p>${page.getError()}</p>
                    </div>
                @endif

                <form action="${exercise.util.NamedRoutes.loginPath()}" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label">Имя</label>
                        <input type="text"
                               class="form-control ${page.getError() != null ? "is-invalid" : ""}"
                               id="name"
                               name="name"
                               value=""
                               required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Пароль</label>
                        <input type="password"
                               class="form-control ${page.getError() != null ? "is-invalid" : ""}"
                               id="password"
                               name="password"
                               required>
                    </div>

                    <button type="submit" class="btn btn-primary">Войти</button>
                </form>
            </div>
        </div>
    </div>
`)
