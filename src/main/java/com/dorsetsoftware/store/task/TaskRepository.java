package com.dorsetsoftware.store.task;

import java.util.List;
import com.dorsetsoftware.store.status.Status;
import com.dorsetsoftware.store.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByStatusAndUserOrderByTaskIndexAsc(Status status, User user);

    @Query("SELECT MAX(t.taskIndex) FROM Task t WHERE t.status.id = :statusId")
    Integer findMaxTaskIndexByStatus(@Param("statusId") Integer statusId);

    @Modifying
    @Query("UPDATE Task t SET t.taskIndex = t.taskIndex + 1 " +
            "WHERE t.status = :status AND t.taskIndex < :startIndex AND t.taskIndex >= :endIndex")
    void incrementIndicesFrom(@Param("status") Status status,
            @Param("startIndex") int startIndex, @Param("endIndex") int endIndex);

    @Modifying
    @Query("UPDATE Task t SET t.taskIndex = t.taskIndex - 1 " +
            "WHERE t.status = :status AND t.taskIndex <= :endIndex AND t.taskIndex > :startIndex")
    void decrementIndicesFrom(@Param("status") Status status,
            @Param("startIndex") int startIndex, @Param("endIndex") int endIndex);
}
