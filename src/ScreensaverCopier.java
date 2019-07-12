import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreensaverCopier {
    private final static String src = "c:/Users/1072938/AppData/Local/Packages/Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy/LocalState/Assets/";
    private final static String dst = "c:/Users/1072938/desktop/Pictures/Assets Img/";


    public static void main(String[] args) throws IOException {
        Path source = Paths.get(src);
        Path destination = Paths.get(dst);
        
        checkDirectory(destination);

        Files.walk(source)
                .filter(s -> Files.isRegularFile(s))
                .filter(ScreensaverCopier::returnBig)
                .forEach(s -> {
                    Path randomName = Paths.get(destination + File.separator + (int) (Math.random() * 1000000) + ".jpg");
                    copy(s, randomName);
                });
    }
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void checkDirectory(Path destination) {
        if (Files.notExists(destination))
            destination.toFile().mkdirs();
    }
    
    private static void copy(Path source, Path dst) {
        try {
            Files.copy(source, dst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean returnBig(Path path) {
        try {
            return Files.size(path) > 150000;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
