package demo.resource;

import org.springframework.core.io.FileSystemResource;

import java.io.*;

public class ResourceDemo {
    public static void main(String[] args) throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource(
                "D:\\Program Files\\CodeBase\\simpleframework\\src\\main\\java\\demo\\resource\\test.txt"
        );
        File file = fileSystemResource.getFile();
        System.out.println(file.length());
        OutputStream outputStream = fileSystemResource.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("Hello Li");
        bufferedWriter.flush();
        outputStream.close();
        bufferedWriter.close();
    }

}
