package com.yt.capter.capter_5_base_block.同步工具类.FutureTaskTest;

import com.yt.capter.tool.DataCenterUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author: YT
 * @date: 2022/1/20/020
 */
public class Preloader {
    @Test
    public void test() throws SQLException {
        start();
        ResultSet resultSet = get();
        ProductInfo info= (ProductInfo) DataCenterUtils.resoleResultSet(resultSet,ProductInfo.class);
        if (Objects.nonNull(info)) {
            System.out.println(info.toString());
        }
        while (resultSet.next()) {
            System.out.println(resultSet.getString("price"));
        }
    }

    /**
     * 获取线程执行回调  可以在一些费时间的操作先启动计算结果 并返回结果
     */
    private final FutureTask<ResultSet> futureTask = new FutureTask<ResultSet>(new Callable<ResultSet>() {
        @Override
        public ResultSet call() throws Exception {
            // 回调函数 在
            Connection connection = DataCenterUtils.connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product_info ");
            return resultSet;
        }
    });
    public final Thread thread = new Thread(futureTask);


    public void start() {
        thread.start();
    }

    public ResultSet get() {
        try {
            return futureTask.get();
        } catch (Exception e) {
            throw new InternalError();
        }
    }
}

/**
 * demo test product information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class ProductInfo {
    String name;
    Integer time;
    Integer price;
}
