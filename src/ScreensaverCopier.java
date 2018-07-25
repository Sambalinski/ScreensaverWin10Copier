import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreensaverCopier {
    final static String src = "c:/Users/Sambalinski/AppData/Local/Packages/Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy/LocalState/Assets/";
    final static String dst = "d:/Pictures/Assets Img/";
    public static void main(String[] args) throws IOException {
        Path source = Paths.get(src);
        Path destination = Paths.get(dst);

        if (Files.notExists(destination)) Files.createDirectory(destination);

        Files.walk(source)
                .filter(s -> Files.isRegularFile(s))
                .filter(s -> returnBig(s))
                .forEach(s -> {
                    Path randomName = Paths.get(destination + File.separator + String.valueOf((int) (Math.random() * 1000000)) + ".jpg");
                    copy(s, randomName);
                });
    }

    public static void copy(Path sourse, Path dst) {
        try {
            Files.copy(sourse, dst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean returnBig (Path path) {
        try{
        return Files.size(path) > 105000;}
        catch (IOException e) {e.printStackTrace();}
        return true;
    }
}