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

package org.openmrs.module.kenyalab.api.db.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Obs;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.module.kenyalab.api.db.LaboratoryDAO;

import java.util.List;

/**
 * Hibernate implementation of the laboratory DAO
 */
public class HibernateLaboratoryDAO implements LaboratoryDAO {

	private static final int TEST_CONCEPT_CLASS_ID = 1; // Is part of core data so always has same id

	private SessionFactory sessionFactory;

	/**
	 * Sets the session factory
	 * @param sessionFactory the session factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Convenience method to get current session
	 * @return the session
	 */
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * @see LaboratoryDAO#getLabOrdersByPatient(org.openmrs.Patient)
	 */
	@SuppressWarnings("unchecked")
	public List<Order> getLabOrdersByPatient(Patient patient) {
		return getCurrentSession().createCriteria(Order.class)
				.createAlias("concept", "c")
				.add(Restrictions.eq("c.conceptClass.id", TEST_CONCEPT_CLASS_ID))
				.add(Restrictions.eq("patient", patient))
				.add(Restrictions.eq("voided", false))
				.list();
	}

	/**
	 * @see LaboratoryDAO#getLabResultsByPatient(org.openmrs.Patient)
	 */
	@SuppressWarnings("unchecked")
	public List<Obs> getLabResultsByPatient(Patient patient) {
		return getCurrentSession().createCriteria(Obs.class)
				.createAlias("concept", "c")
				.add(Restrictions.eq("c.conceptClass.id", TEST_CONCEPT_CLASS_ID))
				.add(Restrictions.eq("person", patient))
				.add(Restrictions.eq("voided", false))
				.list();
	}
}