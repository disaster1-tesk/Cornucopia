package com.disaster.springbootproject.config;

import com.codahale.metrics.*;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MetricConfig {

    @Bean
    public MetricRegistry metrics() {
        return new MetricRegistry();
    }

    /**
     * Reporter 数据的展现位置
     *
     * @param metrics
     * @return
     */
    @Bean
    public ConsoleReporter consoleReporter(MetricRegistry metrics) {
        return ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
    }

    @Bean
    public Slf4jReporter slf4jReporter(MetricRegistry metrics) {
        return Slf4jReporter.forRegistry(metrics)
                .outputTo(LoggerFactory.getLogger("demo.metrics"))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
    }

//    @Bean
//    public JmxReporter jmxReporter(MetricRegistry metrics) {
//        return JmxReporter.forRegistry(metrics).build();
//    }
//
//    /**
//     * 自定义单位
//     *
//     * @param metrics
//     * @return
//     */
//    @Bean
//    public ListManager listManager(MetricRegistry metrics) {
//        return new ListManager(metrics);
//    }

    /**
     * TPS 计算器
     *
     * @param metrics
     * @return
     */
    @Bean
    public Meter requestMeter(MetricRegistry metrics) {
        return metrics.meter("request");
    }

    /**
     * 直方图
     *
     * @param metrics
     * @return
     */
    @Bean
    public Histogram responseSizes(MetricRegistry metrics) {
        return metrics.histogram("response-sizes");
    }

    /**
     * 计数器
     *
     * @param metrics
     * @return
     */
    @Bean
    public Counter pendingJobs(MetricRegistry metrics) {
        return metrics.counter("requestCount");
    }

    /**
     * 计时器
     *
     * @param metrics
     * @return
     */
    @Bean
    public Timer responses(MetricRegistry metrics) {
        return metrics.timer("executeTime");
    }

}