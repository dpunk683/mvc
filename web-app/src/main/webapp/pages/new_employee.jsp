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
    <title><fmt:message key="new.employee.title"/></title>
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
            <FORM METHOD="POST" NAME="FORM1" ACTION="action" TITLE="Анкета">
                <FIELDSET>
                    <LEGEND>Введите данные для нового сотрудника:</LEGEND>
                    <INPUT TYPE="TEXT" id="fio" NAME="fio" MAXLENGTH="25" size="25" pattern="{1,50}"
                           placeholder="ФИО" autofocus required>
                    <input list="acclevel" name="acclevel" placeholder="Выберите уровень доступа" >
                    <datalist id="acclevel" required>
                        <option value="1">Администратор</option>
                        <option value="2">Официант</option>
                        <option value="3">Кухонный рабочий</option>
                    </datalist>
                    </br>
                    <INPUT TYPE="TEXT" id="cardNum" NAME="cardNum" MAXLENGTH="24" size="25" pattern="[0-9]{1,24}"
                           placeholder="Номер карты доступа">
                </FIELDSET>
                <FIELDSET>
                    <LEGEND>Данные для входа и восстановления пароля</LEGEND>
                    <INPUT TYPE="TEXT" id="login" NAME="login" MAXLENGTH="20" size="20" pattern="{5,20}"
                           placeholder="Login" required>
                    <INPUT TYPE="password" id="password" NAME="password" MAXLENGTH="20" size="20"
                           pattern="{1,20}" placeholder="Password" autocomplete="off" required>
                </fieldset>
                <FIELDSET>
                    <LEGEND></LEGEND>
                    <input type="hidden" name="an" value="addemployee" />
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
