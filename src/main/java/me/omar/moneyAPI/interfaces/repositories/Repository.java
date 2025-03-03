package me.omar.moneyAPI.interfaces.repositories;

public interface Repository<T> extends Pageable<T> {

    int size();

    T getById(String id);

    T getInvalid();

}
