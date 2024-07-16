package com.myspringboot.jobms.job.impl;

import com.myspringboot.jobms.job.DTO.JobWithCompanyDTO;
import com.myspringboot.jobms.job.Job;
import com.myspringboot.jobms.job.JobService;
import com.myspringboot.jobms.job.external.Company;
import com.myspringboot.jobms.job.repos.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepo;

    public JobServiceImpl(JobRepository jobRepo) {
        this.jobRepo = jobRepo;
    }

    @Override
    public List<JobWithCompanyDTO> findAllJobs() {
        List<Job> jobs = jobRepo.findAll();
        return jobs.stream().map(this::getJobWithCompDto).collect(Collectors.toList());
    }

    private JobWithCompanyDTO getJobWithCompDto(Job job) {
        RestTemplate restTemplate = new RestTemplate();
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        Company company = restTemplate.getForObject("http://localhost:8082/companies/" + job.getCompanyId(), Company.class);
        jobWithCompanyDTO.setJob(job);
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;
    }

    @Override
    public Job findById(long id) {
        return jobRepo.findById(id).orElse(null);
        /*for(Job job: jobs) {
            if(job.getId() == id) {
                return job;
            }
        }
        return null;*/
    }

    @Override
    public boolean updateJob(Job updateJob, long id) {
        Job job = jobRepo.findById(id).orElse(null);
        if (job != null) {
            job.setDescription(updateJob.getDescription());
            job.setTitle(updateJob.getTitle());
            job.setMinSal(updateJob.getMinSal());
            job.setMaxSal(updateJob.getMaxSal());
            job.setLocation(updateJob.getLocation());
            jobRepo.save(job);
            return true;
        }
        return false;
    }

    @Override
    public void createNewJob(Job job) {
        jobRepo.save(job);
       /* job.setId(idCounter++);
        jobs.add(job);*/
    }

    @Override
    public boolean deleteJob(long id) {
        jobRepo.deleteById(id);
        return true;
    }
}
