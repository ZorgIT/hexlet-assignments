@import exercise.dto.users.UsersPage
@param UsersPage uPage
<!DOCTYPE html>

<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Пользователи</title>
</head>
<body>
<div class="containter mt-5">
    <h1>${uPage.getHeader()}</h1>
    <table class="table table-bordered">
        @if(uPage.getUsers().isEmpty())
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
                @for(var user : uPage.getUsers())
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

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</body>
</html>