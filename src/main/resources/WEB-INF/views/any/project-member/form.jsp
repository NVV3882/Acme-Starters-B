<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.projectmember.form.label.username" path="userAccount.username" readonly="true"/>

	<jstl:if test="${manager != null}">
		<acme:form-textbox code="authenticated.manager.form.label.position" path="manager.position" readonly="true"/>
		<acme:form-textbox code="authenticated.manager.form.label.skills" path="manager.skills" readonly="true"/>
		<acme:form-checkbox code="authenticated.manager.form.label.executive" path="manager.executive" readonly="true"/>
	</jstl:if>

	<jstl:if test="${spokesperson != null}">
		<acme:form-textbox code="authenticated.spokesperson.form.label.organisation" path="spokesperson.organisation" readonly="true"/>
		<acme:form-textarea code="authenticated.spokesperson.form.label.profile" path="spokesperson.profile" readonly="true"/>
	</jstl:if>

	<jstl:if test="${fundraiser != null}">
		<acme:form-textbox code="authenticated.fundraiser.form.label.bank" path="fundraiser.bank" readonly="true"/>
		<acme:form-textarea code="authenticated.fundraiser.form.label.statement" path="fundraiser.statement" readonly="true"/>
		<acme:form-checkbox code="authenticated.fundraiser.form.label.agent" path="fundraiser.agent" readonly="true"/>
	</jstl:if>

	<jstl:if test="${inventor != null}">
		<acme:form-textbox code="authenticated.inventor.form.label.identity" path="inventor.identity" readonly="true"/>
		<acme:form-textarea code="authenticated.inventor.form.label.profile" path="inventor.profile" readonly="true"/>
	</jstl:if>
</acme:form>
