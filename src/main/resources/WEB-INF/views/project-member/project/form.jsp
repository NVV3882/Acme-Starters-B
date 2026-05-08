
<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="manager.project.form.label.title" path="title"/>
	<acme:form-textbox code="manager.project.form.label.keyWords" path="keyWords"/>
	<acme:form-textarea code="manager.project.form.label.description" path="description"/>
	<acme:form-moment code="manager.project.form.label.kickOffMoment" path="kickOffMoment"/>
	<acme:form-moment code="manager.project.form.label.closeOutMoment" path="closeOutMoment"/>
	<acme:form-moment code="manager.project.form.label.momentOfPublication" path="momentOfPublication"/>
	<acme:form-double code="manager.project.form.label.effort" path="effort"/>
	
	<jstl:choose>
		<jstl:when test="${_command == 'show' && draftMode == false }">
			<acme:button code="manager.project.form.button.inventions" action="/project-member/invention/list?projectId=${id}"/>
			<acme:button code="manager.project.form.button.campaigns" action="/project-member/campaign/list?projectId=${id}"/>
			<acme:button code="manager.project.form.button.strategies" action="/project-member/strategy/list?projectId=${id}"/>
			<acme:button code="any.project.form.button.inventors" action="/project-member/inventor/list?projectId=${id}"/>
			<acme:button code="any.project.form.button.spokespeople" action="/project-member/spokesperson/list?projectId=${id}"/>
			<acme:button code="any.project.form.button.fundraisers" action="/project-member/fundraiser/list?projectId=${id}"/>
			
			<acme:button code="manager.project.form.button.manager" action="/project-member/manager/show?projectId=${id}"/>
		</jstl:when>

		<jstl:when test="${acme:anyOf(_command, 'show') && draftMode == true}">
			<acme:button code="manager.project.form.button.inventions" action="/project-member/invention/list?projectId=${id}"/>
			<acme:button code="manager.project.form.button.campaigns" action="/project-member/campaign/list?projectId=${id}"/>
			<acme:button code="manager.project.form.button.strategies" action="/project-member/strategy/list?projectId=${id}"/>
			<acme:button code="any.project.form.button.inventors" action="/project-member/inventor/list?projectId=${id}"/>
			<acme:button code="any.project.form.button.spokespeople" action="/project-member/spokesperson/list?projectId=${id}"/>
			<acme:button code="any.project.form.button.fundraisers" action="/project-member/fundraiser/list?projectId=${id}"/>
			
			<acme:button code="manager.project.form.button.manager" action="/project-member/manager/show?projectId=${id}"/>
			<acme:submit code="manager.project.form.button.publish" action="/project-member/project/publish?id=${id}"/>
			
		</jstl:when>


		<jstl:when test="${_command == 'create'}">
            <acme:submit code="manager.project.form.button.create" action="/project-member/project/create"/>
        </jstl:when>
    </jstl:choose>

</acme:form>
