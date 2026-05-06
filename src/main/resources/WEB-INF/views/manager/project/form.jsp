
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="manager.project.form.label.title" path="title"/>
	<acme:form-textbox code="manager.project.form.label.keyWords" path="keyWords"/>
	<acme:form-textarea code="manager.project.form.label.description" path="description"/>
	<acme:form-moment code="manager.project.form.label.kickOffMoment" path="kickOffMoment"/>
	<acme:form-moment code="manager.project.form.label.closeOutMoment" path="closeOutMoment"/>
	<acme:form-moment code="manager.project.form.label.momentOfPublication" path="momentOfPublication" readonly="true"/>
	<acme:form-double code="manager.project.form.label.effort" path="effort" readonly="true"/>
	
	<jstl:choose>
		<jstl:when test="${_command == 'show' && draftMode == false }">
			<acme:button code="manager.project.form.button.inventions" action="/manager/invention/list?projectId=${id}"/>
			<acme:button code="manager.project.form.button.campaigns" action="/manager/campaign/list?projectId=${id}"/>
			<acme:button code="manager.project.form.button.strategies" action="/manager/strategy/list?projectId=${id}"/>
			<acme:button code="manager.project.form.button.members" action="/any/project-member/list?projectId=${id}"/>
			
			<acme:button code="manager.project.form.button.manager" action="/any/manager/show?projectId=${id}"/>
		</jstl:when>

		<jstl:when test="${acme:anyOf(_command, 'show') && draftMode == true}">
			<acme:button code="manager.project.form.button.inventions" action="/manager/invention/list?projectId=${id}"/>
			<acme:button code="manager.project.form.button.campaigns" action="/manager/campaign/list?projectId=${id}"/>
			<acme:button code="manager.project.form.button.strategies" action="/manager/strategy/list?projectId=${id}"/>
			<acme:button code="manager.project.form.button.members" action="/any/project-member/list?projectId=${id}"/>
			
			<acme:button code="manager.project.form.button.manager" action="/any/manager/show?projectId=${id}"/>
			<acme:submit code="manager.project.form.button.publish" action="/manager/project/publish?id=${id}"/>
			
		</jstl:when>


		<jstl:when test="${_command == 'create'}">
            <acme:submit code="manager.project.form.button.create" action="/manager/project/create"/>
        </jstl:when>
    </jstl:choose>

</acme:form>
