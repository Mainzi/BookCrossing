package com.library.util;

public  class ParseString {
    public static void main(String[] args){
        String s = "Первый Фамилия, Второй Он, третий, четвертый";
        String[] tokens = s.split(",");
        for(String t : tokens){
            t.trim();
            System.out.println(t);
        }
    }
}
