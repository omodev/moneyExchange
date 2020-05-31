package me.omar.moneyAPI.repositories;

import me.omar.moneyAPI.interfaces.Holder;
import me.omar.moneyAPI.interfaces.repositories.HolderRepository;
import me.omar.moneyAPI.interfaces.repositories.PagedResult;
import me.omar.moneyAPI.models.holders.AbstractHolder;
import me.omar.moneyAPI.service.PagedResultImpl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class DefaultHolderRepository implements HolderRepository {

    private final ConcurrentMap<String, Holder> holders;

    DefaultHolderRepository() {
        holders = new ConcurrentHashMap<>();
    }


    @Override
    public Holder getById(String id) {
        return holders.getOrDefault(id, getInvalid());
    }

    @Override
    public Holder getInvalid() {
        return AbstractHolder.getInvalid();
    }

    @Override
    public Holder addHolder(String name, String email) {
        final Holder holder= AbstractHolder.makeSampleHolder( name, email);
        holders.putIfAbsent(holder.getId(), holder);
        return holder;
    }

    @Override
    public PagedResult<Holder> getAll(int pageNumber, int recordsPerPage) {
        return PagedResultImpl.from(pageNumber, recordsPerPage, holders);
    }

    @Override
    public int size() {
        return holders.size();
    }
}
