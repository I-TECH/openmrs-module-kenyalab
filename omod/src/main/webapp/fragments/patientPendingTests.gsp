<%
	ui.decorateWith("kenyaui", "panel", [ heading: "Lab Tests" ])
%>
<table class="ke-table-vertical">
	<thead>
		<tr>
			<th>Concept</th>
			<th>Source</th>
			<th>Result</th>
		</tr>
	</thead>
	<tbody>
	<% tests.each { test ->
		def source = test.orderer
	%>
		<tr>
			<td>${ ui.format(test.concept) }</td>
			<td>${ ui.format(source) }</td>
			<td>
			<% if (test.hasResult()) { %>
				${ ui.includeFragment("kenyalab", "testResult", [ result: test.result ]) }
			<% } else { %>
				${ ui.includeFragment("kenyaui", "widget/button", [ label: "Add" ]) }
			<% } %>
			</td>
		</tr>
	<% } %>
	</tbody>
</table>