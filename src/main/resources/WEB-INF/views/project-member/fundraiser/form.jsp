<%@page%>

    <%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="acme" uri="http://acme-framework.org/" %>

            <acme:form readonly="true">

                <acme:form-textbox code="manager.fundraiser.form.label.bank" path="bank" />

                <acme:form-textarea code="manager.fundraiser.form.label.statement" path="statement" />

                <acme:form-checkbox code="manager.fundraiser.form.label.agent" path="agent" />

            </acme:form>