package com.amadeus.testelasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ElasticSearchConfiguration implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConfiguration.class);

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;

    private RestHighLevelClient restHighLevelClient;


    @Override
    public void destroy() throws Exception {
        try {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (final Exception e) {
            LOGGER.error("Error closing ElasticSearch client", e);
        }
    }

    /**
     * 控制Bean的实例化过程
     * @return restHighLevelClient
     * @throws Exception 抛出异常
     */
    @Override
    public RestHighLevelClient getObject() throws Exception {
        return restHighLevelClient;
    }

    /**
     * 获取接口返回的实例的class
     * @return RestHighLevelClient.class
     */
    @Override
    public Class<?> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        restHighLevelClient = buildClient();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    private RestHighLevelClient buildClient() {
        try {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(
                                    clusterNodes.split(":")[0],
                                    Integer.parseInt(clusterNodes.split(":")[1]),
                                    "http"
                            )
                    )
            );
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return restHighLevelClient;
    }
}
