package com.jobs.jobtracker.Repository;

import com.jobs.jobtracker.Model.Jobs;
import com.jobs.jobtracker.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
}
