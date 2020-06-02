package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "report1")
public class ACWJasper
{
    @Id
    
    public String extension;
    public String agent_name;
    public String start;
    public String end;
    public String duration;
    public String disposition;
    public String case1;
    public String detail;

    public String getExtension() {
        return extension;
    } 
    public void setExtension(String extension) {
            this.extension= extension;
    }
    
    public String getAgent_name() {
        return agent_name;
    } 
    public void setAgent_name(String agent_name) {
            this.agent_name= agent_name;
    }
    public String getStart() {
        return start;
    } 
    public void setStart(String start) {
            this.start= start;
    }
    public String getEnd() {
        return end;
    } 
    public void setEnd(String end) {
            this.end= end;
    }
    public String getDuration() {
        return duration;
    } 
    public void setDuration(String duration) {
            this.duration= duration;
    }
    public String getDisposition() {
        return disposition;
    } 
    public void setDisposition(String disposition) {
            this.disposition= disposition;
    }
    public String getCase1() {
        return case1;
    } 
    public void setCase1(String case1) {
            this.case1= case1;
    }
    public String getDetail() {
        return detail;
    } 
    public void setDetail(String detail) {
            this.detail= detail;
    }
    
}