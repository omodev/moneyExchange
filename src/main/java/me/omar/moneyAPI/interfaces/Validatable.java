package me.omar.moneyAPI.interfaces;


public interface Validatable {

    boolean isValid();

    default boolean isNotValid() {
        return !isValid();
    }
}
