package com.myfirstapp.markgeohelper.repositories.repint;

import com.myfirstapp.markgeohelper.exceptions.repositoryex.ItemNotFoundException;
import com.myfirstapp.markgeohelper.types.mydata.MyData;

import java.util.List;
import java.util.UUID;

public abstract class RepositoryImpl<T extends MyData> implements Repository<T> {
    private List<T> items;
    public RepositoryImpl(List<T> items){
        this.items = items;
    }
    @Override
    public List<T> getList() {
        return items;
    }

    @Override
    public void removeItem() {
        items.remove(items.size()-1);
    }

    @Override
    public T getItem(UUID id) {
        for (T type : items){
            if (id.equals(type.getId())){
                return type;
            }
        }
        throw new ItemNotFoundException(String.valueOf(id));
    }

    @Override
    public T getLastItem() {
        return items.get(items.size()-1);
    }
}
