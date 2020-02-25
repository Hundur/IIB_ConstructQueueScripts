import java.util.ArrayList;
import java.util.List;

public class Utils {

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
