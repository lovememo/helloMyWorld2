package com.opm.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.opm.common.enumdict.OrderType;

/**
 * Created by kfzx-liuyz1 on 2016/10/30.
 */
public class OrderTypeHandler extends BaseTypeHandler<OrderType> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, OrderType orderType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, String.valueOf(orderType.toString()));
    }

    @Override
    public OrderType getNullableResult(ResultSet resultSet, String columnName) throws SQLException {

        String orderType = resultSet.getString(columnName);

        if(orderType != null){

            return OrderType.valueOf(orderType);
        }

        return null;
    }

    @Override
    public OrderType getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {

        String orderType = resultSet.getString(columnIndex);

        if(orderType != null){

            return OrderType.valueOf(orderType);
        }

        return null;
    }

    @Override
    public OrderType getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {

        String orderType = callableStatement.getString(columnIndex);

        if(orderType != null){

            return OrderType.valueOf(orderType);
        }

        return null;
    }
}
