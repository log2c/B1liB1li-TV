package com.github.log2c.b1lib1li_tv.network;

import com.github.log2c.b1lib1li_tv.model.BaseModel;

import java.io.IOException;
import java.lang.reflect.Type;

import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.exception.ParseException;
import rxhttp.wrapper.parse.TypeParser;
import rxhttp.wrapper.utils.Converter;

@Parser(name = "Response")
public class ResponseParser<T> extends TypeParser<T> {

    protected ResponseParser() {
        super();
    }

    public ResponseParser(Type type) {
        super(type);
    }

    @Override
    public T onParse(okhttp3.Response response) throws IOException {
        BaseModel<T> data = Converter.convertTo(response, BaseModel.class, types);
        T t = data.getData();
        if (data.getCode() != 0) {
            throw new ParseException(String.valueOf(data.getCode()), data.getMessage(), response);
        }
        return t;
    }
}