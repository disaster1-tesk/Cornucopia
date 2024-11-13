package com.disaster.es.ordinary.document.bulk;

import com.disaster.es.ConnectElasticsearch;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;

public class BatchDeleteDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            BulkRequest request = new BulkRequest();
            request.add(new DeleteRequest().index("disaster").id("1001"));
            request.add(new DeleteRequest().index("disaster").id("1002"));
            request.add(new DeleteRequest().index("disaster").id("1003"));

            BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
            //打印结果信息
            System.out.println("took:" + responses.getTook());
            System.out.println("items:" + responses.getItems());
        });
    }
}
