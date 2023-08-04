<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>지하철역과의 거리</title>
</head>
<body>

    <c:forEach items="${subway}" var="dto">
        ${dto.name},${dto.line},${dto.x},${dto.y} <br>
    </c:forEach>

</body>
</html>

