package test.simplejwtapp.javadev.job.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import test.simplejwtapp.javadev.job.core.Job;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class JobServiceImpl implements JobService{
    private String GET_JOB_LIST_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
    private String GET_JOB_DETAIL_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions/";
    public List<Job> getAllJobs() throws IOException {
        List<Job> jobs = new ArrayList<Job>();
        var mapper = new ObjectMapper();
        JsonNode jsonObject;

        var url = new URL(GET_JOB_LIST_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoInput(true);

        if(con.getResponseCode() == 200) {
            var inline = new StringBuilder();
            var scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }

            scanner.close();
            jsonObject = mapper.readTree(inline.toString());

            for(int i = 0; i < jsonObject.size(); i++){
                jobs.add(convertJsonNodeToJob(jsonObject.get(i)));
            }
        }
        return jobs;
    }

    public Job getJobDetail(String id) throws IOException{
        Job job = new Job();
        var mapper = new ObjectMapper();
        JsonNode jsonObject;
        var url = new URL(GET_JOB_DETAIL_URL + id);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoInput(true);

        if(con.getResponseCode() == 200) {
            var inline = new StringBuilder();
            var scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
            scanner.close();
            jsonObject = mapper.readTree(inline.toString());
            job = convertJsonNodeToJob(jsonObject);
        }
        return job;
    }

    private List<Job> convertJsonNodeToJobList(JsonNode listNode){
        List<Job> jobs = new ArrayList<Job>();
        for(int i = 0; i < listNode.size(); i++){
            jobs.add(convertJsonNodeToJob(listNode.get(i)));
        }
        return jobs;
    }

    private Job convertJsonNodeToJob(JsonNode jobNode){
        Job job = new Job();
        job.setId(jobNode.get("id").asText());
        job.setType(jobNode.get("type").asText());
        job.setUrl(jobNode.get("url").asText());
        job.setCompany(jobNode.get("company").asText());
        job.setCompany_url(jobNode.get("company_url").asText());
        job.setLocation(jobNode.get("location").asText());
        job.setLocation(jobNode.get("title").asText());
        job.setTitle(jobNode.get("title").asText());
        job.setDescription(jobNode.get("description").asText());
        job.setCreated_at(jobNode.get("created_at").asText());
        return job;
    }
}
