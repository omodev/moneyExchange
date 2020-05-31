package me.omar.moneyAPI.interfaces;

public interface Base extends Validatable {

    String INVALID_ID = "";

    String getId();

    @Override
    default boolean isValid() {
        return !INVALID_ID.equalsIgnoreCase(getId());
    }
}
