package com.line;

import com.dbexecise.domain.User;
import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        LineReader<Hospital> hospitalLineReader = new LineReader<>(new HospitalParser());
        String filename = "C:\\Users\\khn11\\Downloads\\seoul_hospital_infos.csv";
        List<Hospital> hospitals = hospitalLineReader.readLines(filename);


        /*
        for (Hospital hospital : hospitals) {
            System.out.printf("%s,%s,%s,%s,%d,%s,%s\n",
                    hospital.getId(), hospital.getAddress(), hospital.getDistrict(),
                    hospital.getCategory(),hospital.getEmergencyRoom(), hospital.getName(),
                    hospital.getSubdivision());
        }

         */

        /*
        List<String> hospitalQry = new ArrayList<>();

        for (Hospital hospital : hospitals) {
            hospitalQry.add(hospital.getSqlInsertQuery2());
        }

        String sqlFilename = "seoul_hospital_insert.sql";
        hospitalLineReader.createANewFile(sqlFilename);
        hospitalLineReader.writeLines(hospitalQry, sqlFilename);

         */

        User user = new User();
        //hospitalLineReader.insert();
        user = hospitalLineReader.select("4");
        System.out.println(user.getName());
    }
}
