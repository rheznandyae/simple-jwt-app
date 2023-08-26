package test.simplejwtapp.javadev.job.service;

import test.simplejwtapp.javadev.job.core.Job;

import java.io.IOException;
import java.util.List;

public interface JobService {
    public List<Job> getAllJobs() throws IOException;
    public Job getJobDetail(String id) throws IOException;
}
