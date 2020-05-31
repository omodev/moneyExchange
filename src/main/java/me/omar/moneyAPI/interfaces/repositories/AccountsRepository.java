package me.omar.moneyAPI.interfaces.repositories;


import me.omar.moneyAPI.interfaces.Account;
import me.omar.moneyAPI.interfaces.Holder;

import java.math.BigDecimal;
import java.util.Collection;

public interface AccountsRepository extends Repository<Account> {

    Account addAccount(String number, Holder holder, BigDecimal balance);
    Collection<Account> getByHolder(Holder holder);
    PagedResult<Account> getAll(int pageNumber, int recordsPerPage);
}
