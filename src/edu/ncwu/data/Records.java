package edu.ncwu.data;

public class Records {
	private String did;
	private int psno;
	private String disease;
	private String drug_sno;
	private String depart;
	private String bed;
	private String indate;
	private String outdate;
	private String advice;
	
	public Records() {
		super();
	}
	
	public Records(String did, int psno, String disease, String drug_sno, String depart, String bed, String indate,
			String outdate, String advice) {
		super();
		this.did = did;
		this.psno = psno;
		this.disease = disease;
		this.drug_sno = drug_sno;
		this.depart = depart;
		this.bed = bed;
		this.indate = indate;
		this.outdate = outdate;
		this.advice = advice;
	}
	
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public int getPsno() {
		return psno;
	}
	public void setPsno(int psno) {
		this.psno = psno;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getDrug_sno() {
		return drug_sno;
	}
	public void setDrug_sno(String drug_sno) {
		this.drug_sno = drug_sno;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getOutdate() {
		return outdate;
	}
	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}

	
}
