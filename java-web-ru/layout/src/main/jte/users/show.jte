@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(content = @`
    <div class="containter mt-5">
        <h1>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h1>
        <p>${page.getUser().toString()}</p>
    </div>`
, title = "Данные пользователя"
)
