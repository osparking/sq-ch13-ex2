package sq_ch13_ex2.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import sq_ch13_ex2.dto.Account;

@Repository
@AllArgsConstructor
public class AccountRepository {
	private final JdbcTemplate template;
	
	// 총 3 개의 메소드를 만든다.
	// 1. 모든 계정 읽어 반환하기 - getAllAccounts()
	// 2. 새 계정 정보를 디비에 저장하기 - storeAccount()
	// 3. 한 계정의 정보를 읽어 반환하기 - - getOneAccount
	
	// 1
	public List<Account> getAllAccounts() {
		String sql = "select * from account";
		return template.query(sql, new AccountMapper());
	}
	
	// 2
	public void storeAccount(Account account) {
		// 계정 정보를 디비 테이블에 저장한다.
		// INSERT INTO account(name, amount) VALUES('철수', 100000);
		String sql = "insert into account(name, amount) values(?, ?)";
		template.update(sql, account.getName(), account.getAmount());
	}
	
	// 3. 
	public Account getOneAccount(int acctId) {
		String sql = "select * from account where id=?";
		return template.queryForObject(sql, new AccountMapper(), acctId);
	}
}
