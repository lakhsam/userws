package me.ahmed.batch.tools;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;

public class ParameterSetter implements PreparedStatementSetter {

	private final String x;

	public ParameterSetter(String x) {
		this.x = x;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setString(1, x);
	}
}