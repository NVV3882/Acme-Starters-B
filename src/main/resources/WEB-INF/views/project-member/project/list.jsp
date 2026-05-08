<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.project.list.label.title" path="title" width="20%"/>
	<acme:list-column code="any.project.list.label.keyWords" path="keyWords" width="20%"/>
	<acme:list-column code="any.project.list.label.kickOffMoment" path="kickOffMoment" width="20%"/>
	<acme:list-column code="any.project.list.label.closeOutMoment" path="closeOutMoment" width="20%"/>
	<acme:list-column code="any.project.list.label.draftMode" path="draftMode" width="20%"/>
	
	<acme:list-hidden path="description"/>
	<acme:list-hidden path="momentOfPublication"/>
	<acme:list-hidden path="effort"/>
</acme:list>

<acme:button code="manager.project.list.button.create" action="/project-member/project/create"/>

