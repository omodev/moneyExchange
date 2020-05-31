package me.omar.moneyAPI.service;

import me.omar.moneyAPI.enums.TxStatus;
import me.omar.moneyAPI.interfaces.Account;
import me.omar.moneyAPI.interfaces.Holder;
import me.omar.moneyAPI.interfaces.Transaction;
import me.omar.moneyAPI.interfaces.repositories.TransactionRepository;
import me.omar.moneyAPI.service.TransferService;
import me.omar.moneyAPI.utils.TransactionPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransferServiceTest {

    private TransferService transferServiceUnderTest;

    @BeforeEach
    public void setUp() {
        transferServiceUnderTest = TransferService.getInstance();
    }


    @Test
    public void testTransfer() {
        final Holder debitH = mock(Holder.class);
        final Holder creditH = mock(Holder.class);
        final BigDecimal amount = new BigDecimal("10.00");

        Account debit = transferServiceUnderTest.getContext().getAccountsRepository().addAccount("12345678901234567890", debitH, amount);
        Account credit = transferServiceUnderTest.getContext().getAccountsRepository().addAccount("12345678901234567891", creditH, amount);
        TransactionPayload payload = new TransactionPayload(debit.getId(), credit.getId(), amount);
        TransactionRepository txRepo = spy(TransactionRepository.class);

        final Transaction result = transferServiceUnderTest.transfer(payload);

        assertEquals(debit, result.getDebit());
        assertEquals(credit, result.getCredit());
        assertEquals(amount, result.getAmount());
        assertEquals(TxStatus.COMPLETED, result.getStatus());
    }

}
