package com.aldrich.portal.bo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class FundingBO {

	@Getter
	@Setter
	private String totalFunding;
	
	@Getter
	@Setter
	private List<CrunchbaseFundingRounds> crunchbaseFundingsList;
	
	
}
