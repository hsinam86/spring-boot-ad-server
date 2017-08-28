package com.ad.server.domain;

import java.sql.Timestamp;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/*
 * a simple domain entity doubling as a DTO
 */
@Entity
@Table( name = "Advertise" ,uniqueConstraints={@UniqueConstraint(columnNames={"partnerId"})})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Advertise {

   @Id
    @GeneratedValue()
    private long id;
	   
    @Column(nullable = false)
    private String partnerId;
    
    @Column()
    private int duration;

    @Column()
    private String adContent;
    
    @Column()
    private Timestamp createdDate;

    public Advertise() {
    }

    public Advertise(String partnerId, String adContent, int duration) {
        this.partnerId = partnerId;
        this.adContent = adContent;
        this.duration = duration;
    }
    
    public long getId() {
        return this.id;
    }

    // for tests ONLY
    public void setId(long id) {
        this.id = id;
    }
    
    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

    @Override
    public String toString() {
        return "Advertise {" +
                "partnerId=" + partnerId +
                ", adContent='" + adContent + '\'' +
                ", duration='" + duration +
                 ", createdDate='" + createdDate +
                '}';
    }
}
