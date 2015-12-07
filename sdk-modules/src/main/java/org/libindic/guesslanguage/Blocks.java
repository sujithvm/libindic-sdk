package org.libindic.guesslanguage;

import android.content.Context;

import org.libindic.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sujith on 28/7/14.
 */
public class Blocks {

    private List<Integer> endpoints;
    private List<String> names;

    private void loadBlocks(Context context) {

        Pattern splitter = Pattern.compile("^(....)\\.\\.(....); (.*)$");
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            context.getResources().
                                    openRawResource(R.raw.silpa_sdk_guess_language_blocks)
                    )
            );
            String str = br.readLine();
            while (str != null) {
                Matcher mat = splitter.matcher(str);
                if (mat.find()) {
                    int start = Integer.parseInt(mat.group(1), 16);
                    int end = Integer.parseInt(mat.group(2), 16);
                    String name = mat.group(3);

                    endpoints.add(start);
                    endpoints.add(end);

                    names.add(name);
                    names.add(name);
                }
                str = br.readLine();
            }
        } catch (IOException ioe) {

        }
    }

    public Blocks(Context context) {
        this.endpoints = new ArrayList<>();
        this.names = new ArrayList<>();
        loadBlocks(context);
    }

    protected String unicodeBlock(char ch) {
        int ix = Collections.binarySearch(this.endpoints, (int) ch);
        if (ix < 0) {
            ix = -ix - 1;
        }
        return this.names.get(ix);
    }
}
