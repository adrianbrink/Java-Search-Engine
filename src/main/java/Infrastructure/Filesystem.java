package Infrastructure;

import java.io.*;

public class Filesystem {

    private static String fileDestination = System.getProperty("user.dir") + "/files/";

    public static String readFromFile(String fileName) throws IOException {
        FileReader fr = new FileReader(fileDestination + fileName);
        BufferedReader br = new BufferedReader(fr);

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }

        return sb.toString();
    }

    public static void writeToFile(File file, String content, boolean shouldAppend) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), shouldAppend);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
    }

    public static void writeToFileByName(String fileName, String content, boolean shouldAppend) throws IOException {

        File file = createFile(fileName);
        writeToFile(file, content, shouldAppend);
    }

    public static void deleteFile(String fileName)
    {
        new File(fileDestination + fileName).delete();
    }

    public static File createFile(String fileName) throws IOException {

        File file= new File(fileDestination + fileName);

        if (!file.exists()) file.createNewFile();

        return file;
    }
}
