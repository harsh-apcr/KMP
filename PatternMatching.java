import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class PatternMatching {

    private static int[] setPrefixLength(String pattern) {
        int j = 0;
        int n = pattern.length();
        int[] prefixLength = new int[n];
        prefixLength[0] = 0;
        int i = 1;
        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                prefixLength[i] = ++j;
                i++;
            }
            else {
                if (j==0) {
                    prefixLength[i] = 0;
                    i++;
                }
                else {
                    j = prefixLength[j-1];
                }
            }
        }
        return prefixLength;
    }

    public static ArrayList<Integer> subStringSearch(String text,String pattern) {
        final int[] prefixLength = setPrefixLength(pattern);
        final int n = text.length();
        final int m = pattern.length();
        int i = 0;
        int j = 0;
        ArrayList<Integer> patternOccurrences = new ArrayList<>();
        while (i < n) {
            if (pattern.charAt(j)!=text.charAt(i)) {
                if (j == 0) {
                    i++;
                }
                else {
                    j = prefixLength[j-1];
                }
            }
            else {
                if (j == m-1) {
                    patternOccurrences.add(i-m+1);
                    j = prefixLength[j-1];
                } else {
                    i++;
                    j++;
                }
            }
        }
        return patternOccurrences;
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final Random rd = new Random();
        int n = Integer.parseInt(br.readLine());    // text length
        int m = Integer.parseInt(br.readLine());    // pattern length
        StringBuilder text = new StringBuilder();
        for (int i = 0 ; i < n ;i++) {
            text.append((char) (rd.nextInt(26)+'a'));
        }
        StringBuilder pattern = new StringBuilder();
        for (int i = 0;i < m;i++) {
            pattern.append((char)(rd.nextInt(26) + 'a'));
        }
        String text1 = String.valueOf(text);
        String pattern1 = String.valueOf(pattern);
        int idx = -1;
        ArrayList<Integer> matchedIdx = subStringSearch(text1, pattern1);
        try {
            idx = matchedIdx.get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(idx);
        }
        System.out.println("does text contains pattern p ? " + !(idx == -1));
        if (idx!=-1) {
            boolean hasBug = false;
            for(int i : matchedIdx) {
                String toMatch = text.substring(i,i+m);
                if (!toMatch.equals(pattern1)) {
                    System.out.println("pattern finding has a bug");
                    System.out.println(toMatch);
                    System.out.println(pattern1);
                    hasBug = true;
                    break;
                }
            }
            System.out.println("does it have a bug ? " + hasBug);
        }
    }

}
