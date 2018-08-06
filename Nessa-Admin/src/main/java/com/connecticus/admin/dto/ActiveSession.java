package com.connecticus.admin.dto;

import java.io.Serializable;

public class ActiveSession implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 45422936409386493L;
	private Status status;

    public Status getStatus ()
    {
        return status;
    }

    public void setStatus (Status status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+"]";
    }
}
			
			