package controler;

import java.io.File;
import static model.Dictionary.CREATED_DB_DIRECTORY;
import static model.Dictionary.CREATED_DB_DIRECTORY_FAILURE;
import static model.Dictionary.SEPARATOR;

/**
 *
 * @author Magdalena
 */
public class FileIO {

    public String generateDBDirectory(String baseDir, String newDir) {
        String newPath = baseDir + SEPARATOR + newDir;

        File file = new File(newPath);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println(CREATED_DB_DIRECTORY);
            } else {
                System.err.println(CREATED_DB_DIRECTORY_FAILURE);
                return null;
            }
        }
        return newPath;
    }
}
