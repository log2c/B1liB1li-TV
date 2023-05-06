package com.github.log2c.b1lib1li_tv.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Inflater;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
import okio.GzipSource;
import okio.Okio;

public class UnzippingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return unzip(response);
    }


    // copied from okhttp3.internal.http.HttpEngine (because is private)
    private Response unzip(final Response response) throws IOException {
        if (response.body() == null) {
            return response;
        }

        //check if we have gzip response
        String contentEncoding = response.headers().get("Content-Encoding");

        //this is used to decompress gzipped responses
        if (contentEncoding != null && contentEncoding.equals("gzip")) {
            long contentLength = response.body().contentLength();
            GzipSource responseBody = new GzipSource(response.body().source());
            Headers strippedHeaders = response.headers().newBuilder().build();
            return response.newBuilder().headers(strippedHeaders)
                    .body(new RealResponseBody(response.body().contentType().toString(), contentLength, Okio.buffer(responseBody)))
                    .build();
        } else if (contentEncoding != null && contentEncoding.equals("deflate")) {
            final byte[] decompressedBytes = decompress(response.body().bytes());
            long contentLength = response.body().contentLength();
            Headers strippedHeaders = response.headers().newBuilder().build();

            MediaType contentType = response.body().contentType();
//            ResponseBody body = ResponseBody.create(contentType, decompressedBytes);
            ResponseBody body = ResponseBody.Companion.create(decompressedBytes, contentType);

            return response.newBuilder().headers(strippedHeaders)
                    .body(body)
                    .build();
        } else {
            return response;
        }
    }


    //    解压的方法体
    public static byte[] decompress(byte[] data) throws IOException {
        byte[] decompressData = null;
        Inflater decompressor = new Inflater(true);
        decompressor.reset();
        decompressor.setInput(data);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
            byte[] buf = new byte[1024];
            while (!decompressor.finished()) {
                int i = decompressor.inflate(buf);
                outputStream.write(buf, 0, i);
            }
            decompressData = outputStream.toByteArray();
        } catch (Exception ignored) {
        }
        decompressor.end();
        return decompressData;
    }
}