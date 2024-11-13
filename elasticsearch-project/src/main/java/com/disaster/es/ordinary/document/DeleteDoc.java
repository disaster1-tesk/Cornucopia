package com.disaster.es.ordinary.document;

import com.disaster.es.ConnectElasticsearch;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;

public class DeleteDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            DeleteRequest request = new DeleteRequest();
            request.index("disaster").id("1002");

            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            System.out.println("_index:" + response.getIndex());
            System.out.println("_type:" + response.getType());
            System.out.println("_id:" + response.getId());
            System.out.println("shards:"+response.getShardInfo());
        });
    }
}
