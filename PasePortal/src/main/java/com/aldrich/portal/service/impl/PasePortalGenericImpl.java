package com.aldrich.portal.service.impl;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import com.aldrich.portal.bo.UrlRedirectionDetailsBO;
import com.aldrich.portal.service.PasePortalGeneric;
import com.aldrich.portal.util.FormatUrl;
import com.aldrich.portal.util.PasePortalConstants;
@Service
public class PasePortalGenericImpl implements PasePortalGeneric 
{
	

	public UrlRedirectionDetailsBO getRedirectedUrlAndStatusCode(String url) {
		UrlRedirectionDetailsBO objRedirectionDetailsVO = new UrlRedirectionDetailsBO();
		String redirectedURL = "";
		int statusCode = 0;
		String statusMessage = "";
		String companyUrl = FormatUrl.formatUrl(url);
		try {
			redirectedURL = "";
			statusCode = 0;
			statusMessage = "";
			do {
				Thread.sleep(5000);
				Response response = Jsoup.connect(companyUrl).timeout(PasePortalConstants.CONNECTION_TIMEOUT)
						.userAgent(PasePortalConstants.USER_AGENT).followRedirects(true).execute();
				if (response.url() != null)
					redirectedURL = response.url().toString();
				if (response.statusCode() != 0)
					statusCode = response.statusCode();
				if (!response.statusMessage().equals(""))
					statusMessage = response.statusMessage();
			} while (statusCode != 200);
		} catch (Exception e) {
			if (e.toString().contains("307")) {
				try {
					Response response = Jsoup.connect(companyUrl).timeout(0).userAgent(PasePortalConstants.USER_AGENT)
							.ignoreHttpErrors(true).followRedirects(true).execute();
					if (!response.url().toString().equals("")) {
						redirectedURL = response.url().toString();
					}
					if (response.statusCode() != 0) {
						statusCode = response.statusCode();
					}
					if (!response.statusMessage().equals("")) {
						statusMessage = response.statusMessage();
					}
					objRedirectionDetailsVO = new UrlRedirectionDetailsBO();
					objRedirectionDetailsVO.setStatusCode(statusCode);
					objRedirectionDetailsVO.setStatusMessage(statusMessage);
					objRedirectionDetailsVO.setRedirectedUrl(redirectedURL);
					return objRedirectionDetailsVO;
				} catch (Exception exp) {
					e.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		}
		objRedirectionDetailsVO.setStatusCode(statusCode);
		objRedirectionDetailsVO.setStatusMessage(statusMessage);
		objRedirectionDetailsVO.setRedirectedUrl(redirectedURL);
		return objRedirectionDetailsVO;
	}

}
