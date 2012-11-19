
<%@ page import="cimii.SistemaDeMolinetes" %>
<!doctype html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'sistemaDeMolinetes.label', default: 'SistemaDeMolinetes')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-sistemaDeMolinetes" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="sistemaDeMolinetes.flujo.label" default="Flujo" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: sistemaDeMolinetesInstance, field: "flujo")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="sistemaDeMolinetes.linea.label" default="Linea" /></td>
				
				<td valign="top" class="value"><g:link controller="linea" action="show" id="${sistemaDeMolinetesInstance?.linea?.id}">${sistemaDeMolinetesInstance?.linea?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
