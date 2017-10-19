package com.app.cmoapi;

public class CMOReport {
	private long crisisID;				//id
	private String name;				//sender name
	private String positionIn911;		//Liaison Officer
	private String crisisType;			//Terrorist Attack
	private String affectedArea;		//Orchard (1.301800, 103.837797)
	private String crisisDate;			//20 September 2017
	private String estimatedStartTime;	//1500 hours
	private String crisisDetails;		//Detailed information on the crisis

	
    public CMOReport(){
        crisisID=0;
    }

	public CMOReport(long crisisID, String name, String positionIn911, String crisisType, String affectedArea,
			String crisisDate, String estimatedStartTime, String crisisDetails) {
		this.crisisID = crisisID;
		this.name = name;
		this.positionIn911 = positionIn911;
		this.crisisType = crisisType;
		this.affectedArea = affectedArea;
		this.crisisDate = crisisDate;
		this.estimatedStartTime = estimatedStartTime;
		this.crisisDetails = crisisDetails;
	}
 
    public long getCrisisID() {
		return crisisID;
	}

	public void setCrisisID(long crisisID) {
		this.crisisID = crisisID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionIn911() {
		return positionIn911;
	}

	public void setPositionIn911(String positionIn911) {
		this.positionIn911 = positionIn911;
	}

	public String getCrisisType() {
		return crisisType;
	}

	public void setCrisisType(String crisisType) {
		this.crisisType = crisisType;
	}

	public String getAffectedArea() {
		return affectedArea;
	}

	public void setAffectedArea(String affectedArea) {
		this.affectedArea = affectedArea;
	}

	public String getCrisisDate() {
		return crisisDate;
	}

	public void setCrisisDate(String crisisDate) {
		this.crisisDate = crisisDate;
	}

	public String getEstimatedStartTime() {
		return estimatedStartTime;
	}

	public void setEstimatedStartTime(String estimatedStartTime) {
		this.estimatedStartTime = estimatedStartTime;
	}

	public String getCrisisDetails() {
		return crisisDetails;
	}

	public void setCrisisDetails(String crisisDetails) {
		this.crisisDetails = crisisDetails;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (crisisID ^ (crisisID >>> 32));
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CMOReport other = (CMOReport) obj;
        if (crisisID != other.crisisID)
            return false;
        return true;
    }
 
    @Override
	public String toString() {
		return "Report [crisisID=" + crisisID + ", name=" + name + ", positionIn911=" + positionIn911
				+ ", crisisType=" + crisisType + ", affectedArea=" + affectedArea + ", crisisDate=" + crisisDate
				+ ", estimatedStartTime=" + estimatedStartTime + ", crisisDetails=" + crisisDetails + "]";
	}
 
 
}