import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class QueueFinder {

    public List<String> findQueues(File file){
        java.util.List<String> queuesFound = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            boolean moreLines = true;
            while (moreLines) {
                line = br.readLine();

                if (line == null)
                    moreLines = false;
                else
                if (line.contains("queueName=\"")) {

                    line = line.split("\\bqueueName=\"")[1];
                    line = line.split("\\b\"")[0];
                    queuesFound.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return queuesFound;
    }

}
