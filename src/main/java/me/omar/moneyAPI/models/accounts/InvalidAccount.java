package me.omar.moneyAPI.models.accounts;


import me.omar.moneyAPI.interfaces.Base;
import me.omar.moneyAPI.interfaces.Account;
import me.omar.moneyAPI.models.holders.AbstractHolder;

import java.math.BigDecimal;

final class InvalidAccount extends AbstractAccount {

    private InvalidAccount() {
        super(Base.INVALID_ID, "", AbstractHolder.getInvalid(), false, BigDecimal.ZERO);
    }

    @Override
    public int hashCode() {
        return Base.INVALID_ID.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        return (obj instanceof InvalidAccount);
    }

    @Override
    public String toString() {
        final String base = super.toString();
        return base.replace("Account{", "InvalidAccount{");
    }

    private static class LazyHolder {
        private static final InvalidAccount INSTANCE = new InvalidAccount();
    }

    static Account getInstance() {
        return LazyHolder.INSTANCE;
    }
}
