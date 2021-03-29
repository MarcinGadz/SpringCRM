<%--
  Created by IntelliJ IDEA.
  User: marcin
  Date: 28.03.2021
  Time: 01:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - The best manager</h2>
        </div>
    </div>
    Put data of new Customer:
    <%--@elvariable id="customer" type="Customer"--%>
    <form:form action="saveCustomer" modelAttribute="customer" method="post">
        <!--To save that information-->
        <form:hidden path="id"/>
        <label>First name:</label>
        <form:input path="firstName"/>
        <label>Last name:</label>
        <form:input path="lastName"/>
        <label>email:</label>
        <form:input path="email"/>
        <label>Submit!</label>
        <input type="submit" value="Done" class="save"/>
    </form:form>
    <p>
        <a href="${pageContext.request.contextPath}/customer/list">Back</a>
    </p>
</body>
</html>
