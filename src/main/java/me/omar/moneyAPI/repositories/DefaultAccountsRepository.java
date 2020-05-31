package me.omar.moneyAPI.repositories;

import me.omar.moneyAPI.interfaces.repositories.AccountsRepository;
import me.omar.moneyAPI.interfaces.Account;
import me.omar.moneyAPI.interfaces.Holder;
import me.omar.moneyAPI.interfaces.repositories.HolderRepository;
import me.omar.moneyAPI.interfaces.repositories.PagedResult;
import me.omar.moneyAPI.models.accounts.AbstractAccount;
import me.omar.moneyAPI.service.PagedResultImpl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

final class DefaultAccountsRepository implements AccountsRepository {

    private final ConcurrentMap<String, Account> accounts;
    private final HolderRepository holderRepository;

    DefaultAccountsRepository(HolderRepository holderRepository) {
        this.holderRepository = holderRepository;
        this.accounts = new ConcurrentHashMap<>();
    }

    @Override
    public Account getById(String id) {
        return accounts.getOrDefault(id, getInvalid());
    }


    @Override
    public Account addAccount(String number, Holder holder, BigDecimal balance) {
        final Account account = AbstractAccount.makeActiveAccount(number, holder, balance);
        accounts.putIfAbsent(account.getId(), account);
        return account;
    }

    @Override
    public int size() {
        return accounts.size();
    }

    @Override
    public Account getInvalid() {
        return AbstractAccount.getInvalid();
    }

    @Override
    public PagedResult<Account> getAll(int pageNumber, int recordsPerPage) {
        return PagedResultImpl.from(pageNumber, recordsPerPage, accounts);
    }

    @Override
    public Collection<Account> getByHolder(Holder holder) {
        return Collections.unmodifiableCollection(
                accounts.values().stream()
                .filter(a -> a.getHolder().equals(holder))
                .sorted(Comparator.comparing(Account::getId))
                .collect(Collectors.toList()));
    }
}
