package workshop;

import java.util.ArrayList;
import java.util.List;

public class ElfWorkshop {
    private List<String> taskList; // primitive obsession

    public ElfWorkshop() {
        this.taskList = new ArrayList<>();
    }

    public List<String> getTaskList() {
        return taskList; // better return a copy of the list or an unmodifiable wrapper
    }

    public void addTask(String task) {
        if (task != null && !task.isEmpty()) {
            taskList.add(task);
        }
    }

    public String completeTask() { // signature?
        if (!taskList.isEmpty()) {
            return taskList.remove(0);
        }
        return null;
    }
}