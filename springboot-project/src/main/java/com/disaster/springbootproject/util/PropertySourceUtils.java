package com.disaster.springbootproject.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * The type Property source utils.
 *
 * @author wangwei
 * @since 1.0
 */
public class PropertySourceUtils {


    /**
     * Gets resource property source.
     *
     * @param location the location
     * @return the resource property source
     */
    public static ResourcePropertySource getResourcePropertySource(String location) {
        // 创建 ResourceLoader 实例
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        // 通过 location 获取 Resource
        Resource resource = resourceLoader.getResource(location);
        // EncodedResource 需指定字符编码，避免中文乱码
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        // 将 EncodedResource 转化为 ResourcePropertySource 对象
        ResourcePropertySource propertySource = null;
        try {
            propertySource = new ResourcePropertySource(encodedResource);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return propertySource;
    }

    /**
     * Resolve uri string [ ].
     *
     * @param uri the uri
     * @return the string [ ]
     */
    public static String[] resolveUri(String uri) {
        return StringUtils.isEmpty(uri) ? null : uri.split(",");
    }

}
