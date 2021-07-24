import java.util.ArrayList;
import java.util.Arrays;

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
                }
                i++;
                j++;
            }
        }
        return patternOccurrences;
    }

    public static void main(String[] args) {
        
    }

}
