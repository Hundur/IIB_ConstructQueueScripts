import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToFile {

    public void writeQueuesToMQSCCreateFile(List<String> queues) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("CreateQueues.mqsc"));

            for (String queue : queues) {
                writer.write(String.format("DEFINE QLOCAL('%s') +\n", queue));
                writer.write("REPLACE + \n");
                writer.write("MAXDEPTH(10000)\n");
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeQueuesToMQSCClearFile(List<String> queues) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ClearQueues.mqsc"));

            for (String queue : queues) {
                writer.write(String.format("CLEAR QLOCAL('%s')\n", queue));
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeQueuesToMQSCDeleteFile(List<String> queues) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("DeleteQueues.mqsc"));

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
