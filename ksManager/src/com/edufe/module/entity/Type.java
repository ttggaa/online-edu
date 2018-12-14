package com.edufe.module.entity ; 

import java.io.Serializable; 

/* 
 *  
 * Fri Sep 14 09:42:31 CST 2018 
 */  

public class Type implements Serializable {  

  private static final long serialVersionUID = 2L; 

    // datebase colume is type_code 
    private String typeCode; 

    // datebase colume is typedesc 
    private String typedesc; 

    // datebase colume is typeid 
    private int typeid; 

    // datebase colume is typename 
    private String typename; 

    // datebase colume is typesort 
    private int typesort; 

    public String getTypeCode(){ 
        return this.typeCode; 
    } 

    public void setTypeCode(String typeCode){ 
        this.typeCode=typeCode; 
    } 


    public String getTypedesc(){ 
        return this.typedesc; 
    } 

    public void setTypedesc(String typedesc){ 
        this.typedesc=typedesc; 
    } 


    public int getTypeid(){ 
        return this.typeid; 
    } 

    public void setTypeid(int typeid){ 
        this.typeid=typeid; 
    } 


    public String getTypename(){ 
        return this.typename; 
    } 

    public void setTypename(String typename){ 
        this.typename=typename; 
    } 


    public int getTypesort(){ 
        return this.typesort; 
    } 

    public void setTypesort(int typesort){ 
        this.typesort=typesort; 
    } 


}