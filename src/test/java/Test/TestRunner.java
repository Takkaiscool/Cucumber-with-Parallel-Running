package Test;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Factory;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipOutputStream;

public class TestRunner {

    @Factory
    public Object[] getTestClasses(){
        String env = System.getProperty("env");
        String []envs = env.split(",");

        Object[] testObject = new Object[envs.length];
        for(int i=0;i< envs.length;i++){
            testObject[i] = new Executor(envs[i]);
        }
        return testObject;
    }

    @AfterSuite
    public void zipTheFile() throws FileNotFoundException {
        try (ZipArchiveOutputStream archive = new ZipArchiveOutputStream(new FileOutputStream("TestReports.zip"))) {

            File folderToZip = new File("TestReports");

            // Walk through files, folders & sub-folders.
            Files.walk(folderToZip.toPath()).forEach(p -> {
                File file = p.toFile();

                // Directory is not streamed, but its files are streamed into zip file with
                // folder in it's path
                if (!file.isDirectory()) {
                    System.out.println("Zipping file - " + file);
                    ZipArchiveEntry entry_1 = new ZipArchiveEntry(file, file.toString());
                    try (FileInputStream fis = new FileInputStream(file)) {
                        archive.putArchiveEntry(entry_1);
                        IOUtils.copy(fis, archive);
                        archive.closeArchiveEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            // Complete archive entry addition.
            archive.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
