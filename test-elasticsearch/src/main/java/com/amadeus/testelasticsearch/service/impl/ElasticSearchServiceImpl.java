package com.amadeus.testelasticsearch.service.impl;

import com.amadeus.testelasticsearch.entity.Employee;
import com.amadeus.testelasticsearch.service.ElasticSearchService;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


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

    @Override
    public void getDocument(String indexName, String id) {
        try {
            // 1、创建获取文档请求
            GetRequest request = new GetRequest(indexName, id);

            // 2、可选的设置
            //request.routing("routing");
            //request.version(2);

            //request.fetchSourceContext(new FetchSourceContext(false)); //是否获取_source字段
            //选择返回的字段
            String[] includes = new String[]{"message", "*Date"};
            String[] excludes = Strings.EMPTY_ARRAY;
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
            request.fetchSourceContext(fetchSourceContext);

            //也可写成这样
            /*String[] includes = Strings.EMPTY_ARRAY;
            String[] excludes = new String[]{"message"};
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
            request.fetchSourceContext(fetchSourceContext);*/


            // 取stored字段
            /*request.storedFields("message");
            GetResponse getResponse = client.get(request);
            String message = getResponse.getField("message").getValue();*/


            //3、发送请求
            GetResponse getResponse = null;
            try {
                // 同步请求
                getResponse = restHighLevelClient.get(request,RequestOptions.DEFAULT);
            } catch (ElasticsearchException e) {
                if (e.status() == RestStatus.NOT_FOUND) {
                    LOGGER.error("没有找到该id的文档");
                }
                if (e.status() == RestStatus.CONFLICT) {
                    LOGGER.error("获取时版本冲突了，请在此写冲突处理逻辑！");
                }
                LOGGER.error("获取文档异常", e);
            }

            //4、处理响应
            if (getResponse != null) {
                String index = getResponse.getIndex();
                String type = getResponse.getType();
                if (getResponse.isExists()) { // 文档存在
                    long version = getResponse.getVersion();
                    String sourceAsString = getResponse.getSourceAsString(); //结果取成 String
                    Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();  // 结果取成Map
                    byte[] sourceAsBytes = getResponse.getSourceAsBytes();    //结果取成字节数组

                    LOGGER.info("index:" + index + "  type:" + type + "  id:" + getResponse.getId());
                    LOGGER.info(sourceAsString);

                } else {
                    LOGGER.error("没有找到该id的文档");
                }
            }


            //异步方式发送获取文档请求
            /*
            ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
                @Override
                public void onResponse(GetResponse getResponse) {

                }

                @Override
                public void onFailure(Exception e) {

                }
            };
            client.getAsync(request, listener);
            */

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
