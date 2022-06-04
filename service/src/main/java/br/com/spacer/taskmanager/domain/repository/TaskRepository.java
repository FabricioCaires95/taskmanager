package br.com.spacer.taskmanager.domain.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.spacer.taskmanager.domain.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
