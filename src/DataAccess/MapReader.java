package dataAccess;

import models.Map;
import models.FieldType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapReader {

    public Map readMap() throws IOException {

        List<List<FieldType>> lines = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("Map.txt")))
        {
            String line = br.readLine();
            while(line != null && !line.equals("")){
                ArrayList<FieldType> mapLine = new ArrayList<>();
                lines.add(mapLine);
                for(int i = 0; i< line.length();i++){
                    mapLine.add(getFieldType(line.charAt(i)));
                }
                line = br.readLine();
            }
        }
        return new Map(lines);
    }

    private FieldType getFieldType(char type){
        switch(type){
            case'x':
                return FieldType.ROAD;

            case'0':
                return FieldType.GRASS;

            case'@':
                return FieldType.CROSS;

            case'A':
                return FieldType.CAR;

            case'U':
                return FieldType.USERCAR;

            case'=':
                return FieldType.END;

            default:
                throw new IllegalArgumentException("Argument should be x, 0, @,=,A or U");
        }
    }


}
