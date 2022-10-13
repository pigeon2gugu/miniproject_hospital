package com.line.parser;

import com.line.domain.Hospital;

public class HospitalParser implements Parser<Hospital> {

    private String replaceAllQuot(String str) {
        return str.replaceAll("\"","");
    }
    @Override
    public Hospital parse(String str) {
        str = replaceAllQuot(str); //line 받을때부터 모든 ""를 없앤다
        String[] splitted = str.split(",");

        return new Hospital(splitted[0],splitted[1],splitted[2],Integer.parseInt(splitted[6]),splitted[10]);
    }
}
