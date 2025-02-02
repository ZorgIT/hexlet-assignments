@import exercise.dto.BasePage
@import gg.jte.Content

@param BasePage page
@param Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hexlet Blog</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    @if (page.getFlash() != null)
        <div class="alert alert-success">${page.getFlash()}</div>
    @endif
    ${content}
</div>
</body>
</html>