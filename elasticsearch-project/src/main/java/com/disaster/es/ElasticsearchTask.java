package com.disaster.es;

import org.elasticsearch.client.RestHighLevelClient;

public interface ElasticsearchTask {
    void doSomething(RestHighLevelClient client) throws Exception;
}
