package be.stivizu.projects.hashcode.util;

import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class FileUtil {

    private static final String RESOURCES_DIR = "src/main/resources/";

    private static final String SOURCES_DIR = "src/main/java";

    private static final String SOURCES_ZIP_FILENAME = "src.zip";

    public static Path loadResource(final String pathFromResources) {
        final Path resource = Paths.get(RESOURCES_DIR + pathFromResources);
        if (!Files.exists(resource)) {
            throw new RuntimeException("Unable to load resource from path <" + RESOURCES_DIR + pathFromResources + ">");
        }
        return resource;
    }

    public static Stream<Path> streamFolderContents(final Path folderPath) {
        try {
            if (!Files.isDirectory(folderPath)) {
                throw new RuntimeException("Supplied path <" + folderPath + "> is not a folder!");
            }
            return Files.list(folderPath);
        } catch (IOException e) {
            throw new RuntimeException("Unable to stream folder contents for path <" + folderPath + ">", e);
        }
    }

    public static List<String> readFileContents(final Path filePath) {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file contents to a String List for file <" + filePath + ">", e);
        }
    }

    public static void clearResourceFolderOrCreateIt(final String pathFromResources) {
        Path folder = null;
        try {
            folder = loadResource(pathFromResources);
        } catch (Exception e) {
            //Skip exception - create folder
        } finally {
            if (folder != null) {
                clearFolderContents(folder);
            } else {
                final Path newPath = Paths.get(RESOURCES_DIR + pathFromResources);
                createFolder(newPath);
            }
        }
    }

    public static void createFolder(final Path directory) {
        try {
            Files.createDirectories(directory);
        } catch (Exception e) {
            throw new RuntimeException("Supplied path <" + directory + "> could not be created");
        }
    }

    public static void clearFolderContents(final Path folderPath) {
        try {
            if (!Files.isDirectory(folderPath)) {
                throw new RuntimeException("Supplied path <" + folderPath + "> is not a folder");
            }
            Files.walk(folderPath)
                    .filter(path -> !path.equals(folderPath))
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            throw new RuntimeException("Unable to clear folder contents for file <" + folderPath + ">", e);
        }
    }

    public static void createAndWriteToFile(final Path outputFolder, final Path fileName, final List<String> fileContents) {
        final Path outputPath = outputFolder.resolve(fileName);
        try {
            Files.write(outputPath, fileContents);
            createSourcesZip(outputFolder);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to output file <" + outputPath + ">", e);
        }
    }

    public static Path changeFileExtension(final Path file, final String newExtension) {
        final String fileName = file.getFileName().toString();
        final String newFileName = fileName.substring(0, fileName.lastIndexOf(".") + 1).concat(newExtension);

        return Paths.get(newFileName);
    }

    private static void createSourcesZip(final Path outputFolder) {
        try {
            deleteFileIfExists(Paths.get(SOURCES_ZIP_FILENAME));
            ZipUtil.pack(Paths.get(SOURCES_DIR).toFile(), Paths.get(SOURCES_ZIP_FILENAME).toFile());
            Files.move(Paths.get(SOURCES_ZIP_FILENAME), outputFolder.resolve(SOURCES_ZIP_FILENAME), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFileIfExists(final Path file) {
        try {
            Files.delete(file);
        } catch (NoSuchFileException e) {
            // Ignore, lets us avoid checking if file exists
        } catch (IOException e) {
            throw new RuntimeException("Was unable to delete file <" + file + ">");
        }
    }
}