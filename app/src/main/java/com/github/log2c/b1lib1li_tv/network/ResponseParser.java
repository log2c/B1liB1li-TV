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
        //将okhttp3.Response转换为自定义的Response对象
        BaseModel<T> data = Converter.convertTo(response, BaseModel.class, types);
        T t = data.getData();
        if (data.getCode() != 0 || t == null) {
            throw new ParseException(String.valueOf(data.getCode()), data.getMessage(), response);
        }
        return t;
    }
}