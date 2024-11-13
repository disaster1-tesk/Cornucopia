package com.disaster.es.ordinary.document.hightquery;

import com.disaster.es.ConnectElasticsearch;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Arrays;

public class GroupQueryDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            SearchRequest request = new SearchRequest().indices("disaster");

            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            sourceBuilder.aggregation(AggregationBuilders.terms("age_groupby").field("age"));

            //设置请求体
            request.source(sourceBuilder);
            //3.客户端发送请求，获取响应对象
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            //4.打印响应结果
            SearchHits hits = response.getHits();
            Arrays.stream(hits.getHits()).forEach(hit ->{
                System.out.println(hit.getSourceAsString());
            });
        });

    }
}
