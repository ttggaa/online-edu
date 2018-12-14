package com.edufe.module.entity ; 

import java.io.Serializable; 

public class Dict implements Serializable {  

  private static final long serialVersionUID = 2L; 

    private String remark; 

    private int orderfield; 

    private String label; 

    private String code; 

    private String fieldname; 

    private String fieldid; 

    public String getRemark(){ 
        return this.remark; 
    } 

    public void setRemark(String remark){ 
        this.remark=remark; 
    } 


    public int getOrderfield(){ 
        return this.orderfield; 
    } 

    public void setOrderfield(int orderfield){ 
        this.orderfield=orderfield; 
    } 


    public String getLabel(){ 
        return this.label; 
    } 

    public void setLabel(String label){ 
        this.label=label; 
    } 


    public String getCode(){ 
        return this.code; 
    } 

    public void setCode(String code){ 
        this.code=code; 
    } 


    public String getFieldname(){ 
        return this.fieldname; 
    } 

    public void setFieldname(String fieldname){ 
        this.fieldname=fieldname; 
    } 


    public String getFieldid(){ 
        return this.fieldid; 
    } 

    public void setFieldid(String fieldid){ 
        this.fieldid=fieldid; 
    } 


}