package com.education.domain ; 

import java.io.Serializable; 

/* 
 *  
 * Tue Aug 18 07:47:19 CST 2015 
 */  

public class PracmainSub implements Serializable {  

  private static final long serialVersionUID = 2L; 
  private Integer id;
    // datebase colume is pracmain_id 
    private int pracmainId; 

    // datebase colume is difid2 
    private int difid2; 

    // datebase colume is difid3 
    private int difid3; 

    // datebase colume is difid1 
    private int difid1; 

    // datebase colume is type_code 
    private int typeCode;
    
    private String typeScore;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeScore() {
		return typeScore;
	}

	public void setTypeScore(String typeScore) {
		this.typeScore = typeScore;
	}

	public int getPracmainId(){ 
        return this.pracmainId; 
    } 

    public void setPracmainId(int pracmainId){ 
        this.pracmainId=pracmainId; 
    } 


    public int getDifid2(){ 
        return this.difid2; 
    } 

    public void setDifid2(int difid2){ 
        this.difid2=difid2; 
    } 


    public int getDifid3(){ 
        return this.difid3; 
    } 

    public void setDifid3(int difid3){ 
        this.difid3=difid3; 
    } 


    public int getDifid1(){ 
        return this.difid1; 
    } 

    public void setDifid1(int difid1){ 
        this.difid1=difid1; 
    } 


    public int getTypeCode(){ 
        return this.typeCode; 
    } 

    public void setTypeCode(int typeCode){ 
        this.typeCode=typeCode; 
    } 


}