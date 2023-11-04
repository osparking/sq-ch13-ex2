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
	// 2. 한 계정의 금액을 변경하기 - updateAmount()
	// 3. 한 계정의 정보를 읽어 반환하기 - - getOneAccount
	
	// 1
	public List<Account> getAllAccounts() {
		String sql = "select * from account";
		return template.query(sql, new AccountMapper());
	}
	
	// 2
	public void updateAmount(int sndrId, int rcvrId, BigDecimal transferAmt) {
		// 두 계정 정보를 읽어온다.
		Account acctSndr = getOneAccount(sndrId);
		Account acctRcvr = getOneAccount(rcvrId);
		
		// 두 계정의 새 금액을 계산한다.
		var amtSndr = acctSndr.getAmount().subtract(transferAmt);
		var amtRcvr = acctRcvr.getAmount().add(transferAmt);
		
		// 두 계정의 금액을 갱신한다.
		String sql = "update account set amount=? where id=?";
		template.update(sql, sndrId, amtSndr);
		template.update(sql, rcvrId, amtRcvr);
	}
	
	// 3. 
	public Account getOneAccount(int acctId) {
		String sql = "select * from account where id=?";
		return template.queryForObject(sql, new AccountMapper(), acctId);
	}
}
