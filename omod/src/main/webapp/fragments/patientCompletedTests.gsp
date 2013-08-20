<%
	ui.decorateWith("kenyaui", "panel", [ heading: "Completed" ])
%>
<table class="ke-table-vertical">
	<thead>
		<tr>
			<th>Test</th>
			<th style="text-align: left">Source</th>
			<th style="text-align: left">Result</th>
			<th style="text-align: left">Performed</th>
		</tr>
	</thead>
	<tbody>
	<% config.tests.reverse().each { test ->
		def source = test.hasOrder() ? test.orderer : test.sourceForm
	%>
		<tr>
			<td>${ ui.format(test.concept) }</td>
			<td style="text-align: left">${ ui.format(source) }</td>
			<td style="text-align: left">
			<% if (test.hasResult()) { %>
				${ ui.includeFragment("kenyalab", "testResult", [ result: test.result ]) }
			<% } else { %>
				${ ui.includeFragment("kenyaui", "widget/button", [ label: "Add" ]) }
			<% } %>
			</td>
			<td style="text-align: left">${ kenyaUi.formatDate(test.performedDate) }</td>
		</tr>
	<% } %>
	</tbody>
</table>