package org.example;

import com.todo.TaskRepository;

import java.util.List;

public class TaskService {

    private TaskRepository taskRepository = new TaskRepository();
    private final CompletedTaskRepository completedTaskRepository = new CompletedTaskRepository();

    public void addTask(String description, String priority, String dueDate) {
        Task task = new Task(description, priority, dueDate);
        taskRepository.addTask(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public void completeTask(int taskId) {
        taskRepository.completeTask(taskId);
    }

    public List<CompletedTask> getAllCompletedTasks() {
        return completedTaskRepository.getAllCompletedTasks();
    }
}
