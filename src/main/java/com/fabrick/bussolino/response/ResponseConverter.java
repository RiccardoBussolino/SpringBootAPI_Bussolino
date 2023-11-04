package com.fabrick.bussolino.response;

public interface ResponseConverter<T, U> {
    public JsonResponse<T> convertResponse();
}

