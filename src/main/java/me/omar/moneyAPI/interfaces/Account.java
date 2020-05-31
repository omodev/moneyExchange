package me.omar.moneyAPI.interfaces;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;

public interface Account extends Base{

    String getNumber();

    BigDecimal getBalance();

    boolean debit(BigDecimal amount);

    boolean credit(BigDecimal amount);

    Holder getHolder();

    boolean isActive();

    Lock writeLock();
}
