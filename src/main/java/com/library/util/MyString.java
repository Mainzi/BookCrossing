package com.library.util;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;

public class MyString implements Array {
    String[] items;

    public MyString(ArrayList<String> items) {
        this.items = items.toArray(new String[0]);
    }

    @Override
    public int getBaseType() throws SQLException {
        return Types.VARCHAR;
    }

    @Override
    public Object getArray() throws SQLException {
        return null;
    }

    @Override
    public Object getArray(Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public Object getArray(long index, int count) throws SQLException {
        return null;
    }

    @Override
    public Object getArray(long index, int count, Map<String, Class<?>> map) throws SQLException {
        return null;
    }


    @Override
    public ResultSet getResultSet() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getResultSet(Map<String, Class<?>> map) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getResultSet(long index, int count) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getResultSet(long index, int count, Map<String, Class<?>> map) throws SQLException {
        return null;
    }


    @Override
    public void free() throws SQLException {

    }

    @Override
    public String getBaseTypeName() throws SQLException {
        return "varchar";
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < items.length; i++) {
            builder.append(items[i]);
            if (i < items.length - 1) {
                builder.append(',');
            }
        }
        builder.append('}');
        return builder.toString();
    }

}