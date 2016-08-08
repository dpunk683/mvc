<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<center>
	<div>
		<table width=100%>
			<tr>
				<td align="center"><img src="images/dishes/order.png" /></td>
				<td align="center"><h1>
						<fmt:message key="dayproduct.title" />
					</h1></td>
				<td align="center"><img src="images/dishes/order.png" /></td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${empty requestScope.daydish}">
				<p>
					<fmt:message key="daydish.empty" />
				</p>
			</c:when>
			<c:otherwise>
				<c:if test="${not empty requestScope.message}">
					<p>
						<fmt:message key="${requestScope.message}" />
					</p>
				</c:if>
				<table border=1 width="100%">
					<c:forEach var="daydish" items="${requestScope.daydish}">
						<tr>
							<td id="menuTable"><b><fmt:message key="daydish.name" />${daydish.name}</b></td>
							<td rowspan="3" width="200"><img
								src="images/dishes/${daydish.picture}" width="200" height="200"></td>
						</tr>
						<tr>
							<td id="menuTable"><b><fmt:message key="daydish.about" />${daydish.about}</b></td>
						</tr>
						<tr>
							<td id="menuTable"><b><fmt:message
										key="daydish.actionprice" />${daydish.secondPrice}</b><br /> <b><fmt:message
										key="daydish.regularprice" />${daydish.price}</b><br />
								<form action="action" method="post" class="clearform">
									<input type="hidden" name="an" value="addtoorder" /> <input
										type="hidden" name="id" value="${daydish.id}" />
									<button type="submit" name="submit"
										class="buttonfixed buttonBlue">
										<fmt:message key="product.buybutton" />
									</button>
								</form></td>
						</tr>
					</c:forEach>
				</table>

			</c:otherwise>
		</c:choose>
	</div>
</center>