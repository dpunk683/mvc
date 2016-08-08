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
<title><fmt:message key="daydish.title" /></title>
</head>
<body onload="startTime()">
	<table>
		<tbody>
			<tr>
				<td colspan="3" valign="middle"><%@include file="../elements/_header.jsp"%>
				</td>
			</tr>
			<tr>
				<td rowspan="2" class="topTD">
					<div>
						<%@include file="../elements/_auth.jsp"%>
					</div>

				</td>
				<td class="topTD">
					<div>
						<%@include file="../elements/_menu.jsp"%>
					</div>
				</td>
				<tr>
					<td> 
						<div>
							<%@include file="../elements/_dayProduct.jsp"%>
						</div>
					</td>
				</tr>
			</tr>
		</tbody>
	</table>
</body>
</html>

