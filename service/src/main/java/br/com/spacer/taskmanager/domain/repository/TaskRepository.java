package br.com.spacer.taskmanager.domain.repository;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.spacer.taskmanager.domain.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Task t SET t.title = :title, t.description = :description, t.finishAt = :finishAt WHERE t.id = :uuid")
    void updateTask(@Param("uuid") UUID id, @Param("title") String title, @Param("description") String description, @Param("finishAt")OffsetDateTime offsetDateTime);
}
