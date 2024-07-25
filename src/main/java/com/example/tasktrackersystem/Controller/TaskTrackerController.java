package com.example.tasktrackersystem.Controller;

import com.example.tasktrackersystem.Api.ApiReponse;
import com.example.tasktrackersystem.Model.TaskTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/task-tracker")
public class TaskTrackerController {
    ArrayList<TaskTracker> taskTrackers = new ArrayList<>();

    //Read
    @GetMapping("/get")
    public ArrayList<TaskTracker> getTaskTrackers() {
        return taskTrackers;
    }

    //Add
    @PostMapping("/add")
    public ApiReponse addTaskTracker(@RequestBody TaskTracker taskTracker) {
        taskTrackers.add(taskTracker);
        return new ApiReponse("Added Success");
    }

    //Update
    @PutMapping("/update/{index}")
    public ApiReponse updateTaskTracker(@PathVariable int index, @RequestBody TaskTracker taskTracker) {
        taskTrackers.set(index, taskTracker);
        return new ApiReponse("Updated Success");
    }

    //Delete
    @DeleteMapping("delete/{index}")
    public ApiReponse deleteTaskTracker(@PathVariable int index) {
        taskTrackers.remove(index);
        return new ApiReponse("Deleted Success");
    }

    //Check Status
    @PutMapping("/check-status/{index}")
    public ApiReponse checkStatus(@PathVariable int index) {
        if (index < 0 || index >= taskTrackers.size()) {
            return new ApiReponse("Task not found");
        }
        TaskTracker currentTask = taskTrackers.get(index);

        if ("not done".equals(currentTask.getStatus())) {
            currentTask.setStatus("done");
            return new ApiReponse("Task status updated to done.");
        } else {
            return new ApiReponse("Task is already done successfully.");
        }
    }
    //Search
    @GetMapping("/search/{title}")
    public ApiReponse searchTask(@PathVariable String title) {
        List<String> matchingTitles = new ArrayList<>();

        for (TaskTracker taskTracker : taskTrackers) {
            if (taskTracker.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingTitles.add(taskTracker.getTitle());
            }
        }

        if (matchingTitles.isEmpty()) {
            return new ApiReponse("Task not found");
        } else {
            String responseMessage = "Tasks found: " + String.join(", ", matchingTitles);
            return new ApiReponse(responseMessage);
        }
    }

}
