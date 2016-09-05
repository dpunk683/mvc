<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<center>
	<div>
		<table width=100%>
			<tr>
				<td align="center"><img src="images/dishes/order.png" /></td>
				<td align="center"><h1>
						<fmt:message key="menu.watch" />
					</h1></td>
				<td align="center"><img src="images/dishes/order.png" /></td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${empty requestScope.menu}">
				<p>
					<fmt:message key="menu.empty" />
				</p>
			</c:when>
			<c:otherwise>
				<c:if test="${not empty requestScope.message}">
					<p>
						<fmt:message key="${requestScope.message}" />
					</p>
				</c:if>
				<table border=1 width="100%">
					<c:forEach var="menu" items="${requestScope.menu}">
						<tr>
							<td id="menuTable"><b><fmt:message key="menu.name" />${menu.name}</b></td>
							<td rowspan="3" width="200"><img
								src="images/dishes/${menu.picture}" width="200" height="200"></td>
						</tr>

						<tr>
							<td id="menuTable"><b><fmt:message key="menu.about" />${menu.about}</b></td>
						</tr>
						<tr>
							<c:choose>
								<c:when test="${menu.secondPrice!=null && menu.secondPrice>0}">
									<td id="menuTable"><b><fmt:message
												key="menu.actionprice" />${menu.secondPrice}</b><br /> <b><fmt:message
												key="menu.regularprice" />${menu.price}</b><br />
										<form action="action" method="post" class="clearform">
											<input type="hidden" name="an" value="addtoorder" /> <input
												type="hidden" name="id" value="${menu.id}" />
											<button type="submit" name="submit"
												class="buttonfixed buttonBlue">
												<fmt:message key="product.buybutton" />
											</button>
										</form></td>

								</c:when>
								<c:otherwise>
									<td id="menuTable"><b><fmt:message key="menu.price" />${menu.price}</b><br />
										<form action="action" method="post" class="clearform">
											<input type="hidden" name="an" value="addtoorder" /> <input
												type="hidden" name="id" value="${menu.id}" />
											<button type="submit" name="submit"
												class="buttonfixed buttonBlue">
												<fmt:message key="product.buybutton" />
											</button>
										</form></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</center>