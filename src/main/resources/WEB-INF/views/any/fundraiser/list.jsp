<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.fundraiser.list.label.bank" path="bank" width="33%"/>
	<acme:list-column code="any.fundraiser.list.label.statement" path="statement" width="34%"/>
	<acme:list-column code="any.fundraiser.list.label.agent" path="agent" width="33%"/>
</acme:list>