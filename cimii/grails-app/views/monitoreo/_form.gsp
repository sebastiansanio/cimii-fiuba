<%@ page import="cimii.SistemaDeMolinetes" %>



			<div class="control-group fieldcontain ${hasErrors(bean: sistemaDeMolinetesInstance, field: 'flujo', 'error')} required">
				<label for="flujo" class="control-label"><g:message code="sistemaDeMolinetes.flujo.label" default="Flujo" /><span class="required-indicator">*</span></label>
				<div class="controls">
					<g:field type="number" name="flujo" step="any" required="" value="${sistemaDeMolinetesInstance.flujo}"/>
					<span class="help-inline">${hasErrors(bean: sistemaDeMolinetesInstance, field: 'flujo', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: sistemaDeMolinetesInstance, field: 'linea', 'error')} required">
				<label for="linea" class="control-label"><g:message code="sistemaDeMolinetes.linea.label" default="Linea" /><span class="required-indicator">*</span></label>
				<div class="controls">
					<g:select id="linea" name="linea.id" from="${cimii.Linea.list()}" optionKey="id" required="" value="${sistemaDeMolinetesInstance?.linea?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: sistemaDeMolinetesInstance, field: 'linea', 'error')}</span>
				</div>
			</div>

