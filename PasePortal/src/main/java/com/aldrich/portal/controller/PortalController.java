package com.aldrich.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aldrich.portal.bo.CrunchbaseFundingRounds;
import com.aldrich.portal.bo.FundingBO;
import com.aldrich.portal.model.Company;
import com.aldrich.portal.service.CompanyCrunchbaseInfoService;

@Controller
public class PortalController 
{
	@Autowired
	CompanyCrunchbaseInfoService companyCrunchbaseInfoService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home()
	{
		return "input";
	}
	
	@RequestMapping(value="/results",method=RequestMethod.POST)
	public String output(@ModelAttribute Company companyUrl,ModelMap map)
	{
		FundingBO bo=null;
		try
		{
			String url=companyUrl.getCompanyUrl();
			 bo=companyCrunchbaseInfoService.runService(url);
			 if(bo!=null)
			 {
				 map.put("suggest", bo.getTotalFunding());
				 map.put("bo", bo);
			 }
			 else
				 map.put("error", "No data available in crunchbase..");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "input";
	}

}
