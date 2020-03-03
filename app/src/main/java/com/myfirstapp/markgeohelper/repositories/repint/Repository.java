package com.myfirstapp.markgeohelper.repositories.repint;

import java.util.List;
import java.util.UUID;

public interface Repository<T>{
    List<T> getList();
    void addItem();
    void removeItem();
    T getItem(UUID id);
    T getLastItem();
}
