<%
	ui.decorateWith("kenyaui", "panel", [ heading: "Pending" ])
%>
<table class="ke-table-vertical">
	<thead>
		<tr>
			<th>Concept</th>
			<th>Source</th>
			<th>Ordered</th>
		</tr>
	</thead>
	<tbody>
	<% config.tests.each { test ->
		def source = test.orderer
	%>
		<tr>
			<td>${ ui.format(test.concept) }</td>
			<td>${ ui.format(source) }</td>
			<td>${ kenyaUi.formatDate(test.orderedDate) }</td>
		</tr>
	<% } %>
	</tbody>
</table>