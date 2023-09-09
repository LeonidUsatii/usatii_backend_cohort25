<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Events</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>Название</th>
            <th>Начало</th>
            <th>Окончание</th>
        </tr>
        <#list eventsList as event>
            <tr>
                <td>${event.title}</td>
                <td>${event.startDate}</td>
                <td>${event.expirationDate}</td>
            </tr>
        </#list>

    </table>

</div>

</body>
</html>