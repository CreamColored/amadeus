package com.amadeus.testelasticsearch.controller;

import com.amadeus.testelasticsearch.service.ElasticSearchService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/es")
public class ElasticSearchController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ElasticSearchService elasticSearchService;


    /**
     * 创建索引
     *
     * @return boolean
     */
    @PutMapping("/createIndex/{indexName}")
    public void createIndex(@PathVariable("indexName") String indexName) {
        elasticSearchService.createIndex(indexName);
    }

    @PutMapping("/indexDocument/{indexName}")
    public void indexDocument(@PathVariable("indexName")String indexName) {
        elasticSearchService.indexDocument(indexName);
    }

    @GetMapping("/search")
    public SearchResponse search() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("world");
        searchRequest.source(searchSourceBuilder);
        System.out.println(searchRequest.source().toString());
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
