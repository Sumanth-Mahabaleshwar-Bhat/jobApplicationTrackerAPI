package com.jobs.jobtracker.Controller;

import com.jobs.jobtracker.Model.Jobs;
import com.jobs.jobtracker.Service.JobsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/jobList")
@RestController
@RequestMapping("/api/v1/jobsList")
public class JobsController {
    @Autowired
    private JobsService jobsService;
    @GetMapping
    public ResponseEntity<List<Jobs>> getAllJobsList() {
        return new ResponseEntity<List<Jobs>>(jobsService.allJobsList(), HttpStatus.OK);
    }

    @GetMapping("/{jobRequisitionId}")
    public ResponseEntity<Optional<Jobs>> getJobListById(@PathVariable String jobRequisitionId) {
        return new ResponseEntity<Optional<Jobs>>(jobsService.jobListById(jobRequisitionId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Jobs> createJob(@RequestBody Jobs job) {
        return new ResponseEntity<Jobs>(jobsService.createJob(job), HttpStatus.CREATED);
    }

    @PutMapping("/{jobRequisitionId}")
    public ResponseEntity<Optional<Jobs>> updateJob(@PathVariable String jobRequisitionId, @RequestBody Jobs updatedJob) {
        Optional<Jobs> updatedJobApplicationDetails = jobsService.updateJob(jobRequisitionId, updatedJob);
        return new ResponseEntity<Optional<Jobs>>(updatedJobApplicationDetails, HttpStatus.OK);
    }

    @DeleteMapping("/{jobRequisitionId}")
    public ResponseEntity<Void> deleteJob(@PathVariable String jobRequisitionId) {
        jobsService.deleteJob(jobRequisitionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
