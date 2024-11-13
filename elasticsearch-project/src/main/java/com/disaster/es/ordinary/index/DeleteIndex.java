package com.disaster.es.ordinary.index;

import com.disaster.es.ESClientUtil;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class DeleteIndex {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = ESClientUtil.getInstance();

        DeleteIndexRequest request = new DeleteIndexRequest("disaster");

        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);

        // 操作结果
        System.out.println("操作结果 ： " + response.isAcknowledged());
        client.close();
    }
}
