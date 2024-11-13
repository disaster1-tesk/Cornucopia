package com.disaster.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.Objects;

/**
 * The type Es client util.
 *
 * @author disaster
 * @version 1.0
 */
public class ESClientUtil {
    public static RestHighLevelClient restHighLevelClient = null;

    static {
        if (Objects.isNull(restHighLevelClient)) {
            synchronized (ESClientUtil.class) {
                if (Objects.isNull(restHighLevelClient)) {
                    restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(Constant.host, Constant.port, "http")));
                }
            }
        }
    }

    public static RestHighLevelClient getInstance() {
        return restHighLevelClient;
    }

}
