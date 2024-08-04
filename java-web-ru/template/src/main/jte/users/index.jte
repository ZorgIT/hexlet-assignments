@import exercise.dto.users.UsersPage
@param UsersPage uPage


<html>
<head>
    <title>Пользователи</title>
    <meta charset="utf-8" />
</head>
<body>
<h1>${uPage.getHeader()}</h1>
@if(uPage.getUsers().isEmpty())
    <p>Пока не добавлено ни одного пользователя</p>
@else
    @for(var user : uPage.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getFirstName()
} ${user.getLastName()}</a></h2>
            <p>${user.toString()}</p>
        </div>
    @endfor
@endif
</body>
</html>