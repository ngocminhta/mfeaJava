package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BPPFileParser {
	public static int objects[];
	public static int c;
	
    public BPPFileParser(String fileName) throws BPPException {
        this.objects = parseFile(fileName);
    }

    public int[] getDim() {
        return this.objects;
    }
    
    public int[] initDataBPP() {
    	return this.objects;
    }
    
    public static int getCapacity() {
    	return c;
    }
    
    public static int[] addX(int arr[], int x)
    {
        int i;
        int newarr[] = new int[arr.length + 1];
        for (i = 0; i < arr.length; i++)
            newarr[i] = arr[i];
    
        newarr[arr.length] = x;
    
        return newarr;
    }
    
    private static int[] parseFile(String fileName) throws BPPException {
    	int objects[] = new int[0];
    	try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line;
            // read capacity
            line = in.readLine();
            c = Integer.parseInt(line);
            while ((line = in.readLine()) != null) {
                //strTok.nextToken(); // Discard the city number
                int d = Integer.parseInt(line);
                objects = addX(objects,d);
            }
    	}
    	catch (IOException e) {
            throw new BPPException("Could not read file " + "'" + fileName + "'!");
        }
    	
    return objects;
    }
}