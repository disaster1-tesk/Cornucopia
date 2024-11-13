package com.disaster.es.ordinary.document;

import com.disaster.es.ConnectElasticsearch;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;

public class UpdateDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            UpdateRequest request = new UpdateRequest();
            request.index("disaster").id("1002");

            request.doc(XContentType.JSON,"height","177","age",30);

            UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
            System.out.println("_index:" + response.getIndex());
            System.out.println("_id:" + response.getId());
            System.out.println("_result:" + response.getResult());
        });
    }
}
