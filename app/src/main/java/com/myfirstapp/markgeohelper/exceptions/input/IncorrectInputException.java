package com.myfirstapp.markgeohelper.exceptions.input;

import com.myfirstapp.markgeohelper.exceptions.GeodezExcepiton;

public class IncorrectInputException extends GeodezExcepiton{
    public IncorrectInputException(String request) {
        super(request);
    }
}
