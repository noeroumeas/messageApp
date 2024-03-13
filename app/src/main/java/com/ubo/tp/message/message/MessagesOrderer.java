package com.ubo.tp.message.message;

import com.ubo.tp.message.datamodel.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesOrderer {
    public static ArrayList<Message> orderMessagesList(List<Message> unorderedList){
        ArrayList<Message> orderedList = new ArrayList<>();
        for(Message m : unorderedList){
            addMessageToList(orderedList, m);
        }
        return orderedList;
    }

    protected static void addMessageToList(List<Message> orderedList, Message message){
        int debut = 0;
        int fin = orderedList.size() - 1;

        while (debut <= fin) {
            int milieu = debut + (fin - debut) / 2;
            if (orderedList.get(milieu).getEmissionDate() < message.getEmissionDate()) {
                debut = milieu + 1;
            } else if (orderedList.get(milieu).getEmissionDate() > message.getEmissionDate()) {
                fin = milieu - 1;
            } else {
                debut = milieu;
                break;
            }
        }
        orderedList.add(debut, message);
    }
}
