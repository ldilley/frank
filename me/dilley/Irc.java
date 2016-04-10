/*
 * Irc.java - Fairly Rational Artificial Neural Kludge
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

import java.io.InputStream;
import java.util.Properties;
import org.jibble.pircbot.PircBot;

class Irc extends PircBot
{
  static final String propFile = "cfg/irc.properties";
  String user, nick, gecos, channel, server, password;
  int port;
  Learning learningRef = null;

  public Irc(Learning learningRef)
  {
    // FixMe: Perform more safety checks (empty strings, etc.)
    try
    {
      this.learningRef = learningRef;
      Properties props = new Properties();
      InputStream is = getClass().getClassLoader().getResourceAsStream(propFile);
      if(is != null)
        props.load(is);
      else
        System.out.println("> Warning: Unable to load " + propFile + ".");
      user = props.getProperty("ident");
      nick = props.getProperty("nickname");
      gecos = props.getProperty("gecos");
      server = props.getProperty("server");
      port = Integer.parseInt(props.getProperty("port"));
      password = props.getProperty("password");
      channel = props.getProperty("channel");
      setVerbose(false);
      setLogin(user);
      setName(nick);
      setFinger(gecos);
      setVersion(gecos);
      connect(server, port, password);
      joinChannel(channel);
    }
    catch(Exception e)
    {
      System.out.println("> Warning: " + e.toString());
    }
  }

  public void onMessage(String channel, String sender, String login, String hostname, String message)
  {
    message = message.trim();
    if(message.toLowerCase().startsWith("!frank"))
    {
      message = message.substring(6).trim();
      sendMessage(channel, sender + ": " + learningRef.addTextAndReply(message));
    }
    else
      learningRef.addText(message);
  }

  public void quit()
  {
    disconnect();
  }
}