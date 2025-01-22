import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class HttpStatusImageDownloader {

    private final HttpStatusChecker checker = new HttpStatusChecker();

    public void downloadStatusImage(int code) throws Exception {
        String imageUrl = checker.getStatusImage(code);
        try (BufferedInputStream in = new BufferedInputStream(new URL(imageUrl).openStream());
        FileOutputStream out = new FileOutputStream(code + ".jpg")) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer, 0, buffer.length))!= -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("Image for HTTP status " + code + "downloaded successfully.");
        } catch (IOException e) {
            throw new Exception("Fieled to download image for HTTP status " + code + e);
        }
    }
}
