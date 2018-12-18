package com.goodHot.fun.repository;

import com.goodHot.fun.domain.ArchiveTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ArchiveTaskRepository extends MongoRepository<ArchiveTask, String> {

    @Query(value = "{'processing': false, 'retry': {$lt: 3}}", count = true)
    Long taskCount();

    @Query("{'processing': false, 'retry': {$lt: 3}}")
    Page<ArchiveTask> takeTask(Pageable pageable);

}
