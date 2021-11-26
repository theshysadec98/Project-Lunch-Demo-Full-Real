package com.example.common;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;

public class CheckEmpty {
    public static boolean standardInput(String value){
        return (value == null || value.trim().equals(""));
    }
    public static boolean checkTimestamp(Timestamp timestamp){
        return timestamp == null;
    }
}
