package com.disaster.es.ordinary.document.bulk;

import com.disaster.es.ConnectElasticsearch;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;

public class BatchUpdateDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            BulkRequest request = new BulkRequest();

            request.add(new UpdateRequest().index("disaster").id("1001").doc("age","25"));
            request.add(new UpdateRequest().index("disaster").id("1002").doc("age","26"));

            BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

            //打印结果信息
            System.out.println("took:" + response.getTook());
            System.out.println("items:" + response.getItems());
        });
    }
}
