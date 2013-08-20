/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.kenyalab.fragment.controller;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.kenyalab.LabTest;
import org.openmrs.module.kenyalab.api.LaboratoryService;
import org.openmrs.ui.framework.annotation.FragmentParam;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Patient lab tests fragment controller
 */
public class PatientLabTestsFragmentController {

	public void controller(@FragmentParam("patient") Patient patient, FragmentModel model) {

		List<LabTest> completedTests = new ArrayList<LabTest>();
		List<LabTest> pendingTests = new ArrayList<LabTest>();

		for (LabTest labTest : Context.getService(LaboratoryService.class).getLabTestsByPatient(patient)) {
			if (labTest.hasResult()) {
				completedTests.add(labTest);
			}
			else {
				pendingTests.add(labTest);
			}
		}

		model.put("completedTests", completedTests);
		model.put("pendingTests", pendingTests);
	}
}