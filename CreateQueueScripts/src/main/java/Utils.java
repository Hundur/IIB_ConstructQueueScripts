import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {

    public static String findProjectName(String dirPath) {

        File dir = new File(dirPath);
        String projectFile;

        projectFile = Objects.requireNonNull(dir.list(new ExtensionAwareFilenameFilter("project")))[0];

        String projectName = "fileName";

        try {
            BufferedReader br = new BufferedReader(new FileReader(dirPath + "\\" + projectFile));

            String line;
            boolean moreLines = true;
            while (moreLines) {
                line = br.readLine();

                if (line == null)
                    moreLines = false;
                else {
                    if (line.contains("<name>")) {
                        line = line.trim();
                        line = line.split("<name>\\b")[1];
                        line = line.split("\\b</name>")[0];

                        projectName = line;
                        moreLines = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projectName;
    }

    public static List<String> removeDuplicateElements(List<String> queues) {

        List<String> noDupesQueues = new ArrayList<>();
        boolean existsInArray;

        for (String queue : queues) {
            existsInArray = false;

            if (noDupesQueues.size() == 0) {
                noDupesQueues.add(queue);
                continue;
            }

            for (String dupeQueue : noDupesQueues) {
                if (dupeQueue.equals(queue)) {
                    existsInArray = true;
                    break;
                }
            }

            if (!existsInArray)
                noDupesQueues.add(queue);
        }

        return noDupesQueues;
    }
}
