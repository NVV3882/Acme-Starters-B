<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.spokesperson.list.label.cv" path="cv" width="33%"/>
	<acme:list-column code="any.spokesperson.list.label.achievements" path="achievements" width="34%"/>
	<acme:list-column code="any.spokesperson.list.label.licensed" path="licensed" width="33%"/>
</acme:list>