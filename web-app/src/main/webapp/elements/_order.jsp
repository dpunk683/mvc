<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<center>
	<div>
		<table width=100%>
			<tr>
				<td align="center"><img src="images/dishes/order.png" /></td>
				<td align="center"><h1>
						<fmt:message key="order.title" />
					</h1></td>
				<td align="center"><img src="images/dishes/order.png" /></td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${empty sessionScope.order.list}">
				<p>
					<fmt:message key="order.empty" />
				</p>
			</c:when>
			<c:otherwise>
				<table border=1>
					<c:forEach var="menu" items="${sessionScope.order.list}">
						<tr>
							<td><b>${menu.name}</b> <b>•</b> ${menu.about}</td>
							<c:choose>
								<c:when test="${menu.secondPrice>0}">
									<td style="padding-left: 10px; padding-right: 10px"><b>
											${menu.secondPrice}</b></td>
								</c:when>
								<c:otherwise>
									<td style="padding-left: 10px; padding-right: 10px"><b>
											${menu.price}</b></td>
								</c:otherwise>
							</c:choose>
							<td>
								<form action="action" method="post" class="clearform">
									<input type="hidden" name="an" value="removefromorder" /> <input
										type="hidden" name="id" value="${menu.id}" /> <input
										type="submit" alt="<fmt:message key="order.remove.button"/>"
										id="RemoveButton" />
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
				<center>
					<form action="action" method="post" class="clearform">
						<input type="hidden" name="an" value="confirmorder" />
						<button type="submit" name="submit" class="buttonfixed buttonBlue">
							<fmt:message key="order.continue" />
						</button>
					</form>
				</center>
			</c:otherwise>
		</c:choose>
	</div>
</center>