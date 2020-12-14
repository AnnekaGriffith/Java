import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    private static void OnesInRow(int n)
    {
        String b = Integer.toBinaryString(n);
        String[] strparts = b.split("0");
        int count = 0; 
        for (int i =0; i < strparts.length; i++)
        {
            if (strparts[i].length()> count)
            {
                count = strparts[i].length();
            }
        }
        System.out.println(count); 
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        OnesInRow(n);
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        scanner.close();
    }
}
