package com.hbaseinaction;

import com.hbaseinaction.two.hbase.UsersDAO;
import com.hbaseinaction.two.model.User;
import org.apache.hadoop.hbase.client.HTablePool;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/14.
 */
public class Main22 {

    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("no args");
        }

        HTablePool pool = new HTablePool();
        UsersDAO dao =  new UsersDAO(pool);

        if("get".equals(args[0])){
            System.out.println("Getting user:"+args[1]);
            User u = dao.getUser(args[1]);

            System.out.println(u);
        }

        if("add".equals(args[0])){
            System.out.println("Adding user...");
            dao.addUser(args[1],args[2],args[3],args[4]);
            User u = dao.getUser(args[1]);
            System.out.println(u);
        }

        pool.closeTablePool(UsersDAO.TABLE_NAME);
    }
}
