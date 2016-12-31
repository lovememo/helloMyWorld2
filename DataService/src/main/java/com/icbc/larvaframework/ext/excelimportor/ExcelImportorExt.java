/* Copyright (c) 2009 Shanghai Research and Development Dept, Software Development Centre,
 *                    Idustrial And Commercial Bank Of China. All rights reserved.
 */
//$Log: ExcelImportorExt.java,v $
//Revision 1.16  2011/08/04 02:28:53  wengxj
//LARVA-503
//
//Revision 1.15  2010/08/27 05:53:27  huangxl
//Excel���봦��CSVǰ��0����
//LARVA-412
//
//Revision 1.14  2010/08/11 05:31:58  huangxl
//LARVA-402
//Excel����֧�ֿͻ��˽�����ʽ��֧��MHT�ȵ�����ʽ���ٵ���
//
//Revision 1.13  2010/04/22 01:09:14  liukan
//LARVA-351
//
//Revision 1.12  2010/02/01 07:22:15  huangxl
//LARVA-310 ��������ṩ����ҪУ��̧ͷ�Ľӿ�
//
//Revision 1.11  2009/12/01 05:32:25  huangxl
//excel�������������û�����ݵĿ�excel�ļ�ʱ��������Խ�籨����Ӧ����ȷ����ԭ��
//LARVA-297
//
//Revision 1.10  2009/09/29 05:12:16  huangxl
//Excel���빦�ܶ��ڵ����ļ��д����Զ����ʽ�޷������������������ȷ��
//LARVA-286
//
//Revision 1.9  2009/08/24 02:34:11  huangxl
//�����ܹ��Զ�ȥ��excel�ļ�ĩ���п��еĽӿ�
//LARVA-262
//
//Revision 1.8  2009/07/03 07:41:00  huangxl
//ʹ��POI��Double.toString()ʱ���ܳ��ֿ�ѧ��������ʾ����Ӧ����
//LARVA-231
//
//Revision 1.7  2009/05/12 03:38:31  huangxl
//Excel����Ч���Ż�
//LARVA-179
//
//Revision 1.6  2009/04/13 09:25:23  huangxl
//excel���������֡�License����
//LARVA-162
//
//Revision 1.5  2009/04/08 01:29:48  huangxl
//Larva�쳣�ദ�� excel����ģ�鲿��
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
 * @title: excel���빤����
 * @desc: excel���빤���࣬���Խ���ac�е�excel�ļ��ύ�ֶ�Ϊ��ά������ʽ���Լ���excel���뵽��ʱ��
 * @module: excel����ģ��
 * @author: kfzx-huangxl
 * @date: 2008-9-26
 * @since: 1.0.0.0
 */
public class ExcelImportorExt {
    private static int MAX_ERROR_TIMES = 200;

    /**
     * @title: POI Eventģʽ excel��ȡ����
     * @desc: һ�ֽϵ��ڴ濪����excel����ʽ������apache POI��ʽ
     * @module: excel����ģ��
     * @author: kfzx-huangxl
     * @date: 2009-5-12
     * @since 1.1.0.0
     */

    public static class EventListener implements HSSFListener {

        private SSTRecord sharedStringTable;

        /**
         * excel�������ݵ��ַ��������ע����ܻ��п��п��У�
         */
        public String[][] data;
        /**
         * excel�������ݵ��ַ���������԰���һ������������ȥ���˿��п��У�
         */
        public ArrayList  result;
        /**
         * ����������� -1:�տ�ʼ���� 0:������һ��sheet�� 1:������һ��sheet�Ѿ���ɣ�֮���sheet������
         */
        public int step     = -1;
        /**
         * ��һsheet��һ�еĿ��
         */
        public int colCount = 0;
        /**
         * ��һsheet�ǿ��еĸ���
         */
        public int rowCount = 0;

        /**
         * ֵ��Ԫ������������Ӧ���е��ַ���������
         *
         * @param valueRecord ֵ��Ԫ��
         * @param value       ֵ��Ԫ���ֵ
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
         * ���� Javadoc��
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