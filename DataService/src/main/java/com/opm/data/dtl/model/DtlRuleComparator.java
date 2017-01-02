package com.opm.data.dtl.model;

import java.util.Comparator;

/**
 * 按字段顺序对规则进行排序
 * Created by Lovememo on 2017/1/2 0002.
 */
public class DtlRuleComparator implements Comparator<DtlRule> {
    //return值小于0表示rule1小
    @Override
    public int compare(DtlRule rule1, DtlRule rule2) {
        int seq1 = rule1.getFieldSeq();
        int seq2 = rule2.getFieldSeq();
        if (seq1 > seq2) {
            return 1;
        } else if (seq1 < seq2) {
            return -1;
        } else {
            return 0;
        }
    }
}