<%@ page import="testcontrollerserviceinjection.Person" %>



<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'person', 'error')} required">
	<label for="person">
		<g:message code="person.person.label" default="Person" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="person" required="" value="${personInstance?.person}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="person.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${personInstance?.password}"/>
</div>

