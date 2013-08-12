<table class="ke-table-vertical">
	<thead>
		<tr>
			<th>Concept</th>
			<th>Orderer</th>
		</tr>
	</thead>
	<tbody>
	<% tests.each { test -> %>
		<tr>
			<td>${ ui.format(test.concept) }</td>
			<td>${ ui.format(test.orderer) }</td>
		</tr>
	<% } %>
	</tbody>
</table>