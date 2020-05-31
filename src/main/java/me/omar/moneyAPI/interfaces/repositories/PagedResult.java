package me.omar.moneyAPI.interfaces.repositories;

import java.util.Collection;

public interface PagedResult<T> {

    boolean hasMore();

    Collection<T> getContent();

    int getPageNumber();

    int getRecordsPerPage();
}
