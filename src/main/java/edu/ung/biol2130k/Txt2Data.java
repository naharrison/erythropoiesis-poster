package edu.ung.biol2130k;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Txt2Data {

  public ArrayList<Integer> x, y, r;
  public ArrayList<String> name, info;

  public Txt2Data(BufferedReader reader) {
    x = new ArrayList<>();
    y = new ArrayList<>();
    r = new ArrayList<>();
    name = new ArrayList<>();
    info = new ArrayList<>();

    Integer itemLineNo = 0;

    try {
      String thisLine = reader.readLine();
      while(thisLine != null) {
        // itemLineNo == 0 are the separating "ITEM:" lines
        if(itemLineNo.equals(1)) x.add(Integer.parseInt(thisLine));
        else if(itemLineNo.equals(2)) y.add(Integer.parseInt(thisLine));
        else if(itemLineNo.equals(3)) r.add(Integer.parseInt(thisLine));
        else if(itemLineNo.equals(4)) name.add(thisLine);
        else if(itemLineNo.equals(5)) info.add(thisLine);
        itemLineNo++;
        itemLineNo = itemLineNo % 6;
        thisLine = reader.readLine();
      }
      reader.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
