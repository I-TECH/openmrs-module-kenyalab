<%
	ui.decorateWith("kenyaemr", "standardPage", [ patient: patient ])
%>

<div class="ke-page-content">
	${ ui.decorate("kenyaui", "panel", [ heading: "Tests" ], ui.includeFragment("kenyalab", "patientLabTests", [ patient: patient ])) }
</div>