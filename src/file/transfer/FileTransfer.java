/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.transfer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 *
 * @author Khaled.Gamal
 */
public class FileTransfer {

    private static final Logger LOGGER = Logger.getLogger(FileTransfer.class.getName());

    public static void copyListedFilesToDirectory(File source, File destination) throws IOException {
        List<Path> filePaths = new ArrayList<>();
        Files.find(Paths.get(source.toURI()),
                Integer.MAX_VALUE,
                (filePath, fileAttr) -> {
                    return !filePath.toString().endsWith(".rtf") && fileAttr.isRegularFile();
                })
                .forEach((Path t) -> {
                    filePaths.add(t);
                });
        copyfiles(filePaths, destination);
        LOGGER.log(Level.INFO, "files copied successfully");
    }

    private static void copyfiles(List<Path> p, File dest) throws IOException {
        if (!dest.isDirectory()) {
            dest.mkdirs();
        }
        for (Path x : p) {
            FileUtils.copyFileToDirectory(x.toFile(), dest);
            //LOGGER.log(Level.INFO, "file {0} copied to the {1}", new Object[]{x.toAbsolutePath(), dest});
        }
    }

    public static void main(String[] args) throws IOException {
        /**
         * Scanner sc = new Scanner(System.in); System.out.println("enter
         * source"); String source = sc.next(); System.out.println("enterdest");
         * String destination = sc.next();
         */

        File sourceFolder = new File("D:\\newdest");
        File destinationFolder = new File("D:\\finaldest\\final11");

//        List<Path> x = new ArrayList<>();
//        x = FileTransfer.listFiles(parent);
//        FileTransfer.copyfiles(FileTransfer.listFiles(parent), dest);
//        System.out.println(System.currentTimeMillis());
        FileTransfer.copyListedFilesToDirectory(sourceFolder, destinationFolder);
//        System.out.println(System.currentTimeMillis());

    }

}
