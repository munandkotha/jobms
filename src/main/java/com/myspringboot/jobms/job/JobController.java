package com.myspringboot.jobms.job;

import com.myspringboot.jobms.job.DTO.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<JobWithCompanyDTO> getAllJobs(){
        return jobService.findAllJobs();
    }

    @PostMapping("/jobs")
    private ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createNewJob(job);
        return new ResponseEntity("Job Successfully Created", HttpStatus.OK);
    }

    @GetMapping("/jobs/{id}")
    private ResponseEntity<Job> getJob(@PathVariable  long id){
        Job job = jobService.findById(id);
        if( job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    private ResponseEntity<String> removeJob(@PathVariable long id) {
        if(jobService.deleteJob(id))
            return new ResponseEntity<>("Deleted Job Successfully!", HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    private ResponseEntity<String> updateJob(@RequestBody Job updateJob, @PathVariable long id){
        if(jobService.updateJob(updateJob, id))
            return new ResponseEntity<>("Job updated Successfully!", HttpStatus.OK);;
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
