package com.disaster.es.ordinary.index;

import com.disaster.es.ESClientUtil;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

/**
 * The type Search index.
 *
 * @author disaster
 * @version 1.0
 */
public class SearchIndex {
    public static void main(String[] args) throws Throwable{
        // 创建客户端对象
        RestHighLevelClient client = ESClientUtil.getInstance();

        // 查询索引 - 请求对象
        GetIndexRequest request = new GetIndexRequest("disaster");
        // 发送请求，获取响应
        GetIndexResponse response = client.indices().get(request,
                RequestOptions.DEFAULT);

        System.out.println("aliases:"+response.getAliases());
        System.out.println("mappings:"+response.getMappings());
        System.out.println("settings:"+response.getSettings());

        client.close();
    }
}
