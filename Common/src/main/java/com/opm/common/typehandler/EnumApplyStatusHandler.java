package com.opm.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.opm.common.enumdict.ApplyStatus;

public class EnumApplyStatusHandler extends BaseTypeHandler<ApplyStatus> {

	@Override
	public ApplyStatus getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
		String statusCode = resultSet.getString(columnName);
		if (statusCode != null) {
			return ApplyStatus.valueOfCode(statusCode);
		}

		return null;
	}

	@Override
	public ApplyStatus getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
		String statusCode = resultSet.getString(columnIndex);
		if (statusCode != null) {
			return ApplyStatus.valueOfCode(statusCode);
		}

		return null;
	}

	@Override
	public ApplyStatus getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
		String statusCode = callableStatement.getString(columnIndex);
		if (statusCode != null) {
			return ApplyStatus.valueOfCode(statusCode);
		}

		return null;
	}

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, ApplyStatus status, JdbcType jdbcType) throws SQLException {
		 preparedStatement.setString(i, String.valueOf(status.toString()));
	}
}
