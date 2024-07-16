package com.myspringboot.jobms.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private long minSal;
    private long maxSal;

    private String location;
    private long companyId;

    public Job() {
    }

    public Job(long id, String title, String description, long minSal, long maxSal, String location, long companyId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSal = minSal;
        this.maxSal = maxSal;
        this.location = location;
        this.companyId = companyId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMinSal() {
        return minSal;
    }

    public void setMinSal(long minSal) {
        this.minSal = minSal;
    }

    public long getMaxSal() {
        return maxSal;
    }

    public void setMaxSal(long maxSal) {
        this.maxSal = maxSal;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", minSal=" + minSal +
                ", maxSal=" + maxSal +
                ", location='" + location + '\'' +
                '}';
    }
}
