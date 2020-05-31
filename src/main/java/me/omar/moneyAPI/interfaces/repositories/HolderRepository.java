package me.omar.moneyAPI.interfaces.repositories;

import me.omar.moneyAPI.interfaces.Holder;

public interface HolderRepository extends Repository<Holder> {

    Holder addHolder(String name, String email);
}
