package me.omar.moneyAPI.repositories;

import me.omar.moneyAPI.interfaces.repositories.AccountsRepository;
import me.omar.moneyAPI.interfaces.repositories.HolderRepository;
import me.omar.moneyAPI.interfaces.repositories.TransactionRepository;

public class Context {

    private final HolderRepository holderRepository;
    private final AccountsRepository accountsRepository;
    private final TransactionRepository transactionRepository;

    private Context(HolderRepository holderRepository,
                    AccountsRepository accountsRepository,
                    TransactionRepository transactionRepository) {
        this.holderRepository = holderRepository;
        this.accountsRepository = accountsRepository;
        this.transactionRepository = transactionRepository;
    }

    public static Context create() {
        final HolderRepository holderRepository = new DefaultHolderRepository();
        final AccountsRepository accountsRepository = new DefaultAccountsRepository(holderRepository);
        final TransactionRepository transactionRepository = new DefaultTransactionRepository();
        return new Context(holderRepository, accountsRepository, transactionRepository);
    }

    public HolderRepository getHolderRepository() {
        return holderRepository;
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public AccountsRepository getAccountsRepository() {
        return accountsRepository;
    }
}
