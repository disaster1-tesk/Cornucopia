package com.disaster.es.ordinary.document.hightquery;

import com.disaster.es.ConnectElasticsearch;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class CombinedQueryDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            SearchRequest request = new SearchRequest().indices("disaster");

            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.from(1);
            sourceBuilder.size(4);

            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.matchQuery("age","20"));
            boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name","disaster"));
            boolQueryBuilder.should(QueryBuilders.matchQuery("type","Engineer"));

            sourceBuilder.query(boolQueryBuilder);
            request.source(sourceBuilder);

            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            // 查询匹配
            SearchHits hits = response.getHits();
            System.out.println("took:" + response.getTook());
            System.out.println("timeout:" + response.isTimedOut());
            System.out.println("total:" + hits.getTotalHits());
            System.out.println("MaxScore:" + hits.getMaxScore());
            System.out.println("hits========>>");
            for (SearchHit hit : hits) {
                //输出每条查询的结果信息
                System.out.println(hit.getSourceAsString());
            }
            System.out.println("<<========");
        });
    }
}
