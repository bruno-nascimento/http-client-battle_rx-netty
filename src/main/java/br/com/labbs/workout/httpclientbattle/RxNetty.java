package br.com.labbs.workout.httpclientbattle;

import br.com.labbs.workout.httpclientbattle.shared.Env;
import io.reactivex.netty.protocol.http.client.HttpClient;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;

@SuppressWarnings("unused")
public class RxNetty implements br.com.labbs.workout.httpclientbattle.shared.HttpClient<HttpClientRequest, HttpClientResponse, HttpClient> {

    @Override
    public String getClientName() {
        return "RxNetty";
    }

    @Override
    public HttpClientRequest newRequest(String url) {
        return HttpClient.newClient(Env.URL_SERVER_DOMAIN.get(), Env.URL_SERVER_PORT.getInt()).createGet(Env.URL_SERVER_PATH.get());
    }

    @Override
    public void addHeaderToRequest(HttpClientRequest httpClientRequest, String key, String value) {
        httpClientRequest.addHeader(key, value);
    }

    @Override
    public HttpClientResponse execRequest(HttpClientRequest httpClientRequest, int request_number) throws Exception {
        return (HttpClientResponse) httpClientRequest.toBlocking().single();
    }

    @Override
    public int getResponseStatusCode(HttpClientResponse httpClientResponse) {
        return httpClientResponse.getStatus().code();
    }

//    public static void main(String[] args) {
//        IntStream.range(1,100000).forEach(i -> {
//            try {
//                HttpClientRequest<ByteBuf, ByteBuf> request = HttpClient.newClient("xxx.xxx.xxx.xxx", 8080).createGet("/hit_me");
//                request.addHeader("teste", i);
//                HttpClientResponse<ByteBuf> single = request.toBlocking().single();
//                single.getStatus().code();
//                single.unsafeConnection().close();
//                single.unsafeNettyChannel().close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        });
//
//    }

}
