package br.com.dev.guzz.web_finance.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListHelper {

    public static boolean isNullOrEmpty(List<?> list){
        return list == null || list.isEmpty();
    }
}
