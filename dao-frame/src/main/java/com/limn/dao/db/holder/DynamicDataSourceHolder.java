package com.limn.dao.db.holder;

import com.limn.dao.db.constant.DynamicDataSourceConstant;
import org.apache.commons.lang3.StringUtils;

public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public DynamicDataSourceHolder() {
    }

    public static String getDbName(){
        String dbName = contextHolder.get();
        return StringUtils.isBlank(dbName)? DynamicDataSourceConstant.PRIMARY:dbName;
    }

    public static void setDbName(String dbName){
        contextHolder.set(dbName);
    }

    public static void removeDbName(){
        contextHolder.remove();
    }

}
