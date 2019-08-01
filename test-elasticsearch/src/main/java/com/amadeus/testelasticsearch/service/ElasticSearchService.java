package com.amadeus.testelasticsearch.service;

public interface ElasticSearchService {

    void createIndex(String indexName);

    void indexDocument(String indexName);

    void getDocument(String indexName, String id);

}
