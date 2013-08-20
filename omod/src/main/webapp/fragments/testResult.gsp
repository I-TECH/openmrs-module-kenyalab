<%
	config.require("result")

	def obs = config.result
	def concept = obs.concept
	def datatype = concept.datatype;
%>

<% if (datatype.numeric) { %>
	${ ui.format(obs.valueNumeric) }${ ui.format(concept.units) }
<% } else if (datatype.text) { %>
	${ ui.format(obs.valueText) }
<% } else if (datatype.coded) { %>
	${ ui.format(obs.valueCoded) }
<% } %>