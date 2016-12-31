package com.opm.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.opm.common.enumdict.DtlStatus;

/**
 * Created by kfzx-jinjf on 2016/12/16.
 */
public class EnumDtlStatusHandler extends BaseTypeHandler<DtlStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, DtlStatus dtlStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, String.valueOf(dtlStatus.toString()));
    }

    @Override
    public DtlStatus getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String statusCode = resultSet.getString(s);
        if (statusCode != null) {
            return DtlStatus.valueOfCode(statusCode);
        }

        return null;
    }

    @Override
    public DtlStatus getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String statusCode = resultSet.getString(i);
        if (statusCode != null) {
            return DtlStatus.valueOfCode(statusCode);
        }

        return null;
    }

    @Override
    public DtlStatus getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String statusCode = callableStatement.getString(i);
        if (statusCode != null) {
            return DtlStatus.valueOfCode(statusCode);
        }

        return null;
    }
}
