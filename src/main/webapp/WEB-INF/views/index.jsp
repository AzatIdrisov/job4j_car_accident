<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
Hello : ${user}
<div class="container-fluid">
    <div class="container">
        <div class="row">
            <a href="<c:url value='/create'/>">Добавить инцидент</a>
        </div>
        <table id='table' class="table">
            <thead>
            <tr>
                <th scope="col">№</th>
                <th scope="col">name</th>
                <th scope="col">text</th>
                <th scope="col">address</th>
                <th scope="col">type</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${accidents}" var="accidents" varStatus="counter">
                <tr>
                    <th>${counter.count}</th>
                    <th>${accidents.value.name}</th>
                    <th>${accidents.value.text}</th>
                    <th>${accidents.value.address}</th>
                    <th>${accidents.value.type.name}</th>
                    <td>
                        <span>
                              <a href="<c:url value='/update?id=${accidents.value.id}'/>">Редактировать</a>
                        </span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>