package com.jobs.jobtracker.Service;

import com.jobs.jobtracker.Model.Jobs;
import com.jobs.jobtracker.Repository.JobsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobsService {
    @Autowired
    private JobsRepository jobsRepository;

    public JobsService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public List<Jobs> allJobsList() {
        return jobsRepository.findAll();
    }

    public Optional<Jobs> jobListById(String jobRequisitionId) {
        return jobsRepository.findJobByjobRequisitionId(jobRequisitionId);
    }

    public Jobs createJob(Jobs job) {
        return jobsRepository.save(job);
    }

    public void updateJob(String id, Jobs updatedJob) {
        Optional<Jobs> existingJob = jobsRepository.findJobByjobRequisitionId(id);
        existingJob.ifPresent(job -> {
            job.setJobRequisitionId(updatedJob.getJobRequisitionId());
            job.setJobTitle(updatedJob.getJobTitle());
            job.setJobLocation(updatedJob.getJobLocation());
            job.setJobType(updatedJob.getJobType());
            job.setJobPostedDate(updatedJob.getJobPostedDate());
            job.setCompanyDescription(updatedJob.getCompanyDescription());
            job.setPositionSummary(updatedJob.getPositionSummary());
            job.setJobDeadlineDate(updatedJob.getJobDeadlineDate());
            job.setJobAddress(updatedJob.getJobAddress());
            job.setReferralEmployeeName(updatedJob.getReferralEmployeeName());
            job.setApplicationStatus(updatedJob.getApplicationStatus());
            jobsRepository.save(job);
        });
    }

    public void deleteJob(String id) {
        jobsRepository.deleteJobByjobRequisitionId(id);
    }
}
