<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}">Dart</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath}">Główna</a></li>
			<li><a href="${pageContext.request.contextPath}/add">Dodaj
					wpis</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="${pageContext.request.contextPath}/register"><span
					class="glyphicon glyphicon-user"></span> Zarejestruj się</a></li>
			<c:choose>
				<c:when test="${pageContext['request'].userPrincipal != null}">
					<li><a href="${pageContext.request.contextPath}/logout">Wyloguj
							się</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/login">Zaloguj
							się</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>

