import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowFinder {

    public List<String> findFlows(String rootPath) {

        File dir = new File(rootPath);

        String[] dirArray = dir.list();
        List<String> newFilesFound;
        List<String> dirOnlyList = new ArrayList<String>();
        List<String> filesFound = findFiles(dir);

        if(dirArray != null)
            dirOnlyList = findDirs(dirArray);

        for(String dirPath : dirOnlyList){
            newFilesFound = enterDir(rootPath + "/" + dirPath);

            if(newFilesFound.size() != 0)
                filesFound.addAll(newFilesFound);
        }

        return filesFound;
    }

    public static List<String> enterDir(String dirPath){

        File dir = new File(dirPath);

        String[] dirArray = dir.list();
        List<String> dirOnlyList = new ArrayList<String>();
        List<String> filesFound = new ArrayList<String>();
        List<String> newFilesFound;

        if(dirArray != null)
            dirOnlyList = findDirs(dirArray);

        for(String filePath : findFiles(dir))
            filesFound.add(dirPath + "/" + filePath);

        if(dirOnlyList.size() == 0)
            return filesFound;

        for(String newDirPath : dirOnlyList) {
            newDirPath = dirPath + "/" + newDirPath;
            newFilesFound = enterDir(newDirPath);

            if(newFilesFound.size() != 0)
                filesFound.addAll(newFilesFound);
        }

        return filesFound;
    }

    public static List<String> findFiles(File dir){

        List<String> filesFound;
        String[] tempArray;

        tempArray = dir.list(new ExtensionAwareFilenameFilter("msgflow", "subflow"));

        if (tempArray != null) {
            filesFound = Arrays.asList(tempArray);
            return new ArrayList<>(filesFound);
        }
        else
            return filesFound = new ArrayList<>();
    }

    public static List<String> findDirs(String[] dirArray){

        List<String> dirOnlyList = new ArrayList<>();

        for(String arrayValue : dirArray){
            if(!arrayValue.contains("."))
                dirOnlyList.add(arrayValue);
        }

        return dirOnlyList;
    }

}
