package com.aldrich.portal.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aldrich.portal.dao.CompanyDetailsDAO;
import com.aldrich.portal.entity.CBOrganisations;
@Repository
public class CompanyDetailsDAOImpl implements CompanyDetailsDAO
{
	@Autowired
	SessionFactory sessionFactory;
	

	public List<CBOrganisations> getCBOrganizationByDomain(String domain) {

		Query query = null;

		try {

			query = this.sessionFactory.openSession().createQuery(
					"select model from CBOrganisations model where model.domain=:domain order by model.activityDatetime");

			query.setParameter("domain", domain);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return query.list();

	}

}
