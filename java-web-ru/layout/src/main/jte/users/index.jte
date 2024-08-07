@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page (
content = @`
    <div class="containter mt-5">
        <table class="table table-bordered">
            @if(page.getUsers().isEmpty())
                <p>Пока не добавлено ни одного пользователя</p>
            @else
                <thead class="thead-light">
                <tr>
                    <th>ID пользователя</th>
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>email</th>
                    <th>Страница пользователя</th>
                </tr>
                </thead>
                <thbody>
                    @for(var user : page.getUsers())
                        <tr>
                            <td>${user.getId()}</td>
                            <td>${user.getFirstName()}</td>
                            <td>${user.getLastName()}</td>
                            <td>${user.getEmail()}</td>
                            <td><a href="/users/${user.getId()}">link</a></td>
                        </tr>
                    @endfor
                </thbody>
            @endif
        </table>
    </div>
`,title = "Все пользователи")