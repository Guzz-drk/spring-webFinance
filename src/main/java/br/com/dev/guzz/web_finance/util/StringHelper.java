package br.com.dev.guzz.web_finance.util;

import org.springframework.stereotype.Component;

@Component
public class StringHelper {

    public static boolean isNullOrEmpty(String t){
        return t == null || t.trim().isEmpty();
    }
}
