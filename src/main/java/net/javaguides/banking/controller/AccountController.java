package net.javaguides.banking.controller;

import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	//API for creating an account
	@PostMapping("/createAccount")
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	//API for getting an account from server
	@GetMapping("/getAccountById/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) throws AccountNotFoundException{
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	//API for updating account balance
	@PutMapping("/deposite/{id}")
	public ResponseEntity<AccountDto> deposite(@PathVariable Long id, @RequestBody Map<String,Double> request) throws AccountNotFoundException{
		
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.deposit(id,amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//API for updating account balance
	@PutMapping("/withdraw/{id}")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request) throws AccountNotFoundException{
		
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id,amount);
		return ResponseEntity.ok(accountDto);
	}
	
	@GetMapping("/getAllAccounts")
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
}
