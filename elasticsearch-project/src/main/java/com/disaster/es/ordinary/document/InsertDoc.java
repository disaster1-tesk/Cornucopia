package com.disaster.es.ordinary.document;

import com.alibaba.fastjson2.JSON;
import com.disaster.es.ConnectElasticsearch;
import com.disaster.es.pojo.User;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;

public class InsertDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            IndexRequest request = new IndexRequest();
            request.index("disaster").id("1002");
            User user = User.builder()
                    .age(20)
                    .height(177)
                    .weight(140)
                    .name("disaster")
                    .type("Engineer")
                    .build();
            String content = JSON.toJSONString(user);
            // 添加文档数据，数据格式为 JSON 格式
            request.source(content, XContentType.JSON);
            // 客户端发送请求，获取响应对象
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            //打印结果信息
            System.out.println("_index:" + response.getIndex());
            System.out.println("_id:" + response.getId());
            System.out.println("_result:" + response.getResult());
        });
    }
}
