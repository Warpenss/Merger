import org.apache.commons.io.FileUtils;

import java.io.*;

public class Merger {
    public static void main(String[] args) throws Exception {
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };

        System.out.println("Enter directory path with .txt files");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String ReadPath = reader.readLine();
        ReadPath = ReadPath.replace("\\", "/");
        ReadPath = ReadPath.replace("\"", "/");
        ReadPath = ReadPath.replace("%", "");

        System.out.println("Enter new file path ");
        String WritePath = reader.readLine();
        WritePath = WritePath.replace("\\", "/");
        WritePath = WritePath.replace("\"", "/");
        WritePath = WritePath.replace("%", "");

        File folder = new File(ReadPath);
        File[] listOfFiles = folder.listFiles(filter);

        String content = "";
        for (File file : listOfFiles) {
            content += FileUtils.readFileToString(file) + "\n";
        }
        try
        {
            FileWriter fw = new FileWriter(WritePath, true); //the true will append the new data
            fw.write(content + "\n");//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
        System.out.println(content);
    }
}
