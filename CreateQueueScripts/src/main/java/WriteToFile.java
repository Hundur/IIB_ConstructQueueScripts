import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToFile {

    public void writeQueuesToMQSCCreateFile(List<String> queues, String targetDir, String projectName) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(targetDir + "\\" + projectName + ".mqsc"));

            for (String queue : queues) {
                writer.write(String.format("DEFINE QLOCAL('%s') +\n", queue));
                writer.write("REPLACE + \n");
                writer.write("MAXDEPTH(10000) +\n");
                writer.write("DESCR('')\n");
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeQueuesToMQSCClearFile(List<String> queues, String projectName) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(projectName + "ClearQueues.mqsc"));

            for (String queue : queues) {
                writer.write(String.format("CLEAR QLOCAL('%s')\n", queue));
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeQueuesToMQSCDeleteFile(List<String> queues, String projectName) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(projectName + "DeleteQueues.mqsc"));

            for (String queue : queues) {
                writer.write(String.format("DELETE QLOCAL('%s')\n", queue));
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
