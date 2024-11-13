package com.disaster.es;

import org.elasticsearch.client.RestHighLevelClient;

public interface ConnectElasticsearch {

     static void connect(ElasticsearchTask task){
        // 创建客户端对象
        RestHighLevelClient client = ESClientUtil.getInstance();
        try {
            task.doSomething(client);
            // 关闭客户端连接
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
