package org.example;

import java.util.List;
import java.util.Scanner;

public class TodoApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();

        while (true) {
            System.out.println("\nTo-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. View Completed Tasks");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Add task logic (same as before)
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter priority: ");
                    String priority = scanner.nextLine();
                    System.out.print("Enter due date: ");
                    String dueDate = scanner.nextLine();
                    taskService.addTask(description, priority, dueDate);
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    // View all tasks
                    List<Task> tasks = taskService.getAllTasks();
                    for (Task task : tasks) {
                        System.out.println(task.getId() + ": " + task.getDescription() + " (Priority: " + task.getPriority() + ", Due: " + task.getDueDate() + ")");
                    }
                    break;
                case 3:
                    // Mark task as completed
                    System.out.print("Enter the ID of the task to complete: ");
                    int taskId = scanner.nextInt();
                    taskService.completeTask(taskId);
                    System.out.println("Task completed successfully!");
                    break;
                case 4:
                    // View completed tasks
                    List<CompletedTask> completedTasks = taskService.getAllCompletedTasks();
                    System.out.println("Completed Tasks:");
                    for (CompletedTask completedTask : completedTasks) {
                        System.out.println(completedTask);
                    }
                    break;
                case 5:
                    HibernateUtil.shutdown();
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
