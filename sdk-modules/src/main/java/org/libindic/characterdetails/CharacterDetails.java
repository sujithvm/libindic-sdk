package org.libindic.characterdetails;

import android.content.Context;
import android.util.Log;

import org.libindic.R;

import java.io.DataInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.InflaterInputStream;

/**
 * Created by sujith on 24/5/14.
 */
public class CharacterDetails {

    /**
     * Context of application
     */
    private Context mContext;

    public static final String MODULE_NAME = "Character Details";
    public static final String MODULE_INFORMATION = "Shows the Unicode Character " +
            "Details of a given character";

    private static final String LOG_TAG = CharacterDetails.MODULE_NAME;


    /**
     * Constructor
     *
     * @param context context
     */
    public CharacterDetails(Context context) {
        this.mContext = context;
    }

    /**
     * Get character details of each character in string
     *
     * @param text string
     * @return map
     */
    private Map<Character, CharacterDetailsObject> getDetails(String text) {
        Map<Character, CharacterDetailsObject> map = new HashMap<Character, CharacterDetailsObject>();

        for (char ch : text.toCharArray()) {

            boolean isDigit = Character.isDigit(ch);
            boolean isAlphabet = Character.isLetter(ch);
            boolean isAlphaNumeric = Character.isLetterOrDigit(ch);

            String codePoint = Integer.toHexString(ch).toUpperCase(Locale.getDefault());
            while (codePoint.length() != 4) codePoint = "0" + codePoint;

            String name = getCharacterName(ch);

            String canonicalDecomposition = Normalizer.normalize(ch + "", Normalizer.Form.NFD);

            CharacterDetailsObject obj = new CharacterDetailsObject(isDigit, isAlphabet, isAlphaNumeric,
                    (int) ch,
                    name, "\\u" + codePoint, canonicalDecomposition);
            map.put(ch, obj);
        }
        return map;
    }

    /**
     * Get character details of a single character
     *
     * @param ch character
     * @return CharacterDetailsObject
     */
    public CharacterDetailsObject getCharacterDetails(char ch) {
        return getDetails(ch + "").get(ch);
    }

    /**
     * Get character details of each character as a map.
     *
     * @param text string
     * @return map
     */
    public Map<Character, CharacterDetailsObject> getCharacterDetailsAsMap(String text) {
        return getDetails(text);
    }

    /**
     * Get character details of each character in an array.
     *
     * @param text string
     * @return CharacterDetailsObject array
     */
    public CharacterDetailsObject[] getCharacterDetailsAsArray(String text) {
        Map<Character, CharacterDetailsObject> map = getDetails(text);

        CharacterDetailsObject[] arr = new CharacterDetailsObject[text.length()];
        for (int i = 0; i < text.length(); i++) {
            arr[i] = map.get(text.charAt(i));
        }
        return arr;
    }

    /**
     * This function gives name of the module
     *
     * @return name of module
     */
    public String getModuleName() {
        return CharacterDetails.MODULE_NAME;
    }

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    public String getModuleInformation() {
        return CharacterDetails.MODULE_INFORMATION;
    }


 /*
 * Copyright (c) 2010, 2011, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

    private SoftReference<byte[]> refStrPool;
    private int[][] lookup;

    private synchronized byte[] initNamePool() {
        byte[] strPool = null;
        if (refStrPool != null && (strPool = refStrPool.get()) != null)
            return strPool;
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new InflaterInputStream(
                    AccessController.doPrivileged(new PrivilegedAction<InputStream>() {
                        public InputStream run() {
                            // return getClass().getResourceAsStream("uniName.dat");
                            return mContext.getResources().openRawResource(R.raw.silpa_sdk_unicode_data);
                        }
                    })
            ));

            lookup = new int[(Character.MAX_CODE_POINT + 1) >> 8][];
            int total = dis.readInt();
            int cpEnd = dis.readInt();
            byte ba[] = new byte[cpEnd];
            dis.readFully(ba);

            int nameOff = 0;
            int cpOff = 0;
            int cp = 0;
            do {
                int len = ba[cpOff++] & 0xff;
                if (len == 0) {
                    len = ba[cpOff++] & 0xff;
                    // always big-endian
                    cp = ((ba[cpOff++] & 0xff) << 16) |
                            ((ba[cpOff++] & 0xff) << 8) |
                            ((ba[cpOff++] & 0xff));
                } else {
                    cp++;
                }
                int hi = cp >> 8;
                if (lookup[hi] == null) {
                    lookup[hi] = new int[0x100];
                }
                lookup[hi][cp & 0xff] = (nameOff << 8) | len;
                nameOff += len;
            } while (cpOff < cpEnd);
            strPool = new byte[total - cpEnd];
            dis.readFully(strPool);
            refStrPool = new SoftReference<>(strPool);
        } catch (Exception x) {
            Log.e(LOG_TAG, "Error : " + x.getMessage());
        } finally {
            try {
                if (dis != null)
                    dis.close();
            } catch (Exception xx) {
            }
        }
        return strPool;
    }

    private String getCharacterName(int cp) {
        byte[] strPool = null;
        if (refStrPool == null || (strPool = refStrPool.get()) == null)
            strPool = initNamePool();
        int off = 0;
        if (lookup[cp >> 8] == null ||
                (off = lookup[cp >> 8][cp & 0xff]) == 0)
            return null;
        @SuppressWarnings("deprecation")
        String result = new String(strPool, 0, off >>> 8, off & 0xff);  // ASCII
        return result;
    }
}
