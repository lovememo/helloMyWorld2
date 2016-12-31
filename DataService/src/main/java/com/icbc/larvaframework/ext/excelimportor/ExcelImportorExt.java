/* Copyright (c) 2009 Shanghai Research and Development Dept, Software Development Centre,
 *                    Idustrial And Commercial Bank Of China. All rights reserved.
 */
//$Log: ExcelImportorExt.java,v $
//Revision 1.16  2011/08/04 02:28:53  wengxj
//LARVA-503
//
//Revision 1.15  2010/08/27 05:53:27  huangxl
//Excel导入处理CSV前导0问题
//LARVA-412
//
//Revision 1.14  2010/08/11 05:31:58  huangxl
//LARVA-402
//Excel导入支持客户端解析方式，支持MHT等导出格式的再倒入
//
//Revision 1.13  2010/04/22 01:09:14  liukan
//LARVA-351
//
//Revision 1.12  2010/02/01 07:22:15  huangxl
//LARVA-310 导入组件提供不需要校验抬头的接口
//
//Revision 1.11  2009/12/01 05:32:25  huangxl
//excel导入组件，导入没有数据的空excel文件时，会数组越界报错误，应该明确错误原因
//LARVA-297
//
//Revision 1.10  2009/09/29 05:12:16  huangxl
//Excel导入功能对于导入文件中带有自定义格式无法解析的情况，报错明确化
//LARVA-286
//
//Revision 1.9  2009/08/24 02:34:11  huangxl
//增加能够自动去除excel文件末空行空列的接口
//LARVA-262
//
//Revision 1.8  2009/07/03 07:41:00  huangxl
//使用POI后Double.toString()时可能出现科学计数法表示，不应出现
//LARVA-231
//
//Revision 1.7  2009/05/12 03:38:31  huangxl
//Excel导入效率优化
//LARVA-179
//
//Revision 1.6  2009/04/13 09:25:23  huangxl
//excel导入组件拆分、License加入
//LARVA-162
//
//Revision 1.5  2009/04/08 01:29:48  huangxl
//Larva异常类处理 excel导入模块部分
//LARVA-153
//
package com.icbc.larvaframework.ext.excelimportor;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.DimensionsRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.RKRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;



/**
 * @title: excel导入工具类
 * @desc: excel导入工具类，用以解析ac中的excel文件提交字段为二维数组形式；以及将excel导入到临时表
 * @module: excel导入模块
 * @author: kfzx-huangxl
 * @date: 2008-9-26
 * @since: 1.0.0.0
 */
public class ExcelImportorExt {
    private static int MAX_ERROR_TIMES = 200;

    /**
     * @title: POI Event模式 excel读取处理
     * @desc: 一种较低内存开销的excel处理方式，利用apache POI方式
     * @module: excel导入模块
     * @author: kfzx-huangxl
     * @date: 2009-5-12
     * @since 1.1.0.0
     */

    public static class EventListener implements HSSFListener {

        private SSTRecord sharedStringTable;

        /**
         * excel导入数据的字符串数组表达（注意可能会有空行空列）
         */
        public String[][] data;
        /**
         * excel导入数据的字符串数组表达（以按第一行列数处理，并去除了空行空列）
         */
        public ArrayList  result;
        /**
         * 导入解析步骤 -1:刚开始解析 0:解析第一个sheet中 1:解析第一个sheet已经完成，之后的sheet被忽略
         */
        public int step     = -1;
        /**
         * 第一sheet第一行的宽度
         */
        public int colCount = 0;
        /**
         * 第一sheet非空行的个数
         */
        public int rowCount = 0;

        /**
         * 值单元格处理，将其放入对应行列的字符串数组中
         *
         * @param valueRecord 值单元格
         * @param value       值单元格的值
         * @since 1.1.0.0
         */
        protected void valueRecordDealer(CellValueRecordInterface valueRecord, String value) {
            int row = valueRecord.getRow();
            int col = valueRecord.getColumn();
            if (col > this.colCount - 1)
                this.colCount = col + 1;
            if (row > this.rowCount - 1)
                this.rowCount = row + 1;
//			System.out.println("row=" + row + " col=" + col + ", rowCount=" + rowCount + " colCount=" + colCount + "; value="
//					+ value);
            this.data[row][col] = value;
        }

        private String double2PlainString(double num) {
            DecimalFormat df = new DecimalFormat();
            return df.format(num);
        }

        /*
         * （非 Javadoc）
         *
         * @see org.apache.poi.hssf.eventusermodel.HSSFListener#processRecord(org.apache.poi.hssf.record.Record)
         */
        public void processRecord(Record record) {
            if (step >= 1)
                return;
            switch (record.getSid()) {
                case BOFRecord.sid:
                    BOFRecord bof = (BOFRecord) record;
                    System.out.println("BOF");
                    if (bof.getType() == BOFRecord.TYPE_WORKSHEET) {
                        step = 0;
                        System.out.println("BOF Sheet");
                    }
                    break;
                case EOFRecord.sid:
                    if (step == 0) {
                        step = 1;
                        result = new ArrayList(rowCount);
                        for (int i = 0; i < rowCount; i++) {
                            String[] rowData = data[i];
                            ArrayList oneRow = new ArrayList(colCount);
                            for (int j = 0; j < colCount; j++)
                                oneRow.add(rowData[j] == null ? "" : rowData[j]);
                            result.add(oneRow);
                            rowData = null;
                        }
                        data = null;
                    }
                    break;
                case DimensionsRecord.sid:
                    DimensionsRecord dr = (DimensionsRecord) record;
                    data = new String[dr.getLastRow() + 1][dr.getLastCol() + 1];
                    System.out.println("sheet row: " + dr.getFirstRow() + " - " + dr.getLastRow() + " , col: "
                            + dr.getFirstCol() + " - " + dr.getLastCol());
                    break;
                case NumberRecord.sid:
                    NumberRecord numrec = (NumberRecord) record;
                    valueRecordDealer(numrec, double2PlainString(numrec.getValue()));
                    break;
                case SSTRecord.sid:
                    this.sharedStringTable = (SSTRecord) record;
                    break;
                case LabelSSTRecord.sid:
                    LabelSSTRecord lsrec = (LabelSSTRecord) record;
                    valueRecordDealer(lsrec, this.sharedStringTable.getString(lsrec.getSSTIndex()).getString());
                    break;
                case FormulaRecord.sid:
                    FormulaRecord frec = (FormulaRecord) record;
                    valueRecordDealer(frec, double2PlainString(frec.getValue()));
                    break;
                case BlankRecord.sid:
                    BlankRecord brec = (BlankRecord) record;
                    valueRecordDealer(brec, "");
                    break;
                case BoolErrRecord.sid:
                    BoolErrRecord berec = (BoolErrRecord) record;
                    valueRecordDealer(berec, Boolean.toString(berec.getBooleanValue()));
                    break;
                case LabelRecord.sid:
                    LabelRecord lrec = (LabelRecord) record;
                    valueRecordDealer(lrec, lrec.getValue());
                    break;
                case RKRecord.sid:
                    RKRecord rkrec = (RKRecord) record;
                    valueRecordDealer(rkrec, double2PlainString(rkrec.getRKNumber()));
                    break;
            }
        }
    }
}