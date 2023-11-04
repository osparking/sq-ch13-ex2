package sq_ch13_ex2.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import sq_ch13_ex2.dto.Account;

public class AccountMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		var account = new Account();
		account.setId(rs.getInt("id"));
		account.setName(rs.getString("name"));
		account.setAmount(rs.getBigDecimal("amount"));
		return account;
	}

}
