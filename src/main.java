
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/*
 * Copyright (C) 2014 Tim Tepatti
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * --------------------
 * Author: Tim Tepatti
 * --------------------
 * I really don't care what you do with this, I made it for fun.
 * Feel free to modify/redistribute/everything GPLv3 says.
*/

import java.util.Random;

public class main {
    public static void main(String args[]) throws IOException {
        ArrayList<String> nouns = new ArrayList<String>();
        ArrayList<String> verbs = new ArrayList<String>();
        ArrayList<String> adjectives = new ArrayList<String>();
        ArrayList<String> phrases = new ArrayList<String>();
        String line = null;
        
        try {
            //This reads each of the files: nouns.txt, verbs.txt, adjectives.txt, and phrases.txt
            //and stores them each in their own array for later use.
            BufferedReader nounsReader = new BufferedReader(new FileReader("nouns.txt"));
            while ((line = nounsReader.readLine()) != null) {
                nouns.add(line);
            }
            BufferedReader verbsReader = new BufferedReader(new FileReader("verbs.txt"));
            while ((line = verbsReader.readLine()) != null) {
                verbs.add(line);
            }
            BufferedReader adjectivesReader = new BufferedReader(new FileReader("adjectives.txt"));
            while ((line = adjectivesReader.readLine()) != null) {
                adjectives.add(line);
            }
            BufferedReader phrasesReader = new BufferedReader(new FileReader("phrases.txt"));
            while ((line = phrasesReader.readLine()) != null) {
                phrases.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Create a new random number generator
        Random rand = new Random();
        int[] randoms = new int[10];
        
        //Random number generator to see if it should send a pre-written phrase
        int qPhrase = rand.nextInt(100) + 1;
        //If it's greater than 90, send a pre-written phrase
        if (qPhrase > 90) {
            //Set random for phrases to between 1 and max line number
            randoms[3] = rand.nextInt(countLines("phrases.txt")) + 1;
            //Set the actual phrase string
            String phrase = phrases.get(randoms[3]);
        } else {
            //Generate a random phrase using the nouns, verbs, and adjectives
            //  v-- This is all to pick a random noun/verb/adjective --v
            //Set random for nouns to between 1 and max line number
            randoms[0] = rand.nextInt(countLines("nouns.txt")) + 1;
            //Set random for verbs to between 1 and max line number
            randoms[1] = rand.nextInt(countLines("verbs.txt")) + 1;
            //Set random for adjectives to between 1 and max line number
            randoms[2] = rand.nextInt(countLines("adjectives.txt")) + 1;
            
            //Set the actual noun string
            String noun = nouns.get(randoms[0]);
            //Set the actual verb string
            String verb = verbs.get(randoms[1]);
            //Set the actual adjective string
            String adjective = adjectives.get(randoms[2]);
        }
        
    }
    
    //countLines function, used for finding the number of lines in a file
    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
}