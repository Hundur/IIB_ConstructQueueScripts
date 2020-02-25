import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateMQSCScript {

    public static void main(String[] args) {

        // Search folders for msgflows and subflows
        FlowFinder flowFinder = new FlowFinder();

        List<String> flowsFound = flowFinder.findFlows("C:\\Users\\Jesper\\IBM\\IIBT10\\workspace\\tip023-ds-aps-gdm-java\\TIP023_APS_GDM");

        // Search files for queues
        File file;
        QueueFinder queueFinder = new QueueFinder();
        List<String> queuesFound = new ArrayList<>();

        for (String flowPath : flowsFound) {
            file = new File(flowPath);
            queuesFound.addAll(queueFinder.findQueues(file));
        }

        // Remove dupes
        List<String> noDupesQueues;
        noDupesQueues = Utils.removeDuplicateElements(queuesFound);

        // Write mqsc files
        WriteToFile writeToFile = new WriteToFile();

        writeToFile.writeQueuesToMQSCCreateFile(noDupesQueues);
        writeToFile.writeQueuesToMQSCClearFile(noDupesQueues);
        writeToFile.writeQueuesToMQSCDeleteFile(noDupesQueues);
    }
}
