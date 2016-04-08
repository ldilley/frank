/*
 * Hearing.java - Fairly Rational Artificial Neural Kludge
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

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogManager;

class Hearing
{
  static LiveSpeechRecognizer recognizer = null;

  public Hearing()
  {
    try
    {
      // ToDo: Too much chatter with logging enabled. Optionally allow it later.
      Logger logger = LogManager.getLogManager().getLogger("");
      Handler[] handlers = logger.getHandlers();
      logger.setLevel(Level.OFF);

      //ConfigurationManager cm = new ConfigurationManager(url);
      Configuration config = new Configuration();
      config.setAcousticModelPath("resource:/lang/en-us");
      config.setDictionaryPath("resource:/lang/cmudict-en-us.dict");
      config.setLanguageModelPath("resource:/lang/en-us.lm.bin");
      // Comment the below two lines to enable free speech detection (warning: detection is rather poor)
      // Also set setUseGrammar() to false to enable free speech.
      config.setGrammarPath("resource:/lang");
      config.setGrammarName("default");
      config.setUseGrammar(true);
      recognizer = new LiveSpeechRecognizer(config);
    }
    catch(Exception e)
    {
      System.out.println("Unable to provide speech recognition: " + e);
      System.exit(1);
    }
  }

  public String hear()
  {
    recognizer.startRecognition(true);
    SpeechResult result;
    String output = "";
    while((result = recognizer.getResult()) != null)
    {
      output = result.getHypothesis();
      if(output != null && !output.isEmpty() && !output.equalsIgnoreCase("<unk>"))
        break;
    }
    recognizer.stopRecognition();
    return output;
  }
}