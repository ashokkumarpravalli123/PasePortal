package com.aldrich.portal.service;

import com.aldrich.portal.bo.UrlRedirectionDetailsBO;

public interface PasePortalGeneric 
{

	public UrlRedirectionDetailsBO getRedirectedUrlAndStatusCode(String url);
}
