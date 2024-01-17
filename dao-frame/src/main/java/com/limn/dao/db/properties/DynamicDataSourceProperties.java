package com.limn.dao.db.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "dynamic")
@Data
public class DynamicDataSourceProperties {

    private Map<String, DynamicDataSourceConfig> datasource = new LinkedHashMap<>();

}
