<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Index</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <div class="jumbotron" style="margin-top: 20px;">
        <h2>Simple BANK aplication</h2>
        <sec:authorize access="!isAuthenticated()">
            <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <p>Ваш логин: <sec:authentication property="principal.username"/></p>

            <p>Выберите, пожалуйста, интересующее вас действие:</p>
            <p><a class="btn btn-sm btn-info" href="<c:url value="/getAccountBalance" />" role="button">Посмотреть
                текущий баланс</a></p>
            <p><a class="btn btn-sm btn-info" href="<c:url value="/addAccountBalance" />" role="button">Внести
                деньги</a></p>
            <p><a class="btn btn-sm btn-info" href="<c:url value="/withdrawAccountBalance" />" role="button">Вывести
                деньги</a></p>
            <p><a class="btn btn-sm btn-info" href="<c:url value="/reportTransaction" />" role="button">Отчет по
                транзакциям</a></p>
            <p><a class="btn btn-sm btn-info" href="<c:url value="/transferMoneyPage" />" role="button">Перевести
                другому пользователю</a></p>

            <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>

        </sec:authorize>
    </div>

</div>
</body>
</html>