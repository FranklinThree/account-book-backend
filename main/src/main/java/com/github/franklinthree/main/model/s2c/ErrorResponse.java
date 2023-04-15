package com.github.franklinthree.main.model.s2c;

import com.google.gson.Gson;

public class ErrorResponse {
    int errorId;
    String errorMassage;

    public ErrorResponse(int errorId, String errorMassage) {
        this.errorId = errorId;
        this.errorMassage = errorMassage;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
