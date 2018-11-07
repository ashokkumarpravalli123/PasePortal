package com.aldrich.portal.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldrich.portal.bo.UrlRedirectionDetailsBO;
import com.aldrich.portal.service.OfficeSpaceLinkExtraction;
import com.aldrich.portal.service.PasePortalGeneric;
import com.aldrich.portal.util.PasePortalConstants;
@Service
public class OfficeSpaceLinkExtractionImpl implements OfficeSpaceLinkExtraction 
{
	@Autowired
	PasePortalGeneric pasePortalGeneric;

	public Set<String> getContactUrls(String companyUrl) 
	{
		Document document=null;
		Elements elements =null;
		Set<String> contactUsURls = null;
		try
		{
			if(companyUrl.startsWith("https"))
			{
				document=Jsoup.connect(companyUrl).followRedirects(true).userAgent(PasePortalConstants.USER_AGENT).get();
			}
			else
			{
				companyUrl="https://www."+companyUrl;
				document=Jsoup.connect(companyUrl).followRedirects(true).userAgent(PasePortalConstants.USER_AGENT).get();
			}
			if(document!=null)
			{
				elements = document.select("a");
				if(elements!=null)
				{
					contactUsURls=new LinkedHashSet<String>();
					for(Element element:elements)
					{
						try
						{
							if (element.attr("href").toLowerCase().contains("contact")
									|| element.text().toLowerCase().contains("contact")
									|| element.className().toLowerCase().contains("contact"))
							{
								if (!element.attr("href").trim().contains("@")
										&& !element.attr("href").trim().contains("javascript:void"))
								{
									String finalURL = null;
									if (element.attr("href").trim().startsWith("http")
											|| element.attr("href").trim().startsWith("www")) {
										finalURL = element.attr("href").trim();
									} else 
									{
										finalURL = companyUrl + "/" + element.attr("href").trim();
									}

									String initialPart = finalURL.split("\\.")[0];
									String laterPart = finalURL.replace(initialPart, "").replace("//", "/");

									finalURL = initialPart + laterPart;

									UrlRedirectionDetailsBO status = this.pasePortalGeneric.getRedirectedUrlAndStatusCode(finalURL);
									int statusCode=status.getStatusCode();
									if(statusCode==200)
									{
										String redirectedUrl = status.getRedirectedUrl();
										contactUsURls.add(redirectedUrl);
									}
								}
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return contactUsURls;
	}

}
