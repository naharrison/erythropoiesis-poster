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


  public void settings() {
    size(1500, 975);
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
    image(flowchart, 0, 0, 975, 975);

    for(int k = 0; k < txt2data.x.size(); k++) {
      int x = txt2data.x.get(k);
      int y = txt2data.y.get(k);
      int r = txt2data.r.get(k);

      fill(0);
      if(Math.sqrt((mouseX-x)*(mouseX-x) + (mouseY-y)*(mouseY-y)) < r) text(txt2data.info.get(k), 980, 0, 520, 975);

      if(debugMode.equals(1)) {
        text(mouseX, 980, 955);
        text(mouseY, 1025, 955);
        fill(0, 204, 255, 70);
        ellipse(x, y, 2*r, 2*r);
      }
    }
  }


}
