@import exercise.dto.users.UserPage
@param UserPage uPage


<html>
<head>
    <title>Пользователи</title>
    <meta charset="utf-8" />
</head>
<body>
<main>
    <h1>${uPage.getUser().getFirstName()} ${uPage.getUser().getLastName()}</h1>
    <p>${uPage.getUser().toString()}</p>
    <br>
    <h2><a href="/users"></a> Все пользователи</h2>
</main>
</body>
</html>