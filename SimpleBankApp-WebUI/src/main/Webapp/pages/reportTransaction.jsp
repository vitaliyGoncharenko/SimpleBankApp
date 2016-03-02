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

    <title>Transaction</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">
</head>

<body>

<div class="container">

    <div class="jumbotron" style="margin-top: 20px;">

        <h3>Отчет по транзакциям</h3>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>#ID</th>
                <th>Amount</th>
            </tr>
            </thead>

            <c:forEach var="transaction" items="${transactions}">
                <tr>
                    <td>${transaction.id}</td>
                    <td>${transaction.amount}</td>
                </tr>
            </c:forEach>
            <p><a class="btn btn-lg btn-primary" href="<c:url value="/back" />" role="button">в главное меню</a></p>
        </table>

    </div>

</div>
</body>
</html>



