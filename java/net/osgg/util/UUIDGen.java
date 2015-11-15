/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.util;

/*
 * Clase utilitaria usada para generar
 * UUIDs  acorde a la especificacion del DCE Universal Token Identifier
 * UUIDGen.java
 *
 * Created on 09.08.2003.
 *
 * Copyright (c) 2003 Johann Burkard (jb@eaio.com)
 *
 */

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This class contains methods that have been refactored out of UUID because of
 * the problems application servers have with Externalizable classes and static
 * fields.
 *
 * @author <a href="mailto:jb@eaio.com">Johann Burkard</a>
 * @version 1.0
 */
public final class UUIDGen {

 private UUIDGen() {}

 /**
  * The last time value. Used to remove duplicate UUIDs.
  */
 private static long lastTime = Long.MIN_VALUE;
 private static long clockAndNode = 0x8000000000000000L;

 static {

  // Skip the clock sequence generation process and use random instead.

  clockAndNode |= (long) (Math.random() * 0x3FFFFFFF) << 32;

  try {
   byte[] local = InetAddress.getLocalHost().getAddress();
   clockAndNode |= (local[0] << 24) & 0xFF000000L;
   clockAndNode |= (local[1] << 16) & 0xFF0000;
   clockAndNode |= (local[2] << 8) & 0xFF00;
   clockAndNode |= local[3] & 0xFF;
  }
  catch (UnknownHostException ex) {
   clockAndNode |= (long) (Math.random() * 0x3FFFFFFF);
  }
 }

 /**
  * Returns the current clockAndNode value.
  *
  * @return long
  */
 public static long clockAndNode() {
  return clockAndNode;
 }

 /**
  * Returns a new time field. Each time field is unique and larger
  * than the previously generated time field.
  *
  * @return long
  */
 public static long time() {

  long time;

  // UTC time

  long timeMillis = (System.currentTimeMillis() * 10000) + 0x01B21DD213814000L;

  if (timeMillis > lastTime) {
   lastTime = timeMillis;
  }
  else {
   timeMillis = ++lastTime;
  }

  // time low

  time = timeMillis << 32;

  // time mid

  time |= (timeMillis & 0xFFFF00000000L) >> 16;

  // time hi and version

  time |= 0x1000 | ((timeMillis >> 48) & 0x0FFF); // version 1

  return time;
 }

 private static UUID nilUUID = new UUID(0L, 0L);

 /**
  * Returns the nil UUID.
  * @return a UUID
  */
 public static UUID nilUUID() {
  return nilUUID;
 }

}

