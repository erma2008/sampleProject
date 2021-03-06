package API.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayloadConvertUtil {
    public static String generateStringFromResources(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path )));
    }
}
