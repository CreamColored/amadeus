package com.amadeus.testelasticsearch.service.impl;

import com.amadeus.testelasticsearch.entity.Employee;
import com.amadeus.testelasticsearch.service.ElasticSearchService;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("elasticSearchService")
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private static Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void createIndex(String indexName) {
        //创建索引
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        //设置索引的settings
        request.settings(Settings.builder().
                put("index.number_of_shards", 3).
                put("index.number_of_replicas", 0)
        );
        //设置别名
        request.alias(new Alias("aaa"));
        /*boolean acknowledged = false;
        boolean shardsAcknowledged = false;
        try {
            //同步方式发送请求
            CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            //处理响应
            acknowledged = response.isAcknowledged();
            shardsAcknowledged = response.isShardsAcknowledged();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return acknowledged && shardsAcknowledged;*/
        //异步方式发送请求
        ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {
                //处理响应
                boolean acknowledged = createIndexResponse.isAcknowledged();
                boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
                System.out.println(acknowledged && shardsAcknowledged);
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("创建索引异常:" + e.getMessage());
            }
        };
        restHighLevelClient.indices().createAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Override
    public void indexDocument(String indexName) {
        //创建索引请求
        IndexRequest request = new IndexRequest(indexName);
        //准备文档数据
        //方式一:直接给json串
        Gson gson = new Gson();
        Employee employee = new Employee();
        employee.setId("123");
        employee.setFirstName("王");
        employee.setLastName("鑫鹏");
        employee.setAge(22);
        employee.setAbout("我叫王鑫鹏");
        String jsonString = gson.toJson(employee);
        request.source(jsonString, XContentType.JSON);
        /*//方式二:以map对象来表示文档
        HashMap<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("firstName", "王");
        jsonMap.put("lastName", "鑫鹏");
        request.source(jsonMap);*/
        //发送请求
        /*IndexResponse indexResponse = null;
        try {
            //同步方式
            indexResponse = restHighLevelClient.index(request,RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            //捕获并处理异常
            //判断是否版本冲突、create但文档已存在冲突
            if (e.status() == RestStatus.CONFLICT) {
                LOGGER.error("冲突了!"+e.getMessage());
            }
            LOGGER.error("索引异常"+e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexResponse;*/
        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        };
        restHighLevelClient.indexAsync(request, RequestOptions.DEFAULT, listener);
    }
}
