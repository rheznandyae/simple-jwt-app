package test.simplejwtapp.javadev.job.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.simplejwtapp.javadev.job.core.Job;
import test.simplejwtapp.javadev.job.service.JobService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {
    @Autowired
    private JobService jobService;
    @GetMapping
    public ResponseEntity<Iterable<Job>> getAllJobList() throws IOException {
        return ResponseEntity.ok(jobService.getAllJobs());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Job> getJobDetail(@PathVariable(value= "id") String id) throws IOException {
        return ResponseEntity.ok(jobService.getJobDetail(id));
    }

}
