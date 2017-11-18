import org.junit.Test;

import java.io.IOException;

public class MyHttpRequestExecutorTest {

    @Test
    public void testDownloader() throws IOException {
        MyHttpRequestExecutor executor = new MyHttpRequestExecutor();
        String content = executor.getStringContent("get");
        System.out.println(content);
    }

}