<%
	ui.decorateWith("kenyaemr", "standardPage", [ patient: currentPatient ])
%>

<div class="ke-page-content">
	${ ui.decorate("kenyaui", "panel", [ heading: "Tests" ], ui.includeFragment("kenyalab", "patientLabTests", [ patient: currentPatient ])) }
</div>