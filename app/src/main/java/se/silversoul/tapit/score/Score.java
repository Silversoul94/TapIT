package se.silversoul.tapit.score;

import java.util.HashMap;

public class Score {
    public HashMap<Integer, String> list(){
        HashMap<Integer, String> scoreList = new HashMap<>();
        scoreList.put(1, "Hi! Thank you for downloading this app.");
        scoreList.put(9, "Have fun tapping!");
        scoreList.put(15, "Nice! keep tapping");
        scoreList.put(25, "Hope you are having fun.");
        scoreList.put(40, "....");
        scoreList.put(45, "Are you having fun?");
        scoreList.put(50, "Don't you have work to do?");
        scoreList.put(80, "You are soon gonna pass 100!");
        scoreList.put(100, "Congratulations you have now 100 tap");
        scoreList.put(150, "Are you gonna keep going? You just pass 100");
        scoreList.put(200, "Maybe you should stop");
        scoreList.put(300, "Hello!?");
        scoreList.put(350, "Are you that bored ?");
        scoreList.put(400, "You know there is other app in store right?");
        scoreList.put(500, "Please stop");
        scoreList.put(600, "Ok i will ignore you know");
        scoreList.put(700, "bye");
        scoreList.put(750, "");
        scoreList.put(5000, "Still here?" );
        scoreList.put(200000, "I have missed you");
        scoreList.put(10000000, "Please stop");
        scoreList.put(15000965, "Should I call help for you?");
        scoreList.put(15555555, "Have you tried fishing?");
        scoreList.put(20000000, "Don't you have a life ?");
        scoreList.put(100000000, "Well you did it! 100000000");
        scoreList.put(100000003, " Omg! Are you still going ?");
        return scoreList;
    }
}
