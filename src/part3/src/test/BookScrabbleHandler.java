package test;
import java.io.*;
import java.util.Arrays;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler{
    DictionaryManager dm;
    PrintWriter out;
    Scanner in;
   @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient)
    {
        out = new PrintWriter(outToClient);
        in = new Scanner(inFromClient);
        dm = DictionaryManager.get();

        String[] inputC = in.next().split(",");
        boolean wordExists = false;

        if(inputC.equals('Q')) {
            wordExists = dm.query(Arrays.copyOfRange(inputC, 1, inputC.length));
        } else {
            wordExists = dm.challenge(Arrays.copyOfRange(inputC, 1, inputC.length));
        }

        out.println(wordExists ? "true" : "false");
        out.flush();
    }
    @Override
    public void close(){
        out.close();
        in.close();

    }


}


