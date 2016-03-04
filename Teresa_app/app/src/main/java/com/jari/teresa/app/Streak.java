package com.jari.teresa.app;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 * @author Jari Van Melckebeke
 */
public class Streak {
    ArrayList<String[]> conversation = new ArrayList<String[]>();
    public Streak(){
        conversation.clear();
    }

    public void add(String[] result){
        conversation.add(result);
    }

    public String[] get(int i){
        return conversation.get(i);
    }
}
