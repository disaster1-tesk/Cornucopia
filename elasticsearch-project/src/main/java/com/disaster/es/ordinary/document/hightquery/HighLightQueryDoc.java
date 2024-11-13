package com.disaster.es.ordinary.document.hightquery;

import com.disaster.es.ConnectElasticsearch;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.util.Map;

public class HighLightQueryDoc {
    public static void main(String[] args) {
        ConnectElasticsearch.connect(client -> {
            // 创建搜索请求对象
            SearchRequest request = new SearchRequest().indices("disaster");

            // 构建查询的请求体
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            //模糊查询
            sourceBuilder.postFilter(QueryBuilders.fuzzyQuery("name", "disaster").fuzziness(Fuzziness.ZERO));
            sourceBuilder.query(QueryBuilders.termQuery("name", "disaster"));

            //构建高亮字段
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.preTags("<font color='red'>");//设置标签前缀
            highlightBuilder.postTags("</font>");//设置标签后缀
            highlightBuilder.field("name");//设置高亮字段

            //设置高亮构建对象
            sourceBuilder.highlighter(highlightBuilder);
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
                String sourceAsString = hit.getSourceAsString();
                System.out.println(sourceAsString);
                //打印高亮结果
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                System.out.println(highlightFields);
            }
            System.out.println("<<::::");
        });
    }
}
