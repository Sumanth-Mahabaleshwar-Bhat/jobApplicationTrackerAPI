package com.jobs.jobtracker.Repository;

import com.jobs.jobtracker.Model.Jobs;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobsRepository extends MongoRepository<Jobs, ObjectId> {
    Optional<Jobs> findJobByjobRequisitionId(String jobRequisitionId);

    Optional<Jobs> deleteJobByjobRequisitionId(String jobRequisitionId);
}
