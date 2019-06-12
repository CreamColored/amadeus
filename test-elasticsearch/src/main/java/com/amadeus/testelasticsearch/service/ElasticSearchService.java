package com.amadeus.testelasticsearch.service;

import org.elasticsearch.action.index.IndexResponse;

public interface ElasticSearchService {

    void createIndex(String indexName);

    void indexDocument(String indexName);

}
