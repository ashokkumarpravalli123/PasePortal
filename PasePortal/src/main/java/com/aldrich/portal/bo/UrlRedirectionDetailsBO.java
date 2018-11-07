package com.aldrich.portal.bo;

import lombok.Getter;
import lombok.Setter;

public class UrlRedirectionDetailsBO
{
	@Setter
	@Getter
	private int statusCode;

	@Setter
	@Getter
	private String url;

	@Setter
	@Getter
	private String statusMessage;

	@Setter
	@Getter
	private String redirectedUrl;

	@Setter
	@Getter
	private String websiteStatus;
}
