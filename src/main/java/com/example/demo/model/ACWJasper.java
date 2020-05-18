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

}