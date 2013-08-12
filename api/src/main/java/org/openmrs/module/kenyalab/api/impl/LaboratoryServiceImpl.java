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

package org.openmrs.module.kenyalab.api.impl;

import org.openmrs.Obs;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.kenyalab.LabTest;
import org.openmrs.module.kenyalab.api.LaboratoryService;
import org.openmrs.module.kenyalab.api.db.LaboratoryDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of the laboratory service
 */
public class LaboratoryServiceImpl implements LaboratoryService {

	private LaboratoryDAO laboratoryDAO;

	/**
	 * Sets the DAO
	 * @param laboratoryDAO the DAO
	 */
	public void setLaboratoryDAO(LaboratoryDAO laboratoryDAO) {
		this.laboratoryDAO = laboratoryDAO;
	}

	/**
	 * @see LaboratoryService#submitOrder(org.openmrs.Order)
	 */
	@Override
	public void submitOrder(Order order) {
		// TODO forward to external lab system

		Context.getOrderService().saveOrder(order);
	}

	/**
	 * @see LaboratoryService#getLabTestsByPatient(org.openmrs.Patient)
	 */
	@Override
	public List<LabTest> getLabTestsByPatient(Patient patient) {
		List<Order> orders = laboratoryDAO.getLabOrdersByPatient(patient);
		List<Obs> results = laboratoryDAO.getLabResultsByPatient(patient);

		// Organize results by their orders
		Map<Order, Obs> resultsForOrders = new HashMap<Order, Obs>();
		List<Obs> resultsWithoutOrders = new ArrayList<Obs>();

		for (Obs result : results) {
			if (result.getOrder() != null) {
				resultsForOrders.put(result.getOrder(), result);
			} else {
				resultsWithoutOrders.add(result);
			}
		}

		Set<LabTest> tests = new TreeSet<LabTest>();

		// Add all orders as transactions
		for (Order order : orders) {
			// Find result if there is one
			Obs result = resultsForOrders.get(order);
			tests.add(new LabTest(order, result));
		}

		// Add all remaining order-less results as transactions
		for (Obs result : resultsWithoutOrders) {
			tests.add(new LabTest(null, result));
		}

		return new ArrayList<LabTest>(tests);
	}
}