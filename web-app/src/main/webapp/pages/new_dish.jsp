<%--
  Created by IntelliJ IDEA.
  User: dima
  Date: 04.09.2016
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../elements/_setLocale.jsp"%>
<html>
<head>
    <script src="js/clock.js"></script>
    <script src="js/jquery-2.2.2.js"></script>
    <script src="js/shovDiv.js"></script>

    <link rel="stylesheet" href="css/style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <title><fmt:message key="new.dish.title"/></title>
</head>
<body onload="startTime()">
<table width=100%>
    <tbody>
    <tr>
        <td colspan="3">
            <%@include file="../elements/_header.jsp" %>
        </td>
    </tr>
    <tr>
        <td align="left" rowspan="2" class="topTD">
            <div>
                <%@include file="../elements/_auth.jsp" %>
            </div>
        </td>
        <td>
            <FORM METHOD="POST" NAME="FORM1" ACTION="action" TITLE="Dish" enctype="multipart/form-data">
                <FIELDSET>
                    <LEGEND>Введите данные для нового блюда:</LEGEND>
                    <INPUT TYPE="TEXT" id="name" NAME="name" MAXLENGTH="25" size="25" placeholder="Название блюда" autofocus required>
                    </br>
                    <textarea name="about" id="about" cols="25" rows="4" placeholder="Описание блюда"></textarea>
                    </br>
                    <INPUT TYPE="TEXT" id="price" NAME="price" MAXLENGTH="25" size="25"
                           placeholder="Price"  required>
                    </br>
                    <INPUT TYPE="TEXT" id="secondPrice" NAME="secondPrice" MAXLENGTH="25" size="25"
                           placeholder="Second price" >
                    </br>
                    <span>Dish image:</span></br>
                    <input type="file" name="file" id="file"/></br>
                    <LEGEND></LEGEND>
                    <input type="hidden" name="an" value="adddish"/>
                    <input type="submit" name="butt1" id="butt1" value=<fmt:message key="waiter.add" />>
                    <input type="reset" name="butt2" id="butt2" value="Сбросить"><br/>
                </fieldset>
            </FORM>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
