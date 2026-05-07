<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="any.manager.form.label.position" path="position" readonly="true"/>
	<acme:form-textarea code="any.manager.form.label.skills" path="skills" readonly="true"/>
	<acme:form-checkbox code="any.manager.form.label.executive" path="executive" readonly="true"/>
</acme:form>
