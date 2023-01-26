import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskList {
    private final List<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public int size() {
        return lst.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public String get(int number) {
        int index = number - 1;
        return String.format("\n\t%d) %s\n", number, this.lst.get(index));
    }

    public String getLast() {
        return this.get(this.size());
    }

    public void addTask(Task task) {
        this.lst.add(task);
    }

    public void markTask(int number) {
        int index = number - 1;
        this.lst.set(index, this.lst.get(index).markDone());
    }

    public void unmarkTask(int number) {
        int index = number - 1;
        this.lst.set(index, this.lst.get(index).markNotDone());
    }

    public void deleteTask(int number) {
        int index = number - 1;
        this.lst.remove(index);
    }

    public String findByDate(LocalDate date) {
        String res = IntStream.range(0, this.size())
                .filter(i -> this.lst.get(i).hasDate(date))
                .mapToObj(i -> String.format("\n\t%d) %s", i + 1, this.lst.get(i)))
                .reduce("", (a, b) -> a + b);
        if (res.isEmpty()) {
            return "No tasks found.\n";
        }
        return res + '\n';
    }
    
    @Override
    public String toString() {
        if (this.lst.isEmpty()) {
            return "You have no tasks\n";
        }

        return IntStream.range(0, this.size())
                .mapToObj(i -> String.format("\n\t%d) %s", i + 1, this.lst.get(i)))
                .reduce("", (a, b) -> a + b) + '\n';
    }
}
