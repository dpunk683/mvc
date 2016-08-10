<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/shovDiv.js"></script>
<div class="Gadget">
	<div class="GadgetContent">
		<table class="menu">
			<c:if test="${(not empty sessionScope.accesslevel) and (sessionScope.accesslevel == '99')}">
			<tr>
				<td>
<<<<<<< Updated upstream
					<form action="action" method="post">
						<input type="hidden" name="an" value="watchmenu" />
						<button type="submit" name="submit" class="button">
							<fmt:message key="menu.watch" />
						</button>
					</form>
				</td>
				<td>
=======
>>>>>>> Stashed changes
					<form>
						<button type="button" class="button buttonBlue">
							<fmt:message key="about" />
						</button>
					</form>
				</td>
				<td>
					<form action="action" method="post" >
						<input type="hidden" name="an" value="getdaydish" />
						<button type="submit" name="submit" class="button buttonBlue">
							<fmt:message key="daydish" />
						</button>
					</form>
				</td>
				<td>
					<form action="action" method="post">
						<input type="hidden" name="an" value="getorder" />
						<button type="submit" name="submit" class="button buttonBlue">
							<fmt:message key="order.view" />:${not empty sessionScope.order ? sessionScope.order.length : '0'}
						</button>
					</form>
				</td>
			</tr>
			</c:if>
			<%-- Providing some waiter access --%>
			<c:if
				test="${(not empty sessionScope.accesslevel) and ((sessionScope.accesslevel == '1') or (sessionScope.accesslevel == '2')or (sessionScope.accesslevel == '3')) }">
				<tr>
					<td style="font-size: 9px; text-align: center">
						<h1>
							<fmt:message key="orders.viewer" />
						</h1>
					</td>
				</tr>
				<tr>
					<td>
						<form action="action" method="post">
						<input type="hidden" name="an" value="vieworders" />
							<button type="submit" name="submit" class="button buttonBlue">
								<fmt:message key="orders.list" />
							</button>
						</form>
					</td>
			</c:if>
			<%-- Providing some administrator access --%>
			<c:if
				test="${(not empty sessionScope.accesslevel) and (sessionScope.accesslevel == '1')}">
				<tr>
					<td style="font-size: 9px; text-align: center">
						<h1>
							<fmt:message key="menu.admin" />
						</h1>
					</td>
				</tr>
				<tr>
					<td>
						<form action="addwaiter" method="post">
							<button type="submit" name="submit" class="button buttonBlue">
								<fmt:message key="waiter.add" />
							</button>
						</form>
					</td>
					<td>
						<form action="addclient" method="post">
							<button type="submit" name="submit" class="button buttonBlue">
								<fmt:message key="client.add" />
							</button>
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<form action="addtable" method="post">
							<button type="submit" name="submit" class="button buttonBlue">
								<fmt:message key="table.add" />
							</button>
						</form>
					</td>
					<td>
						<form action="addproduct" method="post">
							<button type="submit" name="submit" class="button buttonBlue">
								<fmt:message key="product.add" />
							</button>
						</form>
					</td>
				</tr>
				<tr>
					<td style="font-size: 9px; text-align: center">
						<h1>
							<fmt:message key="pos.admin.menu" />
						</h1>
					</td>
				</tr>
				<tr>
					<td>
						<form action="getZreport" method="post">
							<button type="submit" name="submit" class="button buttonBlue">
								<fmt:message key="getZreport" />
							</button>
						</form>
					</td>
					<td>
						<form action="registerPOS" method="post">
							<button type="submit" name="submit" class="button buttonBlue">
								<fmt:message key="registerPOS" />
							</button>
						</form>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	<div class="GadgetSmoothBottom"></div>
</div>