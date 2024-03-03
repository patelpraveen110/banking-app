package net.javaguides.banking.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Service;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return (AccountMapper.mapToAccountDto(savedAccount));
	}

//	@Override
//	public AccountDto getAccountById(Long id) {
//		
//		Optional<Account> optionalAccount = accountRepository.findById(id);
//		
//		if (optionalAccount.isPresent()) {
//			Account account = optionalAccount.get();
//			return AccountMapper.mapToAccountDto(account);
//        } else {
//            System.out.println("Account not found for ID: " + id);
//        }
//				
//		return null;
//	}
	
	public AccountDto getAccountById(Long id) throws AccountNotFoundException {
	    Optional<Account> optionalAccount = accountRepository.findById(id);

	    if (optionalAccount.isPresent()) {
	        Account account = optionalAccount.get();
	        return AccountMapper.mapToAccountDto(account);
	    } else {
	        throw new AccountNotFoundException("Account not found for ID: " + id);
	    }
	}

	@Override
	public AccountDto deposit(Long id, double amount) throws AccountNotFoundException {
		Optional<Account> optionalAccount = accountRepository.findById(id);

	    if (optionalAccount.isPresent()) {
	        Account account = optionalAccount.get();
	        
	        double total = account.getBalance() + amount;
	        account.setBalance(total);
	        Account savedAccount = accountRepository.save(account);
	        return AccountMapper.mapToAccountDto(savedAccount);
	    } else {
	        throw new AccountNotFoundException("Account not found for ID: " + id);
	    }
	}

	@Override
	public AccountDto withdraw(Long id, double amount) throws AccountNotFoundException {
		
		Optional<Account> optionalAccount = accountRepository.findById(id);
	    if (optionalAccount.isPresent()) {
	        Account account = optionalAccount.get();
	        
	        if(account.getBalance() < amount) {
	        	throw new AccountNotFoundException("Insufficient balance for: " + id);
	        }
	        double total = account.getBalance() - amount;
	        account.setBalance(total);
	        Account savedAccount = accountRepository.save(account);
	        return AccountMapper.mapToAccountDto(savedAccount);
	    } else {
	        throw new AccountNotFoundException("Account not found for ID: " + id);
	    }
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		List<AccountDto> result = accounts.stream().map(account-> AccountMapper.mapToAccountDto(account))
		                  .collect(Collectors.toList());
		return result;
	}
}





















