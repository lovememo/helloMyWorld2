package com.opm.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.opm.common.enumdict.ApplyType;

public class EnumApplyTypeHandler extends BaseTypeHandler<ApplyType> {

	@Override
	public ApplyType getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
		String applyCode = resultSet.getString(columnName);
		if (applyCode != null) {
			return ApplyType.valueOfCode(applyCode);
		}

		return null;
	}

	@Override
	public ApplyType getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
		String applyCode = resultSet.getString(columnIndex);
		if (applyCode != null) {
			return ApplyType.valueOfCode(applyCode);
		}

		return null;
	}

	@Override
	public ApplyType getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
		String applyCode = callableStatement.getString(columnIndex);
		if (applyCode != null) {
			return ApplyType.valueOfCode(applyCode);
		}

		return null;
	}

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, ApplyType eventProcessStatus, JdbcType jdbcType) throws SQLException {
		 preparedStatement.setString(i, String.valueOf(eventProcessStatus.toString()));
	}
}
