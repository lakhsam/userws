package me.ahmed.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import me.ahmed.batch.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();
		user.setId(rs.getString("ID"));
		user.setUsername(rs.getString("USERNAME"));
		user.setFirstname(rs.getString("PRENOM"));
		user.setLastname(rs.getString("NOM"));
		user.setEmail(rs.getString("EMAIL"));
		user.setGsm(rs.getString("TELEPHONE_PRINCIPAL"));
		user.setCreationDate(rs.getDate("CREATEDON"));
		user.setVersion(rs.getInt("VERSION"));
		user.setStatut(rs.getString("STATUT"));
		user.setCodeBanque(rs.getString("CODE_BANQUE_ASSOCIE"));
		
		return user;
	}
}
