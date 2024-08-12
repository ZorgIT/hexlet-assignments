@import exercise.dto.users.UsersPage
@param UsersPage page


@template.layout.page(content = @`
    <div class="container mt-5">
        <form action="/users" method="get">
            <input type="search" name="term" value="${page
                        .getNameS()}" placeholder="Имя пользователя" />
            <input type="submit" value="Search" />
        </form>
    </div>
    <div>
        <table class="table table-bordered">
            @if(page.getUsers().isEmpty())

            @else
                <thead class="thead-light">
                <tr>
                    <th>ID user</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>email</th>
                </tr>
                </thead>
                <thbody>
                    @for(var user : page.getUsers())
                        <tr>
                            <td>${user.getId()}</td>
                            <td>${user.getFirstName()}</td>
                            <td>${user.getLastName()}</td>
                            <td>${user.getEmail()}</td>
                        </tr>
                    @endfor
                </thbody>
            @endif
        </table>
    </div>
`)

