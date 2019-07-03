package edu.ung.biol2130k;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author naharrison
 */
public class Poster extends PApplet {

  public static void main(String[] args) {
    PApplet.main("edu.ung.biol2130k.Poster");
  }


  public PImage flowchart;
  public Integer debugMode;
  public Txt2Data txt2data;
  public Integer winw, winh; // window width, height
  public float picWidthFrac;


  public void settings() {
    picWidthFrac = (float) 0.65;

    winw = 400; // default values in case windowSize.txt DNE
    winh = 400;

    // read the window size from file:
    BufferedReader reader = createReader("windowSize.txt");
    try {
      winw = Integer.parseInt(reader.readLine());
      winh = Integer.parseInt(reader.readLine());
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    size(winw, winh);
  }


  public void setup() {
    frameRate(10);
    textSize(14);
    flowchart = loadImage("flowchart.png");

    // read the state of debugMode from file:
    BufferedReader debugBR = createReader("debugMode.txt");
    try {
      debugMode = Integer.parseInt(debugBR.readLine());
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    BufferedReader dataReader = createReader("info.txt");
    txt2data = new Txt2Data(dataReader);
  }


  public void draw() {
    background(240);
    image(flowchart, 0, 0, (int) (picWidthFrac*winw), winh);

    for(int k = 0; k < txt2data.x.size(); k++) {
      int x = (int) ((txt2data.x.get(k)/100.0)*picWidthFrac*winw);
      int y = (int) ((txt2data.y.get(k)/100.0)*winh);
      int r = (int) ((txt2data.r.get(k)/100.0)*picWidthFrac*winw);

      fill(0);
      if(Math.sqrt((mouseX-x)*(mouseX-x) + (mouseY-y)*(mouseY-y)) < r) text(txt2data.info.get(k), (int) (picWidthFrac*winw)+10, 10, (int) (((1.0-picWidthFrac)*winw)-10), winh);

      if(debugMode.equals(1)) {
        text(  (int) (mouseX*100.0/(picWidthFrac*winw))  , (int) (picWidthFrac*winw)+10, winh-20);
        text(  (int) (mouseY*100.0/winh)  , (int) (picWidthFrac*winw)+60, winh-20);
        fill(0, 204, 255, 70);
        ellipse(x, y, 2*r, 2*r);
      }
    }
  }


}
