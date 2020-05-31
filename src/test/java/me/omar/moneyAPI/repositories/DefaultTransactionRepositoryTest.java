package me.omar.moneyAPI.repositories;

import me.omar.moneyAPI.interfaces.Account;
import me.omar.moneyAPI.interfaces.Transaction;
import me.omar.moneyAPI.models.transactions.MoneyTransaction;
import me.omar.moneyAPI.repositories.DefaultTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static  org.mockito.Mockito.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultTransactionRepositoryTest {

    private DefaultTransactionRepository defaultTransactionRepositoryUnderTest;

    @BeforeEach
    public void setUp() {
        defaultTransactionRepositoryUnderTest = new DefaultTransactionRepository();
    }

    @Test
    public void testGetById() {
        final String id = "id";
        final Transaction expectedResult = MoneyTransaction.getInvalid();

        final Transaction result = defaultTransactionRepositoryUnderTest.getById(id);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testAdd() {
        final Account debit = mock(Account.class);
        final Account credit = mock(Account.class);
        final BigDecimal amount = new BigDecimal("10.00");

        final Transaction result = defaultTransactionRepositoryUnderTest.add(debit, credit, amount);

        assertEquals(debit, result.getDebit());
        assertEquals(credit, result.getCredit());
        assertEquals(amount, result.getAmount());
    }
}
