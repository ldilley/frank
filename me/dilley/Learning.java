/*
 * Learning.java - Fairly Rational Artificial Neural Kludge
 * Copyright (C) 2015, 2016 Lloyd Dilley
 * http://www.dilley.me/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/**
 * @author Lloyd Dilley
 */

package me.dilley;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.util.Scanner;
import org.jibble.jmegahal.JMegaHal;

class Learning
{
  static JMegaHal jmhal = null;
  static final String brainFile = "data/brain.dat";

  public Learning()
  {
    jmhal = new JMegaHal();
    // Reload brain from disk.
    try
    {
      FileInputStream fis = new FileInputStream(brainFile);
      ObjectInputStream ois = new ObjectInputStream(fis);
      Object object = ois.readObject();
      if(object instanceof JMegaHal)
        jmhal = (JMegaHal)object;
    }
    catch(ClassNotFoundException cnfe)
    {
      System.out.println("> Warning: Class not found: " + cnfe.toString());
    }
    catch(FileNotFoundException fnfe)
    {
      System.out.println("> Warning: Unable to open brain.dat: " + fnfe.toString());
    }
    catch(IOException ioe)
    {
      System.out.println("> Warning: Unable to open brain.dat: " + ioe.toString());
    }
  }

  // ToDo: Use PircBot for IRC integration.
  public static void chat(Speech speech)
  {
    System.out.println("/apps/chat> Type freely here or type \"quit\" to stop this chat session.");
    while(true)
    { 
      System.out.print("/apps/chat> You: ");
      Scanner scanner = new Scanner(System.in);
      String input = scanner.nextLine();
      if(input.equalsIgnoreCase("quit"))
      {
        // Save brain to disk.
        try
        {
          FileOutputStream fos = new FileOutputStream(brainFile);
          ObjectOutputStream oos = new ObjectOutputStream(fos);
          oos.writeObject(jmhal);
        }
        catch(FileNotFoundException fnfe)
        {
          System.out.println("> Warning: Unable to open brain.dat: " + fnfe.toString());
        }
        catch(IOException ioe)
        {
          System.out.println("> Warning: Unable to open brain.dat: " + ioe.toString());
        }
        speech.speak("I wish that I could say I thoroughly enjoyed this conversation.");
        return;
      }
      jmhal.add(input);
      String output = jmhal.getSentence(input);
      System.out.println("/apps/chat> FRANK: " + output);
      speech.speak(output);
    }
  }

  public void addText(String text)
  {
    jmhal.add(text);
  }

  public static String addTextAndReply(String text)
  {
    jmhal.add(text);
    return jmhal.getSentence(text);
  }
}