package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

public class Mark implements Command {
    private static final String ERROR = "Task not in list.";
    private static final String SUCCESS = "Task has been marked.";
    private static final String FORMAT = "mark 'task number'";

    private final int taskNum;

    private Mark(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Storage<TaskList> storage) {
        if (this.taskNum < 1 || this.taskNum > taskList.size()) {
            Ui.showReply(ERROR);
            return;
        }
        taskList.markTask(this.taskNum);
        Ui.showReply(SUCCESS + taskList.get(this.taskNum));
    }

    /**
     * @return Parser that can parse the mark command.
     * @see Parser
     */
    public static Parser<Command> parser() {
        return Parser.strParserIgnoreCase("mark")
                .thenIgnore(Parser.skipSpace())
                .ignoreThen(Parser.decimal())
                .<Command>map(x -> new Mark(x))
                .overrideMsg(FORMAT);
    }
}
