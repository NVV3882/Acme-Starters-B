<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.inventor.list.label.bio" path="bio" width="40%"/>
	<acme:list-column code="any.inventor.list.label.keyWords" path="keyWords" width="40%"/>
	<acme:list-column code="any.inventor.list.label.licensed" path="licensed" width="20%"/>
</acme:list>