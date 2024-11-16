package _14_Spring_Taskmanagement_API.repository;

import _14_Spring_Taskmanagement_API.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
