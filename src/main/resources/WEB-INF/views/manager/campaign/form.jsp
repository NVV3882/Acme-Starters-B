<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:form-textbox code="manager.campaign.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="manager.campaign.form.label.name" path="name"/>
	<acme:form-textarea code="manager.campaign.form.label.description" path="description"/>
	<acme:form-moment code="manager.campaign.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="manager.campaign.form.label.endMoment" path="endMoment"/>
	<acme:form-url code="manager.campaign.form.label.moreInfo" path="moreInfo"/>
	<acme:form-double code="manager.campaign.form.label.monthsActive" path="monthsActive" readonly="true"/>
	<acme:form-money code="manager.campaign.form.label.effort" path="effort" readonly="true"/>
</acme:form>