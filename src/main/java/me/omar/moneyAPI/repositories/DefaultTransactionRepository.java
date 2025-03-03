package me.omar.moneyAPI.repositories;

import me.omar.moneyAPI.models.transactions.MoneyTransaction;
import me.omar.moneyAPI.interfaces.Account;
import me.omar.moneyAPI.interfaces.Transaction;
import me.omar.moneyAPI.interfaces.repositories.PagedResult;
import me.omar.moneyAPI.interfaces.repositories.TransactionRepository;
import me.omar.moneyAPI.service.PagedResultImpl;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;

final class DefaultTransactionRepository implements TransactionRepository {

    private final ConcurrentMap<String, Transaction> transactions = new ConcurrentHashMap<>();

    @Override
    public Transaction getById(String id) {
        return transactions.getOrDefault(id, getInvalid());
    }

    @Override
    public Transaction getInvalid() {
        return MoneyTransaction.getInvalid();
    }

    @Override
    public int size() {
        return transactions.size();
    }

    @Override
    public Transaction add(Account debit, Account credit, BigDecimal amount) {
        final Transaction transaction = MoneyTransaction.make(debit, credit, amount);
        transactions.putIfAbsent(transaction.getId(), transaction);
        return transaction;
    }

    @Override
    public PagedResult<Transaction> getAll(int pageNumber, int recordsPerPage) {
        return PagedResultImpl.from(pageNumber, recordsPerPage, transactions);
    }

    @Override
    public PagedResult<Transaction> getByAccount(Account account, int pageNumber, int recordsPerPage) {
        Objects.requireNonNull(account, "Account cannot be null");
        Predicate<Transaction> predicate = t -> t.getDebit().equals(account) || t.getCredit().equals(account);
        return PagedResultImpl.from(pageNumber, recordsPerPage, transactions, predicate);
    }
}
