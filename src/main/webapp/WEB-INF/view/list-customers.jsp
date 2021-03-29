<%--
  Created by IntelliJ IDEA.
  User: marcin
  Date: 27.03.2021
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>List of the Customers</title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css"/>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>CRM - The best manager</h2>
            </div>
            <div id="container">
                <div id="content">
                    <input class="addButton" type="button" value="Add new customer"
                           onclick="window.location.href='showAddForm'; return false;">
                    <table>
                        <tr>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>email</th>
                            <th>action</th>
                        </tr>
                        <c:forEach var="customer" items="${customers}">
                            <c:url var="updateURL" value="/customer/customerUpdate">
                                <c:param name="customerId" value="${customer.id}"/>
                            </c:url>
                            <c:url var="deleteURL" value="/customer/delete">
                                <c:param name="customerId" value="${customer.id}"/>
                            </c:url>
                            <tr>
                                <td>${customer.firstName}</td>
                                <td>${customer.lastName}</td>
                                <td>${customer.email}</td>
                                <td>
                                    <a href="${updateURL}">Edit</a>
                                    |
                                    <a href="${deleteURL}" onclick="return confirm('Ar ju siur?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
