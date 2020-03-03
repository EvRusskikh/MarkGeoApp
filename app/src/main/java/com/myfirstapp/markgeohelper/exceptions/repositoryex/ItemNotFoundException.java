package com.myfirstapp.markgeohelper.exceptions.repositoryex;

import com.myfirstapp.markgeohelper.exceptions.GeodezExcepiton;

public class ItemNotFoundException extends GeodezExcepiton {
    public ItemNotFoundException(String request) {
        super(request);
    }
}
