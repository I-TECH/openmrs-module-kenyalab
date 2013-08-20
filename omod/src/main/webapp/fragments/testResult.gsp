<%
	config.require("result")

	def obs = config.result
	def concept = obs.concept
	def datatype = concept.datatype

	/**
	 * Sometimes obs.concept doesn't return a ConceptNumeric for numeric concepts...
	 */
	if (datatype.numeric) {
		concept = org.openmrs.api.context.Context.getConceptService().getConceptNumeric(concept.id);
	}
%>

<% if (datatype.numeric) { %>
	${ ui.format(obs.valueNumeric) } ${ ui.format(concept.units) }
<% } else if (datatype.text) { %>
	${ ui.format(obs.valueText) }
<% } else if (datatype.coded) { %>
	${ ui.format(obs.valueCoded) }
<% } %>