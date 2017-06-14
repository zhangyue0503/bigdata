package com.hbaseinaction;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class Main {

    static Configuration conf = null;
    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zy.test", "localhost");
    }

    public static void main(String[] args)  {
	// write your code here
        HTable table = null;
        try {
            table = new HTable(conf, Bytes.toBytes("users1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ////////////////////
        //添加
        Put p = new Put(Bytes.toBytes("info"));
        Cell c = new KeyValue(Bytes.toBytes("info"),Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes("Mark1"));
        Cell c1 = new KeyValue(Bytes.toBytes("info"),Bytes.toBytes("info"),Bytes.toBytes("email"),Bytes.toBytes("aaa1@aa.com"));
        Cell c2 = new KeyValue(Bytes.toBytes("info"),Bytes.toBytes("info"),Bytes.toBytes("password"),Bytes.toBytes("12345611"));

        Put p2 = new Put(Bytes.toBytes("info1"));
        Cell c20 = new KeyValue(Bytes.toBytes("info1"),Bytes.toBytes("info"),Bytes.toBytes("name"),Bytes.toBytes("Mark1"));
        Cell c21 = new KeyValue(Bytes.toBytes("info1"),Bytes.toBytes("info"),Bytes.toBytes("email"),Bytes.toBytes("aaa1@aa.com"));
        Cell c22 = new KeyValue(Bytes.toBytes("info1"),Bytes.toBytes("info"),Bytes.toBytes("password"),Bytes.toBytes("12345611"));
        try {
            p.add(c);
            p.add(c1);
            p.add(c2);
            p2.add(c20);
            p2.add(c21);
            p2.add(c22);
            table.put(p);
            table.put(p2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ////////////////////
        //读取
        try {
            Get g = new Get(Bytes.toBytes("info1"));
            Result rs = table.get(g);
            for (KeyValue kv : rs.list()) {
                System.out.println("family:" + Bytes.toString(kv.getFamily()));
                System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
                System.out.println("value:" + Bytes.toString(kv.getValue()));
                System.out.println("Timestamp:" + kv.getTimestamp());
                System.out.println("-------------------------------------------");
            }
//            for (Result re: rs) {
//                System.out.println(re.current());
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ////////////////////
        //删除
//        try {
//            Delete d = new Delete(Bytes.toBytes("TheRealMT"));
//            table.delete(d);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Scan scan = new Scan();
        ResultScanner rs = null;

        try {
            rs = table.getScanner(scan);
            for (Result r : rs) {
                for (KeyValue kv : r.list()) {
                    System.out.println("row:" + Bytes.toString(kv.getRow()));
                    System.out.println("family:"
                            + Bytes.toString(kv.getFamily()));
                    System.out.println("qualifier:"
                            + Bytes.toString(kv.getQualifier()));
                    System.out
                            .println("value:" + Bytes.toString(kv.getValue()));
                    System.out.println("timestamp:" + kv.getTimestamp());
                    System.out
                            .println("-------------------------------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
