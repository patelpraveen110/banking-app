package net.javaguides.banking.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import net.javaguides.banking.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id) throws AccountNotFoundException;
	
	AccountDto deposit(Long id, double amount) throws AccountNotFoundException;
	
	AccountDto withdraw(Long id, double amount) throws AccountNotFoundException;
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id) throws AccountNotFoundException;

}
