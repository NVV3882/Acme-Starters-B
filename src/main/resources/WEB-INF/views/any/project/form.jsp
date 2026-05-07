<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form readonly="true">
	<acme:form-textbox code="any.project.form.label.title" path="title"/>
	<acme:form-textbox code="any.project.form.label.keyWords" path="keyWords"/>
	<acme:form-textarea code="any.project.form.label.description" path="description"/>
	<acme:form-moment code="any.project.form.label.kickOffMoment" path="kickOffMoment"/>
	<acme:form-moment code="any.project.form.label.closeOutMoment" path="closeOutMoment"/>
	<acme:form-moment code="any.project.form.label.momentOfPublication" path="momentOfPublication"/>
	<acme:form-double code="any.project.form.label.effort" path="effort"/>
	<acme:form-money code="any.project.form.label.draftMode" path="draftMode"/>
	
	<acme:button code="any.project.form.button.manager" action="/any/manager/show?projectId=${id}"/>
	<acme:button code="any.project.form.button.inventions" action="/any/invention/list?projectId=${id}"/>
	<acme:button code="any.project.form.button.campaigns" action="/any/campaign/list?projectId=${id}"/>
	<acme:button code="any.project.form.button.strategies" action="/any/strategy/list?projectId=${id}"/>
	<acme:button code="any.project.form.button.inventors" action="/any/inventor/list?projectId=${id}"/>
	<acme:button code="any.project.form.button.spokespeople" action="/any/spokesperson/list?projectId=${id}"/>
	<acme:button code="any.project.form.button.fundraisers" action="/any/fundraiser/list?projectId=${id}"/>
	
</acme:form>
