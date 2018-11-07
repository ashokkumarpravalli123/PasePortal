package com.aldrich.portal.dao;

import java.util.List;

import com.aldrich.portal.entity.CBOrganisations;

public interface CompanyDetailsDAO 
{
	public List<CBOrganisations> getCBOrganizationByDomain(String domain);

}
