package edgefuck.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProcessList {

    public static List<ProcessInfo> listProcesses() {
        List<ProcessInfo> processes = new ArrayList<>();

        ProcessHandle.allProcesses().forEach(process -> {
            ProcessInfo info = new ProcessInfo(
                    process.pid(),
                    process.info().user().orElse("-"),
                    process.info().command().orElse("-"),
                    process.info().commandLine().orElse("-")
            );
            processes.add(info);
        });

        return processes;
    }

    public record ProcessInfo(long pid, String user, String process, String cmdLine) {
    }

}
