package com.myspringboot.jobms.job;

import com.myspringboot.jobms.job.DTO.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    public List<JobWithCompanyDTO> findAllJobs();

    public Job findById(long id);

    public boolean updateJob(Job job, long id);

    public void createNewJob(Job job);

    public boolean deleteJob(long id);
}
