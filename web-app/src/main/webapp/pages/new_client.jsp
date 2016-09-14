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
                    <LEGEND>Введите данные для нового клиента:</LEGEND>
                    <INPUT TYPE="TEXT" id="fio" NAME="fio" MAXLENGTH="25" size="25" pattern="{1,50}"
                           placeholder="ФИО" autofocus required>
                    </br>
                    <FIELDSET>
                        <LEGEND>Дата рождения</LEGEND>
                        <input name="dayofb" type="number" min="1" max="31" step="1" id="dayofb">
                        <input list="monthofbl" name="monthofb" placeholder="месяц">
                        <datalist id="monthofbl">
                            <option value="1">Январь</option>
                            <option value="2">Февраль</option>
                            <option value="3">Март</option>
                            <option value="4">Апрель</option>
                            <option value="5">Май</option>
                            <option value="6">Июнь</option>
                            <option value="7">Июль</option>
                            <option value="8">Август</option>
                            <option value="9">Сентябрь</option>
                            <option value="10">Октябрь</option>
                            <option value="11">Ноябрь</option>
                            <option value="12">Декабрь</option>
                        </datalist> </br>
                        <INPUT TYPE="TEXT" id="yearofb" NAME="yearofb" MAXLENGTH="4" size="4" pattern="[0-9]{4}" placeholder="1900">
                    </FIELDSET>
                    <INPUT TYPE="email" id="email" NAME="email" MAXLENGTH="25" size="25" pattern="{1,50}"
                           placeholder="E-mail" autofocus required>
                    </br>
                    <INPUT TYPE="email" id="phone" NAME="phone" MAXLENGTH="25" size="25" pattern="{1,50}"
                           placeholder="Contact number" autofocus required>
                    </br>
                    <INPUT TYPE="TEXT" id="cardNum" NAME="cardNum" MAXLENGTH="24" size="25" pattern="[0-9]{1,24}"
                           placeholder="Номер карты лояльности">
                    </br>
                </fieldset>
                <FIELDSET>
                    <LEGEND></LEGEND>
                    <input type="hidden" name="an" value="addclient" />
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
