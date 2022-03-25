<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

	<div class="container">
		<H1>Lista de Pedidos Atuais</H1>
		<br>
		<table class="table table-bordered table-striped table-hover">
			<thead>
			<th>ID</th>
			<th>Valor</th>
			<th>Data Pedido</th>
			<th>Títulos</th>
			</thead>
			<c:forEach items="${pedidos }" var="pedido">
				<tr>
					<td>${pedido.id }</td>
					<td>
						<fmt:setLocale value = "pt_BR"/>
						<fmt:formatNumber value = "${pedido.valor }" type = "currency"/>
					</td>
					<td><fmt:formatDate value="${pedido.data.time }" pattern="dd/MM/yy"/></td>
					<td>
						<c:forEach items="${pedido.produtos }" var="produto">
							${produto.titulo }
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</table>
		<br>
	</div>

</tags:pageTemplate>
