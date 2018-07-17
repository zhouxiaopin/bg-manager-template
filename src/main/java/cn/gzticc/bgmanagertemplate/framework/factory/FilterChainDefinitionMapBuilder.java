package cn.gzticc.bgmanagertemplate.framework.factory;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/11/28.
 */
public class FilterChainDefinitionMapBuilder {
    public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("/static/**", "anon");
        map.put("/admin/login", "anon");
//        map.put("/shiro/login", "anon");
        map.put("/admin/logout", "logout");
//        map.put("/busInfo.action", "authc,roles[user]");
//        map.put("/admin.jsp", "authc,roles[admin]");
//        map.put("/list.jsp", "user");

        map.put("/**", "authc");
        // FIXME: 2018/6/11 以后去掉下面的  
        map.put("/**", "anon");

        return map;
    }
}
