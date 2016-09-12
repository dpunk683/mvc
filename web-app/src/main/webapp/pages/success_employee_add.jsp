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
	<table width=100%>
		<tbody>
			<tr>
				<td colspan="2" valign="middle"><%@include
						file="../elements/_header.jsp"%></td>
			</tr>
			<tr>
				<td colspan="2" valign="middle" align="center"><h1>
						<fmt:message key="employee.addedtobase" />
					</h1></td>
			</tr>
			<tr>
				<td>
					<form action="action" method="post">
						<input type="hidden" name="an" value="watchmenu" />
						<button type="submit" name="submit" class="button buttonBlue">
							<fmt:message key="menu.back" />
						</button>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>