package me.omar.moneyAPI.interfaces.repositories;

import me.omar.moneyAPI.interfaces.Validatable;

public interface Pagination extends Validatable {

    int getRecordsPerPage();

    int getPageNumber();
}
