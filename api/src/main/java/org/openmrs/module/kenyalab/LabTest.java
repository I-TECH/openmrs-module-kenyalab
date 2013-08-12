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

package org.openmrs.module.kenyalab;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.util.OpenmrsUtil;

import java.util.Date;

/**
 * Represents the association of an order and a result
 */
public class LabTest implements Comparable<LabTest> {

	private Order order;

	private Obs result;

	/**
	 * Constructs a new lab test
	 * @param order the order
	 * @param result the result
	 */
	public LabTest(Order order, Obs result) {
		if (order == null && result == null) {
			throw new IllegalArgumentException("Both order and result can't be null");
		}

		this.order = order;
		this.result = result;
	}

	/**
	 * Gets the order
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Sets the order
	 * @param order the order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * Convenience method to check if test has an order
	 * @return true if there is an order
	 */
	public boolean hasOrder() {
		return order != null;
	}

	/**
	 * Gets the result
	 * @return the result
	 */
	public Obs getResult() {
		return result;
	}

	/**
	 * Sets the result
	 * @param result the result
	 */
	public void setResult(Obs result) {
		this.result = result;
	}

	/**
	 * Convenience method to check if test has a result
	 * @return true if there is a result
	 */
	public boolean hasResult() {
		return result != null;
	}

	/**
	 * Convenience method to get the orderer of the test
	 * @return the orderer
	 */
	public User getOrderer() {
		return hasOrder() ? order.getOrderer() : null;
	}

	/**
	 * Convenience method to get the concept of the test
	 * @return the concept
	 */
	public Concept getConcept() {
		return hasOrder() ? order.getConcept() : result.getConcept();
	}

	/**
	 * Convenience method to get the patient of the test
	 * @return the patient
	 */
	public Patient getPatient() {
		return hasOrder() ? order.getPatient() : (Patient) result.getPerson();
	}

	/**
	 * @see Comparable#compareTo(Object)
	 */
	@Override
	public int compareTo(LabTest test) {
		Date date1 = this.hasOrder() ? this.order.getDateCreated() : this.result.getObsDatetime();
		Date date2 = test.hasOrder() ? test.order.getDateCreated() : test.result.getObsDatetime();
		return OpenmrsUtil.compare(date1, date2);
	}
}