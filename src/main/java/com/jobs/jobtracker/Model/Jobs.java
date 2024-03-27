package com.jobs.jobtracker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jobs {
    @Id
    private ObjectId id;
    private String jobRequisitionId;
    private String jobTitle;
    private String jobLocation;
    private String jobType;
    private String jobPostedDate;
    private String companyDescription;
    private String positionSummary;
    private String jobDeadlineDate;
    private String jobAddress;
    private String referralEmployeeName;
    private String applicationStatus;
    private User userInfo;
}