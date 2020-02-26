import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateMQSCScript {

    public static void main(String[] args) {

        String rootDir = args[0];
        String targetDir = args[1];

        //String rootDir = "C:\\Users\\Jesper\\IBM\\IIBT10\\workspace\\tip023-ds-aps-gdm-java\\TIP023_APS_GDM";
        //String targetDir = "C:\\Users\\Jesper\\IdeaProjects\\IIB_ConstructQueueScripts\\CreateQueueScripts\\out";

        // Search folders for msgflows and subflows
        FlowFinder flowFinder = new FlowFinder();
      
        List<String> flowsFound = flowFinder.findFlows(rootDir);

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

        // Find project name
        String projectName;
        projectName = Utils.findProjectName(rootDir);

        // Write mqsc files
        WriteToFile writeToFile = new WriteToFile();

        /*
        for (String queues : noDupesQueues) {
            System.out.println(queues);
        }
        */

        writeToFile.writeQueuesToMQSCCreateFile(noDupesQueues, targetDir, projectName);

        //writeToFile.writeQueuesToMQSCClearFile(noDupesQueues, projectName);
        //writeToFile.writeQueuesToMQSCDeleteFile(noDupesQueues, projectName);
    }
}
