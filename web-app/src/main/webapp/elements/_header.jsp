<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<form action="action" method="post" id="langform">
<input type="hidden" name="an" value="lang" />
<table width=100% >
<tr valign="middle">
<td align=left><h1 id="txt"></h1></td>
<td width=52px><input type="submit" name="av" value="ru_RU" id="RuLangButton" /></td>
<td width=52px><input type="submit" name="av" value="en_US" id="EnLangButton" /></td>
</tr>
</table>
</form>