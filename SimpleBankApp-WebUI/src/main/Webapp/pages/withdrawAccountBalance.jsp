<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Withdraw Balance</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">
</head>

<body>

<div class="container">

    <div class="jumbotron" style="margin-top: 20px;">

        <form:form action="withdrawBalance" method="get" modelAttribute="amount">
            <table>
                <tr>
                    <td valign=top>
                        Введите сумму: <input type="text" name="amount" value="" size=15 maxlength=auto></td>
                <tr>
                    <td colspan="2" align="right"><input type="submit" value="Снять со счета"></td>
                </tr>
            </table>
        </form:form>

        <h3>${amounts}</h3>
        <h3>${withdrawAccountBalanceMessage}</h3>
        <h3>${accountBalanceAfterWithdraw}</h3>
        <p><a class="btn btn-lg btn-primary" href="<c:url value="/back" />" role="button">в главное меню</a></p>
    </div>

</div>
</body>
</html>



