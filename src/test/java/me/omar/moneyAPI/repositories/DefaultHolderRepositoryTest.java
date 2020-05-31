package me.omar.moneyAPI.repositories;

import me.omar.moneyAPI.interfaces.Holder;
import me.omar.moneyAPI.models.holders.AbstractHolder;
import me.omar.moneyAPI.repositories.DefaultHolderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultHolderRepositoryTest {

    private DefaultHolderRepository defaultHolderRepositoryUnderTest;

    @BeforeEach
    public void setUp() {
        defaultHolderRepositoryUnderTest = new DefaultHolderRepository();
    }

    @Test
    public void testGetById() {
        final String id = "id";
        final Holder expectedResult = AbstractHolder.getInvalid();

        final Holder result = defaultHolderRepositoryUnderTest.getById(id);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddHolder() {
        final String name = "name";
        final String email = "email";

        final Holder result = defaultHolderRepositoryUnderTest.addHolder(name, email);

        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());
    }
}
