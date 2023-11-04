package sq_ch13_ex2.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import sq_ch13_ex2.dto.Account;
import sq_ch13_ex2.repository.AccountRepository;

@AllArgsConstructor
public class AccountService {
	private final AccountRepository repository;
	
	// 총 3 개의 메소드를 만든다.
	// 1. 모든 계정 읽어 반환하기 - getAllAccounts()
	// 2. 계좌 이체 구현하기 - transferMoney()
	
	// 1. 
	public List<Account> getAllAccounts() {
		return repository.getAllAccounts();
	}
	
	// 2.
	@Transactional
	public void transferMoney(int idSndr, int idRcvr, BigDecimal amount) {
		// 돈을 한 계정에서 다른 계정으로 이체하기
		// 두 계정 정보를 읽어온다.
		Account acctSndr = repository.getOneAccount(idSndr);
		Account acctRcvr = repository.getOneAccount(idRcvr);
		
		// 두 계정의 새 금액을 계산한다.
		var amtSndr = acctSndr.getAmount().subtract(amount);
		var amtRcvr = acctRcvr.getAmount().add(amount);
		
		// 두 계정의 금액을 갱신한다.
		repository.updateAmount(idSndr, amtSndr);
		repository.updateAmount(idRcvr, amtRcvr);
	}
}
