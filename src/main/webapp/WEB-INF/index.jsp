<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <title>Tacos</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>

<body>
<div class="container">
    <h2 class="display-1">Registration</h2>
    <div class="card shadow">
        <div class="card-body">
            <form:form action="/register" method="post" modelAttribute="newUser">
                <div class="mb-3">
                    <form:label class="form-label" path="userName">user name</form:label>
                    <form:input class="form-control" path="userName" />
                    <form:errors class="form-text text-warning" path="userName" />
                </div>
                <div class="mb-3">
                    <form:label class="form-label" path="email">email</form:label>
                    <form:input class="form-control" path="email" />
                    <form:errors class="form-text text-warning" path="email" />
                </div>
                <div class="mb-3">
                    <form:label class="form-label" path="password">password</form:label>
                    <form:input class="form-control" path="password" />
                    <form:errors class="form-text text-warning" path="password" />
                </div>
                <div class="mb-3">
                    <form:label class="form-label" path="confirm">confirm password</form:label>
                    <form:input class="form-control" path="confirm" />
                    <form:errors class="form-text text-warning" path="confirm" />
                </div>
                <input class="btn btn-primary" type="submit" value="register">
            </form:form>
        </div>
    </div>

    <h2 class="display-1">Login</h2>
    <div class="card shadow">
        <div class="card-body">
            <form:form action="/login" method="post" modelAttribute="newLogin">
                <div class="mb-3">
                    <form:label class="form-label" path="email">email</form:label>
                    <form:input class="form-control" path="email" />
                    <form:errors class="form-text text-warning" path="email" />
                </div>
                <div class="mb-3">
                    <form:label class="form-label" path="password">password</form:label>
                    <form:input class="form-control" path="password" />
                    <form:errors class="form-text text-warning" path="password" />
                </div>
                <input class="btn btn-primary" type="submit" value="login">
            </form:form>
        </div>
    </div>
</div>
</body>
</html>