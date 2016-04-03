/*
 * Speech.java - Fairly Rational Artificial Neural Kludge
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

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.Random;
import javax.speech.Central;
import javax.speech.EngineException;

class Speech
{
  Voice voice = null;

  public Speech()
  {
    // Bypass need for speech.properties file / NullPointer exception
    try
    {
      System.setProperty("logLevel", "OFF"); // INFO or WARN are also valid
      System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
      System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
      // FixMe: Get Arctic voices working.
      //System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_slt_arctic.ArcticVoiceDirectory");
      Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
    }
    catch(EngineException e)
    {
      System.out.println("Unable to provide speech synthesis: " + e);
      System.exit(1);
    }
    voice = VoiceManager.getInstance().getVoice("kevin16");
    //voice = VoiceManager.getInstance().getVoice("slt_arctic");
    voice.allocate();
  }

  public void confirmation()
  {
    Random rand = new Random();
    int r = rand.nextInt(4);
    String[] confirmations = new String[4];
    confirmations[0] = "As you wish";
    confirmations[1] = "Acknowledged";
    confirmations[2] = "I'm on it";
    confirmations[3] = "Affirmative";
    voice.speak(confirmations[r]);
  }

  public void speak(String text)
  {
    voice.speak(text);
    // ToDo: Add ability to optionally output the speech.
    //System.out.println(text);
  }
}