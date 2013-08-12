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

import org.junit.Before;
import org.junit.Test;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.kenyalab.LabMetadata;
import org.openmrs.module.kenyalab.api.LaboratoryService;
import org.openmrs.module.kenyautil.test.TestUtils;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests for {@link LaboratoryServiceImpl}
 */
public class LaboratoryServiceImplTest extends BaseModuleContextSensitiveTest {

	@Autowired
	private LaboratoryService laboratoryService;

	/**
	 * Set up each test
	 */
	@Before
	public void setup() throws Exception {
		executeDataSet("test-data.xml");
	}

	/**
	 * @see LaboratoryServiceImpl#submitOrder(org.openmrs.Order)
	 */
	@Test
	public void submitOrder() {
		Patient patient = TestUtils.getPatient(7);

		Order order = new Order();
		order.setPatient(patient);
		order.setOrderer(Context.getUserService().getUser(1));
		order.setConcept(Context.getConceptService().getConcept(5497)); // CD4
		order.setOrderType(Context.getOrderService().getOrderTypeByUuid(LabMetadata.LAB_ORDER_TYPE));

		laboratoryService.submitOrder(order);
	}

	/**
	 * @see LaboratoryServiceImpl#getLabTestsByPatient(org.openmrs.Patient)
	 */
	@Test
	public void getLabTestsByPatient() {
		Patient patient = TestUtils.getPatient(7);

		// TODO save some orders

		laboratoryService.getLabTestsByPatient(patient);
	}
}