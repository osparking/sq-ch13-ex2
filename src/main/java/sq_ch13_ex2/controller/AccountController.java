package sq_ch13_ex2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sq_ch13_ex2.dto.Account;
import sq_ch13_ex2.dto.TransferRequest;
import sq_ch13_ex2.service.AccountService;

@RestController
@AllArgsConstructor
public class AccountController {
	private final AccountService service;
	
	@PostMapping("/transfer") 
	public void transferMoney(@RequestBody TransferRequest transfer) {
		service.transferMoney(
				transfer.getSndrId(),
				transfer.getRcvrId(), 
				transfer.getAmount());
	}
	
	@GetMapping("/accounts") 
	public List<Account> getAllAccounts() {
		return service.getAllAccounts();
	}
	
}
