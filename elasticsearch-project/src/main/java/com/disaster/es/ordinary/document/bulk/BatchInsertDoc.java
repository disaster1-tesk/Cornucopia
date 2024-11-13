package com.disaster.es.ordinary.document.bulk;

import com.alibaba.fastjson2.JSON;
import com.disaster.es.ConnectElasticsearch;
import com.disaster.es.pojo.User;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;

public class BatchInsertDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            //创建批量新增请求对象
            BulkRequest request = new BulkRequest();
            request.add(new
                    IndexRequest().index("disaster").id("1001").source(JSON.toJSONString(User.builder()
                    .id(1001l)
                    .age(20)
                    .height(177)
                    .weight(140)
                    .name("disaster")
                    .type("Engineer")
                    .build()),XContentType.JSON));
            request.add(new
                    IndexRequest().index("disaster").id("1002").source(JSON.toJSONString(User.builder()
                    .id(1002l)
                    .age(20)
                    .height(178)
                    .weight(140)
                    .name("disaster1")
                    .type("Engineer")
                    .build()),XContentType.JSON));
            request.add(new
                    IndexRequest().index("disaster").id("1003").source(JSON.toJSONString(User.builder()
                    .id(1003l)
                    .age(20)
                    .height(179)
                    .weight(140)
                    .name("disaster2")
                    .type("Engineer")
                    .build()),XContentType.JSON));
            //客户端发送请求，获取响应对象
            BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
            //打印结果信息
            System.out.println("took:" + responses.getTook());
            System.out.println("items:" + responses.getItems());
        });
    }
}
