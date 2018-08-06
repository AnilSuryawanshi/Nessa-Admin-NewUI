package com.connecticus.admin.dto;

public class Data {
	 private String error;
	 private Info info;

	    //private String info;

	    public String getError ()
	    {
	        return error;
	    }

	    public Info getInfo() {
			return info;
		}

		public void setInfo(Info info) {
			this.info = info;
		}

		public void setError (String error)
	    {
	        this.error = error;
	    }


	    @Override
	    public String toString()
	    {
	        return "ClassPojo [error = "+error+", info = "+info+"]";
	    }
}
