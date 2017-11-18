import okhttp3.*;

import java.io.IOException;

public class MyHttpRequestExecutor {

    private static String url="https://httpbin.org/";
    private String answer;
    private OkHttpClient client;

    public MyHttpRequestExecutor() {
        client = new OkHttpClient();
    }

    private void getContent(String method) throws IOException {
        String url = MyHttpRequestExecutor.url + method;
        System.out.println(url);
        final Request request = new Request.Builder()
                .url(url)
                .build();


        //synchronous request
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            Headers responseHeaders = response.headers();
//            for (int i = 0; i < responseHeaders.size(); i++) {
//                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//            }
//
//            System.out.println(response.body().string());
//
//        }

        //asynchronous request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                answer = "Connection is fail";
                System.out.println(answer);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("evt successfull");
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    System.out.println(responseBody.string());
                }


                answer = response.body().string();
                System.out.println("response.body().string()"+answer);
            }
        });
    }

    public String getStringContent(String method) throws IOException {
        getContent(method);
        return answer;
    }

}
