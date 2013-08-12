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

package org.openmrs.module.kenyalab.api;

import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.module.kenyalab.LabTest;

import java.util.List;

/**
 * Service interface for laboratory tests
 */
public interface LaboratoryService {

	/**
	 * Submits an order, notifying any external laboratory system
	 * @param order the order
	 */
	void submitOrder(Order order);

	/**
	 * Gets all lab tests in chronological order for the given patient
	 * @param patient the patient
	 * @return the lab works
	 */
	List<LabTest> getLabTestsByPatient(Patient patient);
}