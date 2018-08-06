package com.connecticus.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status 
{
    private String currentSessions;

    private String startedOn;

    private String domain;

    private String uiType;

    private SessionIDs[] sessionIDs;

    private String URI;
    
    private String company;
    
    public String getCurrentSessions ()
    {
        return currentSessions;
    }

    public void setCurrentSessions (String currentSessions)
    {
        this.currentSessions = currentSessions;
    }

    public String getStartedOn ()
    {
        return startedOn;
    }

    public void setStartedOn (String startedOn)
    {
        this.startedOn = startedOn;
    }

    public String getDomain ()
    {
        return domain;
    }

    public void setDomain (String domain)
    {
        this.domain = domain;
    }

    public String getUiType ()
    {
        return uiType;
    }

    public void setUiType (String uiType)
    {
        this.uiType = uiType;
    }

    public SessionIDs[] getSessionIDs ()
    {
        return sessionIDs;
    }

    public void setSessionIDs (SessionIDs[] sessionIDs)
    {
        this.sessionIDs = sessionIDs;
    }

    public String getURI ()
    {
        return URI;
    }
    
    public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@JsonProperty("URI")
    public void setURI (String URI)
    {
        this.URI = URI;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [currentSessions = "+currentSessions+", startedOn = "+startedOn+", domain = "+domain+", uiType = "+uiType+", sessionIDs = "+sessionIDs+", URI = "+URI+"]";
    }
}
