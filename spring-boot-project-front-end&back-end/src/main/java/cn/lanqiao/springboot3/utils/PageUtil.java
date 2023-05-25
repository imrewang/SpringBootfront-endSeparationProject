package cn.lanqiao.springboot3.utils;

import java.util.LinkedHashMap;
import java.util.Map;

//Map接口的哈希表和链表实现，具有可预测的迭代顺序。
public class PageUtil extends LinkedHashMap<String, Object> {
    //当前页码
    private int page;
    //每页条数
    private int limit;

    public PageUtil(Map<String, Object> params) {
        this.putAll(params);//将指定映射中的所有映射复制到此映射。 这些映射将替换此映射对当前位于指定映射中的任何键的任何映射。
        //public class LinkedHashMap<K,V> // 已存在会被替换
        //    extends HashMap<K,V>
        //    implements Map<K,V>
        //所以PageUtil也有putAll

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());//将字符串参数解析为带符号的十进制整数。
        this.limit = Integer.parseInt(params.get("limit").toString());

        this.put("start", (page - 1) * limit);//将指定的值与此映射中的指定键相关联。
        this.put("page", page);// 已存在会被替换
        this.put("limit", limit);
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


    @Override
    public String toString() {
        return "PageUtil{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
