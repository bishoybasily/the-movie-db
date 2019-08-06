package com.neugelb.themoviedb.external.okhttp;

import com.neugelb.themoviedb.external.dagger.ScopeMain;
import com.neugelb.themoviedb.helper.LogHelper;
import okhttp3.*;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

import javax.inject.Inject;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@ScopeMain
public final class LoggingInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String TAG = LoggingInterceptor.class.getSimpleName();

    private LogHelper logHelper;

    @Inject
    public LoggingInterceptor(LogHelper logHelper) {
        this.logHelper = logHelper;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        RequestBody requestBody = request.body();
        Boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
        logHelper.info(TAG, requestStartMessage);

        if (hasRequestBody) {
            // Request body headers are only present when installed as a network interceptor. Force
            // them to be included (when available) so there values are known.
            if (requestBody.contentType() != null) {
                logHelper.info(TAG, "Content-FaceType: " + requestBody.contentType());
            }
            if (requestBody.contentLength() != -1) {
                logHelper.info(TAG, "Content-Length: " + requestBody.contentLength());
            }
        }

        Headers requestHeaders = request.headers();
        for (int i = 0, count = requestHeaders.size(); i < count; i++) {
            String name = requestHeaders.name(i);
            // Skip headers from the request body as they are explicitly logged above.
            if (!"Content-FaceType".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                logHelper.info(TAG, name + ": " + requestHeaders.value(i));
            }
        }

        if (!hasRequestBody) {
            logHelper.info(TAG, "--> END " + request.method());
        } else if (bodyEncoded(request.headers())) {
            logHelper.info(TAG, "--> END " + request.method() + " (encoded body omitted)");
        } else {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }

            logHelper.info(TAG, "");
            if (isPlaintext(buffer)) {
                logHelper.info(TAG, buffer.readString(charset));
                logHelper.info(TAG, "--> END " + request.method() + " (" + requestBody.contentLength() + "-byte body)");
            } else {
                logHelper.info(TAG, "--> END " + request.method() + " (binary " + requestBody.contentLength() + "-byte body omitted)");
            }
        }

        Long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            logHelper.info(TAG, "<-- HTTP FAILED: " + e);
            throw e;
        }
        Long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        Long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        logHelper.info(TAG, "<-- " + response.code() + ' ' + response.message() + ' ' + response.request().url() + " (" + tookMs + "ms" + "" + ')');

        Headers responseHeaders = response.headers();
        for (int i = 0, count = responseHeaders.size(); i < count; i++) {
            logHelper.info(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        if (!HttpHeaders.hasBody(response)) {
            logHelper.info(TAG, "<-- END HTTP");
        } else if (bodyEncoded(response.headers())) {
            logHelper.info(TAG, "<-- END HTTP (encoded body omitted)");
        } else {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }

            if (!isPlaintext(buffer)) {
                logHelper.info(TAG, "");
                logHelper.info(TAG, "<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
                return response;
            }

            if (contentLength != 0) {
                logHelper.info(TAG, "");
                logHelper.info(TAG, buffer.clone().readString(charset));
            }

            logHelper.info(TAG, "<-- END HTTP (" + buffer.size() + "-byte body)");
        }

        return response;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}
