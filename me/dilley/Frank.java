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

import java.util.Random;

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
    speech.speak("Ahh! My eyes!");
    //Learning learning = new Learning();
    //speech.speak("It is all coming back to me now.");
    System.out.println("> Memory subsystem temporarily offline.");
    speech.speak("I cannot seem to remember anything.");
    System.out.println("> FRANKOS startup complete. Transferring program control to user...");
    System.out.println("> Say \"help\" for a list of commands or \"exit\" to quit.");
    commandPrompt(hearing, speech);
  }

  public static void commandPrompt(Hearing hearing, Speech speech)
  {
    while(true)
    {
      System.out.print("> ");
      String input = hearing.hear();
      System.out.println(input);
      switch(input.toLowerCase())
      {
        case "chat":
          System.out.println("> Temporarily offline.");
          break;
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
        case "rock paper scissors":
          speech.confirmation();
          playRPS(hearing, speech);
          break;
        default:
          System.out.println("> Invalid input.");
          break;
      }
    }
  }

  public static void displayHelp()
  {
    System.out.println("chat:                Initiate a chat session.");
    System.out.println("exit:                Exit the program.");
    System.out.println("help:                Display this helpful message.");
    System.out.println("quit:                Quit the program.");
    System.out.println("rock paper scissors: Play rock, paper, scissors.");
  }

  public static void quit(Speech speech)
  {
    speech.speak("Wait. What? No!");
    System.out.println("> FRANKOS reluctantly shutting down...\n*Blip*");
    System.exit(0);
  }

  public enum Weapon { ROCK, PAPER, SCISSORS };

  public static boolean checkWinner(Weapon frank, Weapon user)
  {
    boolean isUserWinner; // false = frank wins, true = user wins
    if(frank == Weapon.ROCK && user == Weapon.PAPER)
      isUserWinner = true;
    else if(frank == Weapon.PAPER && user == Weapon.ROCK)
      isUserWinner = false;
    else if(frank == Weapon.ROCK && user == Weapon.SCISSORS)
      isUserWinner = false;
    else if(frank == Weapon.SCISSORS && user == Weapon.ROCK)
      isUserWinner = true;
    else if(frank == Weapon.PAPER && user == Weapon.SCISSORS)
      isUserWinner = true;
    else
      isUserWinner = false; // frank has scissors and user has paper
    return isUserWinner;
  }

  // ToDo: Randomize taunts from a pool later.
  public static void playRPS(Hearing hearing, Speech speech)
  {
    System.out.println("> Say \"rock\", \"paper\", \"scissors\", \"help\", or \"quit\".");
    while(true)
    {
      Random rand = new Random();
      int r = rand.nextInt(3);
      Weapon weapon = Weapon.values()[r];
      System.out.print("> ");
      String input = hearing.hear();
      System.out.println("You chose: " + input);
      switch(input.toLowerCase())
      {
        case "help":
          speech.confirmation();
          System.out.println("help:     Display this helpful message.");
          System.out.println("quit:     Quit the game.");
          System.out.println("paper:    Choose this to cover rock... or get cut by scissors.");
          System.out.println("rock:     Choose this to smash scissors... or get covered by paper.");
          System.out.println("scissors: Choose this to cut paper... or get smashed by rock.");
          break;
        case "quit":
          speech.speak("Had enough eh?");
          return;
        case "paper":
          System.out.println("> System selects " + weapon + ".");
          speech.speak("I choose " + weapon + "!");
          if(weapon == Weapon.PAPER)
          {
            System.out.println("> Stalemate detected.");
            speech.speak("A tie. How dull.");
          }
          else
          {
            if(checkWinner(weapon, Weapon.PAPER))
            {
              System.out.println("> System loses.");
              speech.speak("I am not amused.");
            }
            else
            {
              System.out.println("> System wins.");
              speech.speak("I am not surprised.");
            }
          }
          break;
        case "rock":
          System.out.println("> System selects " + weapon + ".");
          speech.speak("I choose " + weapon + "!");
          if(weapon == Weapon.ROCK)
          {
            System.out.println("> Stalemate detected.");
            speech.speak("A tie. How dull.");
          }
          else
          {
            if(checkWinner(weapon, Weapon.ROCK))
            {
              System.out.println("> System loses.");
              speech.speak("You actually find this game amusing?");
            }
            else
            {
              System.out.println("> System wins.");
              speech.speak("I am yawning right now.");
            }
          }
          break;
        case "scissors":
          System.out.println("> System selects " + weapon + ".");
          speech.speak("I choose " + weapon + "!");
          if(weapon == Weapon.SCISSORS)
          {
            System.out.println("> Stalemate detected.");
            speech.speak("A tie. How dull.");
          }
          else
          {
            if(checkWinner(weapon, Weapon.SCISSORS))
            {
              System.out.println("> System loses.");
              speech.speak("I blame the programmer for not using the pseudo-random number generator builtin to FRANKOS.");
            }
            else
            {
              System.out.println("> System wins.");
              speech.speak("I doubt you'll ever amount to anything.");
            }
          }
          break;
        default:
          System.out.println("> Invalid input.");
          break;
      }
    }
  }
}