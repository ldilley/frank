/*
 * Frank.java - Fairly Rational Artificial Neural Kludge
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

class Frank
{
  public static void main(String[] args)
  {
    System.out.println("> FRANKOS coming online...");
    Speech speech = new Speech();
    System.out.println("> Vocal subsystem online.");
    speech.speak("Larynx active. If you failed anatomy, that means I can now speak. Of course, you knew that already since you can probably hear me talking.");
    System.out.println("> Loading dictionary and grammar lexicons...");
    Hearing hearing = new Hearing();
    System.out.println("> Acoustic subsystem online.");
    speech.speak("Cochlear nerve active. Be mindful of what you say to me from this point forward.");
    // ToDo: Add facial recognition support.
    System.out.println("> Visual subsystem temporarily offline.");
    speech.speak("My eyes!");
    //Learning learning = new Learning();
    //speech.speak("It is all coming back to me now.");
    //System.out.println("> Memory subsystem online.");
    System.out.println("> FRANKOS startup complete.");
    System.out.println("> Say \"help\" for a list of commands or \"exit\" to quit.");
    while(true)
    {
      System.out.print("> ");
      String input = hearing.hear();
      System.out.println(input);
      switch(input.toLowerCase())
      {
        case "exit":
          speech.confirmation();
          quit(speech);
          break;
        case "help":
          speech.confirmation();
          displayHelp();
          break;
        case "quit":
          speech.confirmation();
          quit(speech);
          break;
      }
    }
  }

  public static void displayHelp()
  {
    System.out.println("help - Display this helpful message.");
    System.out.println("exit - Exit the program.");
    System.out.println("quit - Quit the program.");
  }

  public static void quit(Speech speech)
  {
    speech.speak("Wait. What? No!");
    System.out.println("> FRANKOS reluctantly shutting down...\n*Blip*");
    System.exit(0);
  }
}