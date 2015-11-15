/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.osgg.util;

/*
 * Clase utilitaria usada para generar
 * UUIDs  acorde a la especificacion del DCE Universal Token Identifier
 * Hex.java
 *
 * Created on 04.07.2003.
 *
 * Copyright (c) 2003 Johann Burkard (jb@eaio.com)
 *
 */

/**
 * A utility class for number-to-hexadecimal and hexadecimal-to-number
 * conversions.
 * @author <a href="mailto:jb@eaio.com">Johann Burkard</a>
 * @version 1.5
 */
public final class Hex {

 /**
  * No instances needed.
  */
 private Hex() {}

 private static final char[] DIGITS =
  {
   '0',
   '1',
   '2',
   '3',
   '4',
   '5',
   '6',
   '7',
   '8',
   '9',
   'a',
   'b',
   'c',
   'd',
   'e',
   'f' };

 /**
  * Transform a byte to a character array of hex octets.
  *
  * @param byte the byte
  * @return char[] the hex byte array
  */
 public static char[] asChars(byte in) {
  return asChars(in, 2);
 }

 /**
  * Transform a byte to a character array of hex octets.
  *
  * @param byte the byte
  * @param length the number of octets to produce
  * @return char[]
  */
 public static char[] asChars(byte in, int length) {
  char[] out = new char[length--];
  for (int i = length; i > -1; i--) {
   out[i] = DIGITS[(byte) (in & 0x0F)];
   in >>= 4;
  }
  return out;
 }

 /**
  * Transform a long to a character array of hex octets.
  *
  * @param int the integer
  * @return char[] the hex byte array
  */
 public static char[] asChars(int in) {
  return asChars(in, 8);
 }

 /**
  * Transform an integer to a character array of hex octets.
  *
  * @param integer the integer
  * @param int the number of octets to produce
  * @return char[]
  */
 public static char[] asChars(int in, int length) {
  char[] out = new char[length--];
  for (int i = length; i > -1; i--) {
   out[i] = DIGITS[(byte) (in & 0x0F)];
   in >>= 4;
  }
  return out;
 }

 /**
  * Transform a long to a character array of hex octets.
  *
  * @param long the long
  * @return char[] the hex byte array
  */
 public static char[] asChars(long in) {
  return asChars(in, 16);
 }

 /**
  * Transform a long to a character array of hex octets.
  *
  * @param long the long
  * @param int the number of octets to produce
  * @return char[] the hex byte array
  */
 public static char[] asChars(long in, int length) {
  char[] out = new char[length--];
  for (int i = length; i > -1; i--) {
   out[i] = DIGITS[(byte) (in & 0x0F)];
   in >>= 4;
  }
  return out;
 }

 /**
  * Transform a short to a character array of hex octets.
  *
  * @param short the integer
  * @return char[] the hex byte array
  */
 public static char[] asChars(short in) {
  return asChars(in, 4);
 }

 /**
  * Transform a short to a character array of hex octets.
  *
  * @param short the integer
  * @param int the number of octets to produce
  * @return char[] the hex byte array
  */
 public static char[] asChars(short in, int length) {
  char[] out = new char[length--];
  for (int i = length; i > -1; i--) {
   out[i] = DIGITS[(byte) (in & 0x0F)];
   in >>= 4;
  }
  return out;
 }

 /**
  * Transform an array of bytes into a character array of hex octets.
  * @param b
  * @return char[]
  */
 public static char[] asChars(byte[] b) {
  int len = b.length << 1;
  char[] out = new char[len--];
  for (int i = b.length - 1; i > -1; i--) {
   out[len--] = DIGITS[(byte) (b[i] & 0x0F)];
   out[len--] = DIGITS[(byte) (b[i] & 0xF0) >> 4];
  }
  return out;
 }

}

