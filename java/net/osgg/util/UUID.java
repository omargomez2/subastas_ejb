/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.util;

/*
 * Clase utilitaria usada para generar
 * UUIDs  acorde a la especificacion del DCE Universal Token Identifier
 * UUID.java
 *
 * Created 07.02.2003
 *
 * Copyright (c) 2003 Johann Burkard (jb@eaio.com)
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * Benchmark data for one million UUID creations (@2.4 GHz, Sun SDK 1.4.1_01).
 *
 * 1.1: ~4530 ms
 * 1.5: ~2050 ms
 */

/**
 * Creates UUIDs according to the DCE Universal Token Identifier specs. This
 * implementation differs from the specs in that it does not attempt to get the
 * MAC address of the network card. It uses the local network address instead,
 * together with 32 random bits. This implementation does not use Externalizable
 * because of problems with application servers (Sun One Application Server,
 * to name it).
 *
 * @see <a href="http://www.opengroup.org/onlinepubs/9629399/apdxa.htm">
 * http://www.opengroup.org/onlinepubs/9629399/apdxa.htm</a>
 * @see <a href="http://www.uddi.org/pubs/draft-leach-uuids-guids-01.txt">
 * http://www.uddi.org/pubs/draft-leach-uuids-guids-01.txt</a>
 * @author <a href="mailto:jb@eaio.de">Johann Burkard</a>
 * @version 1.6
 */
public class UUID implements Comparable, Serializable, Cloneable {

 static final long serialVersionUID = -978610497289878999L;

 public long time;
 public long clockAndNode;

 //public static UUID instance = new UUID();

 /**
  * Constructor for UUID.
  */


 public UUID() {
  time = UUIDGen.time();
  clockAndNode = UUIDGen.clockAndNode();
 }


 //public static UUID getUUID(){
 //  return instance;
 //}

 /**
  * Constructor for UUID.
  *
  * @param time the upper 64 bits
  * @param clockAndNode the lower 64 bits
  */
 public UUID(long time, long clockAndNode) {
  this.time = time;
  this.clockAndNode = clockAndNode;
 }

 /**
  * Compares this UUID to another Object.
  * @param other the other Object
  * @return int
  * @see java.lang.Comparable#compareTo(java.lang.Object)
  */
 public int compareTo(Object other) {
  if (other == null) {
   throw new NullPointerException(); /* 2DO */
  }
  if (!(other instanceof UUID)) {
   return 0;
  }

  UUID t = (UUID) other;
  if (time > t.time) {
   return 1;
  }
  if (time < t.time) {
   return -1;
  }
  if (clockAndNode > t.clockAndNode) {
   return 1;
  }
  if (clockAndNode < t.clockAndNode) {
   return -1;
  }
  return 0;
 }

 private void writeObject(ObjectOutputStream out) throws IOException {
  out.writeLong(time);
  out.writeLong(clockAndNode);
 }

 private void readObject(ObjectInputStream in)
  throws IOException, ClassNotFoundException {
  time = in.readLong();
  clockAndNode = in.readLong();
 }

 /**
  * Returns the UUID as a String.
  * @see java.lang.Object#toString()
  */
 public String toString() {
  return new StringBuffer(32)
   .append(Hex.asChars((int) (time >> 32)))
   //.append('-')
   .append(Hex.asChars((short) (time >> 16)))
   //.append('-')
   .append(Hex.asChars((short) time))
   //.append('-')
   .append(Hex.asChars((short) (clockAndNode >> 48)))
   //.append('-')
   .append(Hex.asChars(clockAndNode, 12))
   .toString();
 }

 /**
  * Returns a hash code of this UUID. The hash code is calculated by XOR'ing the
  * upper 32 bits of the time and clockAndNode fields and the lower 32 bits of
  * the time and clockAndNode fields.
  * @return the hash code
  * @see java.lang.Object#hashCode()
  */
 public final int hashCode() {
  return (int) ((time >> 32) ^ time ^ (clockAndNode >> 32) ^ clockAndNode);
 }

 /**
  * Clones this UUID.
  * @return a new UUID with same values of this UUID
  * @see java.lang.Object#clone()
  */
 public Object clone() {
  return new UUID(time, clockAndNode);
 }

// /**
//  * Prints another UUID to the console.
//  * @param args console arguments - ignored
//  */
// public static void main(String[] args) {
//  System.out.println(new UUID());
//  long then = System.currentTimeMillis();
//  for (int i = 0; i < 10000000; i++) {
//   new UUID();
//  }
//  System.out.println("took " + (System.currentTimeMillis() - then) + " ms");
// }

 /**
  * Returns the time field of the UUID (upper 64 bits).
  * @return the time field
  */
 public final long getTime() {
  return time;
 }

 /**
  * Returns the clock and node field of the UUID (lower 64 bits).
  * @return the clockAndNode field
  */
 public final long getClockAndNode() {
  return clockAndNode;
 }

 /**
  * Compares two objects for equality.
  * @see java.lang.Object#equals(Object)
  * @return boolean
  */
 public boolean equals(Object other) {
  if (this == other) {
   return true;
  }
  if (!(other instanceof UUID)) {
   return false;
  }
  UUID id = (UUID) other;
  return (clockAndNode == id.clockAndNode) && (time == id.time);
 }

}

