/**
 * This class tests the isAnagram method from Leetcode.
 *
 * @author Eddie
 * @version 1.0
 *
 **/
public class Solution {
    public static void main(String[] args) {
        String s = "racecar";
        String t = "race a car";
    }
    /**
     * @param s: string
     * @param t: string we want to check whether an anagram of s
     * @return true if t an anagram of s, otherwise false.
     **/
    public boolean isAnagram(String s, String t) {
        HashMap <String, Integer> sMap = new HashMap();
        for(char c: s.toCharArray()) {
            sMap.put(c, 1 + sMap.getOrDefault(c, 0));
        }

    }
}

//generate javadoc: javadoc -d docs Solution.java