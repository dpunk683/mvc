<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../elements/_setLocale.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="js/clock.js"></script>
<script src="js/shovDiv.js"></script>
<link rel="stylesheet" href="css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="images/favicon.ico" />
<title><fmt:message key="index.title" /></title>
</head>
<body onload="startTime()">
	<table width="100%" align="left">
		<tbody>
			<tr>
				<td colspan="2"><%@include file="../elements/_header.jsp"%>
				</td>
			</tr>
			<tr>
				<td align="left" rowspan="2" class="topTD" width="20%">
					<div>
						<%@include file="../elements/_auth.jsp"%>
					</div>

				</td>
				<td class="topTD">
					<table border="1" width="100%">
						<tr>
							<td><b><fmt:message key="order.tooktime" /></b></td>
							<td><b><fmt:message key="order.dishname" /></b></td>
							<td><b><fmt:message key="order.tablenum" /></b></td>
							<td><b><fmt:message key="order.status" /></b></td>
							<td><b><fmt:message key="order.control" /></b></td>
						</tr>
						<c:forEach var="acc_orders" items="${sessionScope.acc_orders}">
							<c:choose>
								<c:when
									test="${(not empty sessionScope.accesslevel) and ((sessionScope.accesslevel == '1') or (sessionScope.accesslevel == '2'))}">
									<tr>
										<td><b>${acc_orders.starttime}</b></td>
										<td><b>${acc_orders.prodname}</b></td>
										<td><b>${acc_orders.tableNum}</b></td>
										<c:choose>
										<c:when test="${(acc_orders.status==0)}">
										<td><b><fmt:message key="order.status.new" /></b></td>
										</c:when>
										<c:otherwise><c:choose><c:when test="${(acc_orders.status==1)}">
										<td><b><fmt:message key="order.status.on.kitchen" /></b></td>
										</c:when><c:otherwise><td><b><fmt:message key="order.status.is.ready" /></b></td></c:otherwise></c:choose></c:otherwise></c:choose>
										<td><table>
												<tr>
													<td>
														<form action="action" method="post" class="clearform">
															<input type="hidden" name="an" value="sendtokitchen" />
															<input type="hidden" name="id" value="${acc_orders.id}" />
															<input type="submit" id="KitchenButton" />
														</form>
													</td>
													<td>
														<form action="action" method="post" class="clearform">
															<input type="hidden" name="an" value="pay" /> <input
																type="hidden" name="id" value="${acc_orders.id}" /> <input
																type="submit"
																alt="<fmt:message key="order.remove.button"/>"
																id="PayButton" />
														</form>
													</td>
													<td>
														<form action="action" method="post" class="clearform">
															<input type="hidden" name="an" value="removefromaccorder" />
															<input type="hidden" name="id" value="${acc_orders.id}" />
															<input type="submit"
																alt="<fmt:message key="order.remove.button"/>"
																id="RemoveButton" />
														</form>
													</td>
												</tr>
											</table></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td><b>${acc_orders.starttime}</b></td>
										<td><b>${acc_orders.prodname}</b></td>
										<td><b>${acc_orders.tableNum}</b></td>
										<td><b><fmt:message key="order.status.new" /></b></td>
										<td><table>
												<tr>
													<td>
														<form action="action" method="post" class="clearform">
															<input type="hidden" name="an" value="setchecked" />
															<input type="hidden" name="id" value="${acc_orders.id}" />
															<input type="submit" id="ReadyButton" />
														</form>
													</td>
												</tr>
											</table></td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>

