package com.aldrich.portal.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.aldrich.portal.bo.CrunchbaseFundingRounds;
import com.aldrich.portal.bo.FundingBO;
import com.aldrich.portal.dao.CompanyDetailsDAO;
import com.aldrich.portal.entity.CBOrganisations;
import com.aldrich.portal.service.CompanyCrunchbaseInfoService;



@Service
@SuppressWarnings({ "unused" })
@EnableScheduling
public class CompanyCrunchbaseInfoServiceImpl implements CompanyCrunchbaseInfoService{
	
	@Autowired
	CompanyDetailsDAO companyDetailsDAO;
	
	
	
	public boolean isValid(JSONObject jsonObject, String propertyName) {
		boolean valid = false;
		if (propertyName != null) {
			if (jsonObject.has(propertyName) && !jsonObject.get(propertyName).toString().equalsIgnoreCase("null")
					&& !jsonObject.get(propertyName).toString().equalsIgnoreCase(""))
				valid = true;
		}
		return valid;
	}

public FundingBO runService(String Url){
		
		FundingBO fundingBO = null;
		List<CrunchbaseFundingRounds> crunchbaseFundingRoundsList = null;
		
		try {
			
			String crunchbaseUniqueName="";
			
			String companyDomain = getDomainName(Url);
			
			List<CBOrganisations> cbOrganizationsList = companyDetailsDAO.getCBOrganizationByDomain(companyDomain);
			
			for(CBOrganisations cBOrganisations : cbOrganizationsList)
			{
				crunchbaseUniqueName = cBOrganisations.getPermaLink();
			}
			
			
			URL urlm = new URL("https://api.crunchbase.com/v3.1/organizations/"+crunchbaseUniqueName+"?user_key=6b3ef887c70f8e4309477a098505f4a7");
			URLEncoder.encode(urlm.toString(),"UTF-8");
			HttpURLConnection urlConnectionm = (HttpURLConnection) urlm.openConnection();
			
			BufferedReader inm = new BufferedReader(new InputStreamReader(urlConnectionm.getInputStream()));
			String inutLinem;
			
			StringBuffer responsem = new StringBuffer();

			while ((inutLinem = inm.readLine()) != null) {
				responsem.append(inutLinem);
			}
			
			System.out.println(responsem.toString());
			
			JSONObject jsonobjectm = new JSONObject(responsem.toString());
			
			JSONObject resultJSONm = jsonobjectm.getJSONObject("data");
			
			JSONObject propertiesJSONm = resultJSONm.getJSONObject("properties");
			
			String total_funding = propertiesJSONm.get("total_funding_usd")+"";
			
			URL url = new URL("https://api.crunchbase.com/v3.1/organizations/"+crunchbaseUniqueName+"/funding_rounds?user_key=6b3ef887c70f8e4309477a098505f4a7");
			URLEncoder.encode(url.toString(),"UTF-8");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inutLine;
			
			StringBuffer response1 = new StringBuffer();

			while ((inutLine = in.readLine()) != null) {
				response1.append(inutLine);
			}
			
			System.out.println(response1.toString());
			
			JSONObject jsonobject1 = new JSONObject(response1.toString());
			
			JSONObject resultJSON1 = jsonobject1.getJSONObject("data");
			
			JSONArray resultsArray1 = resultJSON1.getJSONArray("items");
			
			fundingBO = new FundingBO();
			crunchbaseFundingRoundsList = new ArrayList<CrunchbaseFundingRounds>();
			
			for (int j = 0; j < resultsArray1.length(); j++) {
				
				try {
					
					CrunchbaseFundingRounds crunchbaseFundingRounds = new CrunchbaseFundingRounds();
					
					if(resultsArray1.length() > 0) {
					
					JSONObject dataJson1 = resultsArray1.getJSONObject(j);
					
					JSONObject propertiesJson1 = dataJson1.getJSONObject("properties");
						
					try {
					
					if(isValid(propertiesJson1, "funding_type"))
						crunchbaseFundingRounds.setFundingType(propertiesJson1.get("funding_type").toString());
						
					if(isValid(propertiesJson1, "series"))
						crunchbaseFundingRounds.setFundingSeries(propertiesJson1.get("series").toString());
						
					if(isValid(propertiesJson1, "announced_on")) 
						crunchbaseFundingRounds.setAnnouncedOn(propertiesJson1.get("announced_on").toString());
					
					try {
						
					if(isValid(propertiesJson1, "money_raised_usd"))
						crunchbaseFundingRounds.setMoneyRaisedUSD(propertiesJson1.get("money_raised_usd").toString());
					}catch(Exception excp){}
					
					/*if(isValid(propertiesJson1, "money_raised_currency_code"))
						System.out.println("Money Raised Currency : "+propertiesJson1.get("money_raised_currency_code").toString());
					*/
					
					}catch(Exception excp){}
					
					if(isValid(dataJson1, "relationships")) 
					{
					JSONObject relationshipsJson = dataJson1.getJSONObject("relationships");
					
					JSONArray investmentsArray = relationshipsJson.getJSONArray("investments");
					
					String investorLink="";
					String investorName="";
					String investorType="";
					
					crunchbaseFundingRounds.setNoOfInvestors(investmentsArray.length()+"");
					
					for (int q = 0; q < investmentsArray.length(); q++) {
						
						try {
							JSONObject investmentsJson = investmentsArray.getJSONObject(q);
							
							if(isValid(investmentsJson, "relationships")) {
							JSONObject relationsObject = investmentsJson.getJSONObject("relationships");
							
							if(isValid(relationsObject, "investors")) {
							JSONObject investors = relationsObject.getJSONObject("investors");
							
							if(isValid(investors, "type"))
								investorType = investorType+","+investors.get("type");
							
							if(isValid(investors, "properties")) {
							JSONObject investorProeprties = investors.getJSONObject("properties");
							
							investorLink = investorLink+","+investorProeprties.get("permalink").toString();
							
							if(isValid(investorProeprties, "name"))
								investorName = investorName+","+investorProeprties.get("name").toString();
							else
								investorName = investorName+","+investorProeprties.get("first_name").toString()+" "+investorProeprties.get("last_name").toString();
							
												
									}
								}
							}
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
					
					crunchbaseFundingRounds.setInvestorLinks(investorLink.substring(1));
					crunchbaseFundingRounds.setInvestorNames(investorName.substring(1));
					crunchbaseFundingRounds.setInvestorType(investorType.substring(1));
					
					}
					
					
					}
					
					crunchbaseFundingRoundsList.add(crunchbaseFundingRounds);
					
				} catch (Exception excp) {
					excp.printStackTrace();
					/*ExceptionDetails exceptionDetails = new ExceptionDetails();
					
					exceptionDetails.setException_message(excp.getMessage());
					exceptionDetails.setLinkedinCompanyId("Company Domain");
					exceptionDetails.setActivityDate(new Date()+"");
					exceptionDetails.setSource("");
					
					if(companyDetailsDAO.checkForExceptionExistance("CompanyDomain", exceptionDetails.getSource()).size()==0)
					companyDetailsDAO.save(exceptionDetails);*/
					
				}
				
				
			}
			
			fundingBO.setTotalFunding(total_funding);
			fundingBO.setCrunchbaseFundingsList(crunchbaseFundingRoundsList);
			
		}catch(Exception excp) {
			excp.printStackTrace();
			
		}
		return fundingBO;
	}

@SuppressWarnings("nls")
public static String getDomainName(String url) {
	String host = null;
	try {
		if (!url.startsWith("http") && !url.startsWith("https")) {
			url = "http://" + url;
		}
		if (url.contains("www1.")) {
			url = url.replace("www1.", "www.");
		}
		URL netUrl = new URL(url);
		host = netUrl.getHost();
		if (host.startsWith("www")) {
			host = host.substring("www".length() + 1);
		}
	} catch (MalformedURLException e) {

		e.printStackTrace();
	}
	return host.toLowerCase();
}
	
}


