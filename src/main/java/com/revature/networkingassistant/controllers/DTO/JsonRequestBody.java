package com.revature.networkingassistant.controllers.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.beans.SessionToken;

import java.util.Objects;

public class JsonRequestBody<T> {
    @JsonView(Views.Public.class)
    protected SessionToken token;

    @JsonView(Views.Public.class)
    protected T object;

    public JsonRequestBody() {
    }

    public JsonRequestBody(SessionToken token, T object) {
        this.token = token;
        this.object = object;
    }

    public SessionToken getToken() {
        return token;
    }

    public void setToken(SessionToken token) {
        this.token = token;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonRequestBody<?> that = (JsonRequestBody<?>) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(object, that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, object);
    }

    @Override
    public String toString() {
        return "JsonRequestBody{" +
                "token=" + token +
                ", object=" + object +
                '}';
    }
}
