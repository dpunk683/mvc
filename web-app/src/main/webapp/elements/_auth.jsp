<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<script type="text/javascript" src="js/validation.js"></script>
<div class="Gadget">
	<div class="GadgetSmoothTop"></div>
	<div class="GadgetContent">
		<c:choose>
			<%-- Checks if the user authorized --%>
			<c:when
				test="${(not empty sessionScope.accesslevel) and (sessionScope.accesslevel == '99')}">
				<form action="action" method="post" autocomplete="off"
					style="padding-left: 19px; overflow: hidden;">
					<input type="hidden" name="an" value="login" />
					<div class="group">
						<input id="loginAuthForm" name="login" type="text" /> <span
							class="highlight"></span><span class="bar"></span> <label><fmt:message
								key="login.login" /></label>
					</div>
					<div class="group">
						<input id="passAuthForm" name="pass" type="password"><span
							class="highlight"></span><span class="bar"></span> <label><fmt:message
								key="login.pass" /></label>
					</div>
					<button type="submit" class="button buttonBlue">
						<fmt:message key="login.enter" />
						<div class="ripples buttonRipples">
							<span class="ripplesCircle"></span>
						</div>
					</button>
				</form>
				<script
					src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
				<script src="js/index.js"></script>
			</c:when>
			<c:otherwise>
				<div class="authorized">
					<center>
						<p style="font-size: 14px">
							<b><fmt:message key="login.greetings" />
							, ${sessionScope.user.name}!</b>
						</p>
						<img src="images/user_thumb.png" alt="yourimage" /> <br /> <br />
						<br /> <b><a href="action?an=logout" style="font-size: 11px"> <fmt:message
								key="login.exit" /></b>
						</a><br />
						<b><a href="..pages/index.jsp" style="font-size: 11px"> <fmt:message
								key="login.backtomenu" /></b>
						</a>
					</center>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="GadgetSmoothBottom"></div>
</div>
