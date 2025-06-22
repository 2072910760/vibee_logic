package com.neuedu.xinxin.vo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页查询实体类
 *
 * @param <T>
 */
@Slf4j
@Data
public class PageData<T> implements IPage<T> {
    /**
     * 记录集
     */
    private static final int MOD = 2;
    private List<T> records;
    /***当前页号:默认为1*/
    private long current = 1;
    /**
     * 总记录条数
     **/
    private long total;
    /**
     * 每页显示的条数 默认为10
     */
    private long size = 10;
    /**
     * 查询条件Map集合
     * key:字段名称
     * value:字段值
     */
    private Map<String, String> criteriaMap;
    /**
     * 每个板块显示的分页页码数量
     */
    private long displayCount = 5;
    /**
     * 总页数
     */
    private long totalPage = 1;
    /**
     * 排序Map集合
     * key:  规则为order_by_字段名(规则可以任意定义)
     * value:排序方式 true:升序;false:降序(规则可以任意定义)
     */
    private Map<String, Boolean> orderMap;
    /**
     * 排序规则
     */
    private List<OrderRule> orderRuleList;
    /**
     * 页面可以点击页号列表
     */
    private List<Long> displayList;

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    public IPage<T> setTotal(long total) {
        this.total = total > 0 ? total : 0;
        this.totalPage = (this.total / this.size)
                + (0 == this.total % this.size ? 0 : 1);
        if (0 == this.totalPage) {
            this.totalPage = 1;
        }
        return this;
    }

    public void setOrderRuleList(String[] orderRules) {
        if (orderRules == null) {
            return;
        }
        this.orderRuleList = new ArrayList<>();
        for (String orderRuleJSON : orderRules) {
            OrderRule orderRule=JSONObject.parseObject(orderRuleJSON,OrderRule.class);
            this.orderRuleList.add(orderRule);
        }
    }

    public void setOrderRuleList(List<OrderRule> orderRuleList) {
        this.orderRuleList = orderRuleList;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.size = size > 0 ? size : 0;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current > this.totalPage ? this.totalPage : current;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        this.current = current > 0 ? current : 0;
        return this;
    }

    /**
     * 获取排序SQL片段
     * 格式:字段1 DESC,字段2,字段3...
     * @return
     */
    public String getOrderBySQL() {
        if (CollectionUtil.isEmpty(orderRuleList)) {
            return null;
        }
        StringBuffer sqlBuf = new StringBuffer();
        int size = orderRuleList.size();
        int maxIndex = size - 1;
        for (int index = 0; index < maxIndex; index++) {
            OrderRule rule = orderRuleList.get(index);
            sqlBuf.append(rule.getColumnName());
            sqlBuf.append(' ');
            if (rule.isDESC()) {
                sqlBuf.append("DESC");
            }
            sqlBuf.append(',');
        }
        OrderRule rule = orderRuleList.get(maxIndex);
        sqlBuf.append(rule.getColumnName());
        sqlBuf.append(' ');
        if (rule.isDESC()) {
            sqlBuf.append("DESC");
        }
        return sqlBuf.toString();
    }

    /**
     * 分页页码列表
     *
     * @return List
     */
    public List<Long> getPageNumList() {
        long n = (int) displayCount / MOD;
        long m = this.getCurrent();
        displayList = new ArrayList<>();
        if (totalPage > displayCount) {
            if (m <= n) {
                for (int index = 1; index <= displayCount; index++) {
                    displayList.add(Long.valueOf(index));
                }

            } else {
                long start = m - n + 1;
                long end;
                if (start + displayCount > totalPage) {
                    start = totalPage - displayCount + 1;
                    end = totalPage;
                } else {
                    start = m - n + 1;
                    end = m - n + displayCount;
                }
                for (long index = start; index <= end; index++) {
                    displayList.add(Long.valueOf(index));
                }
            }

        } else {
            for (int index = 1; index <= totalPage; index++) {
                displayList.add(Long.valueOf(index));
            }
        }
        return displayList;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public Map<String, String> getCriteriaMap() {
        return criteriaMap;
    }

    /**
     * 添加条件
     *
     * @param columnName 字段
     * @param value      值
     */
    public void addCriteria(String columnName, String value) {
        if (this.criteriaMap == null) {
            criteriaMap = new HashMap<>();
        }
        if (StrUtil.isEmpty(columnName)) {
            throw new IllegalArgumentException("查询字段名称不能为空");
        }
        criteriaMap.put(columnName, value);
    }

    @Override
    public List<OrderItem> orders() {
        return null;
    }

    /**
     * 排序规则
     */
    @Data
    public static class OrderRule {
        /**
         * 字段名称
         */
        private String columnName;

        private boolean isDESC;

    }

}
