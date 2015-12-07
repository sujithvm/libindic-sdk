package org.libindic.guesslanguage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.libindic.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.Normalizer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sujith on 28/7/14.
 */
public class GuessLanguage {

    private static final int MIN_LENGTH = 20;
    private static final String UNKNOWN = "UNKNOWN";
    private static final int UNKNOWN_ID = -1;

    private static final List<String> BASIC_LATIN = new ArrayList<>(Arrays.asList("en ceb ha so tlh id haw la sw eu nr nso zu xh ss st tn ts".split(" ")));
    private static final List<String> EXTENDED_LATIN = new ArrayList<>(Arrays.asList("cs af pl hr ro sk sl tr hu az et sq ca es fr de nl it da is nb sv fi lv pt ve lt tl cy".split(" ")));
    private static final List<String> ALL_LATIN = new ArrayList<>();

    static {
        ALL_LATIN.addAll(BASIC_LATIN);
        ALL_LATIN.addAll(EXTENDED_LATIN);
    }

    private static final List<String> CYRILLIC = new ArrayList<>(Arrays.asList("ru uk kk uz mn sr mk bg ky".split(" ")));
    private static final List<String> ARABIC = new ArrayList<>(Arrays.asList("ar fa ps ur".split(" ")));
    private static final List<String> DEVANAGARI = new ArrayList<>(Arrays.asList("hi ne".split(" ")));
    private static final List<String> SINGLETONS = new ArrayList<>(Arrays.asList("Armenian hy Hebrew he Bengali bn Gurmukhi pa Greek el Gujarati gu Oriya or Tamil ta Telugu te Kannada kn Malayalam ml Sinhala si Thai th Lao lo Tibetan bo Burmese my Georgian ka Mongolian mn-Mong Khmer km".split(" ")));
    private static final List<String> PT = new ArrayList<>(Arrays.asList("pt_BR pt_PT".split(" ")));

    private static final Map<String, String> NAME_MAP = new HashMap<>();

    static {
        NAME_MAP.put("ab", "Abkhazian");
        NAME_MAP.put("af", "Afrikaans");
        NAME_MAP.put("ar", "Arabic");
        NAME_MAP.put("az", "Azeri");
        NAME_MAP.put("be", "Byelorussian");
        NAME_MAP.put("bg", "Bulgarian");
        NAME_MAP.put("bn", "Bengali");
        NAME_MAP.put("bo", "Tibetan");
        NAME_MAP.put("br", "Breton");
        NAME_MAP.put("ca", "Catalan");
        NAME_MAP.put("ceb", "Cebuano");
        NAME_MAP.put("cs", "Czech");
        NAME_MAP.put("cy", "Welsh");
        NAME_MAP.put("da", "Danish");
        NAME_MAP.put("de", "German");
        NAME_MAP.put("el", "Greek");
        NAME_MAP.put("en", "English");
        NAME_MAP.put("eo", "Esperanto");
        NAME_MAP.put("es", "Spanish");
        NAME_MAP.put("et", "Estonian");
        NAME_MAP.put("eu", "Basque");
        NAME_MAP.put("fa", "Farsi");
        NAME_MAP.put("fi", "Finnish");
        NAME_MAP.put("fo", "Faroese");
        NAME_MAP.put("fr", "French");
        NAME_MAP.put("fy", "Frisian");
        NAME_MAP.put("gd", "Scots Gaelic");
        NAME_MAP.put("gl", "Galician");
        NAME_MAP.put("gu", "Gujarati");
        NAME_MAP.put("ha", "Hausa");
        NAME_MAP.put("haw", "Hawaiian");
        NAME_MAP.put("he", "Hebrew");
        NAME_MAP.put("hi", "Hindi");
        NAME_MAP.put("hr", "Croatian");
        NAME_MAP.put("hu", "Hungarian");
        NAME_MAP.put("hy", "Armenian");
        NAME_MAP.put("id", "Indonesian");
        NAME_MAP.put("is", "Icelandic");
        NAME_MAP.put("it", "Italian");
        NAME_MAP.put("ja", "Japanese");
        NAME_MAP.put("ka", "Georgian");
        NAME_MAP.put("kk", "Kazakh");
        NAME_MAP.put("km", "Cambodian");
        NAME_MAP.put("ko", "Korean");
        NAME_MAP.put("ku", "Kurdish");
        NAME_MAP.put("ky", "Kyrgyz");
        NAME_MAP.put("la", "Latin");
        NAME_MAP.put("lt", "Lithuanian");
        NAME_MAP.put("lv", "Latvian");
        NAME_MAP.put("mg", "Malagasy");
        NAME_MAP.put("mk", "Macedonian");
        NAME_MAP.put("ml", "Malayalam");
        NAME_MAP.put("mn", "Mongolian");
        NAME_MAP.put("mr", "Marathi");
        NAME_MAP.put("ms", "Malay");
        NAME_MAP.put("nd", "Ndebele");
        NAME_MAP.put("ne", "Nepali");
        NAME_MAP.put("nl", "Dutch");
        NAME_MAP.put("nn", "Nynorsk");
        NAME_MAP.put("no", "Norwegian");
        NAME_MAP.put("nso", "Sepedi");
        NAME_MAP.put("pa", "Punjabi");
        NAME_MAP.put("pl", "Polish");
        NAME_MAP.put("ps", "Pashto");
        NAME_MAP.put("pt", "Portuguese");
        NAME_MAP.put("ro", "Romanian");
        NAME_MAP.put("ru", "Russian");
        NAME_MAP.put("sa", "Sanskrit");
        NAME_MAP.put("sh", "Serbo-Croatian");
        NAME_MAP.put("sk", "Slovak");
        NAME_MAP.put("sl", "Slovene");
        NAME_MAP.put("so", "Somali");
        NAME_MAP.put("sq", "Albanian");
        NAME_MAP.put("sr", "Serbian");
        NAME_MAP.put("sv", "Swedish");
        NAME_MAP.put("sw", "Swahili");
        NAME_MAP.put("ta", "Tamil");
        NAME_MAP.put("te", "Telugu");
        NAME_MAP.put("th", "Thai");
        NAME_MAP.put("tl", "Tagalog");
        NAME_MAP.put("tlh", "Klingon");
        NAME_MAP.put("tn", "Setswana");
        NAME_MAP.put("tr", "Turkish");
        NAME_MAP.put("ts", "Tsonga");
        NAME_MAP.put("tw", "Twi");
        NAME_MAP.put("uk", "Ukrainian");
        NAME_MAP.put("uk", "Ukranian");
        NAME_MAP.put("ur", "Urdu");
        NAME_MAP.put("uz", "Uzbek");
        NAME_MAP.put("ve", "Venda");
        NAME_MAP.put("vi", "Vietnamese");
        NAME_MAP.put("xh", "Xhosa");
        NAME_MAP.put("zh", "Chinese");
        NAME_MAP.put("zh-tw", "Traditional Chinese (Taiwan)");
        NAME_MAP.put("zu", "Zulu");

    }

    private static final Map<String, Integer> IANA_MAP = new HashMap<>();

    static {
        IANA_MAP.put("ab", 12026);
        IANA_MAP.put("af", 40);
        IANA_MAP.put("ar", 26020);
        IANA_MAP.put("az", 26030);
        IANA_MAP.put("be", 11890);
        IANA_MAP.put("bg", 26050);
        IANA_MAP.put("bn", 26040);
        IANA_MAP.put("bo", 26601);
        IANA_MAP.put("br", 1361);
        IANA_MAP.put("ca", 3);
        IANA_MAP.put("ceb", 26060);
        IANA_MAP.put("cs", 26080);
        IANA_MAP.put("cy", 26560);
        IANA_MAP.put("da", 26090);
        IANA_MAP.put("de", 26160);
        IANA_MAP.put("el", 26165);
        IANA_MAP.put("en", 26110);
        IANA_MAP.put("eo", 11933);
        IANA_MAP.put("es", 26460);
        IANA_MAP.put("et", 26120);
        IANA_MAP.put("eu", 1232);
        IANA_MAP.put("fa", 26130);
        IANA_MAP.put("fi", 26140);
        IANA_MAP.put("fo", 11817);
        IANA_MAP.put("fr", 26150);
        IANA_MAP.put("fy", 1353);
        IANA_MAP.put("gd", 65555);
        IANA_MAP.put("gl", 1252);
        IANA_MAP.put("gu", 26599);
        IANA_MAP.put("ha", 26170);
        IANA_MAP.put("haw", 26180);
        IANA_MAP.put("he", 26592);
        IANA_MAP.put("hi", 26190);
        IANA_MAP.put("hr", 26070);
        IANA_MAP.put("hu", 26200);
        IANA_MAP.put("hy", 26597);
        IANA_MAP.put("id", 26220);
        IANA_MAP.put("is", 26210);
        IANA_MAP.put("it", 26230);
        IANA_MAP.put("ja", 26235);
        IANA_MAP.put("ka", 26600);
        IANA_MAP.put("kk", 26240);
        IANA_MAP.put("km", 1222);
        IANA_MAP.put("ko", 26255);
        IANA_MAP.put("ku", 11815);
        IANA_MAP.put("ky", 26260);
        IANA_MAP.put("la", 26280);
        IANA_MAP.put("lt", 26300);
        IANA_MAP.put("lv", 26290);
        IANA_MAP.put("mg", 1362);
        IANA_MAP.put("mk", 26310);
        IANA_MAP.put("ml", 26598);
        IANA_MAP.put("mn", 26320);
        IANA_MAP.put("mr", 1201);
        IANA_MAP.put("ms", 1147);
        IANA_MAP.put("ne", 26330);
        IANA_MAP.put("nl", 26100);
        IANA_MAP.put("nn", 172);
        IANA_MAP.put("no", 26340);
        IANA_MAP.put("pa", 65550);
        IANA_MAP.put("pl", 26380);
        IANA_MAP.put("ps", 26350);
        IANA_MAP.put("pt", 26390);
        IANA_MAP.put("ro", 26400);
        IANA_MAP.put("ru", 26410);
        IANA_MAP.put("sa", 1500);
        IANA_MAP.put("sh", 1399);
        IANA_MAP.put("sk", 26430);
        IANA_MAP.put("sl", 26440);
        IANA_MAP.put("so", 26450);
        IANA_MAP.put("sq", 26010);
        IANA_MAP.put("sr", 26420);
        IANA_MAP.put("sv", 26480);
        IANA_MAP.put("sw", 26470);
        IANA_MAP.put("ta", 26595);
        IANA_MAP.put("te", 26596);
        IANA_MAP.put("th", 26594);
        IANA_MAP.put("tl", 26490);
        IANA_MAP.put("tlh", 26250);
        IANA_MAP.put("tn", 65578);
        IANA_MAP.put("tr", 26500);
        IANA_MAP.put("tw", 1499);
        IANA_MAP.put("uk", 26510);
        IANA_MAP.put("uk", 26520);
        IANA_MAP.put("ur", 26530);
        IANA_MAP.put("uz", 26540);
        IANA_MAP.put("vi", 26550);
        IANA_MAP.put("zh", 26065);
        IANA_MAP.put("zh-tw", 22);
    }

    private Map<String, Map<String, Integer>> models;

    private Context mContext;

    private static final String LOG_TAG = "Guess Language";

    private Blocks blocks;

    public GuessLanguage(Context context) {
        this.mContext = context;
        this.blocks = new Blocks(context);
        loadModels();
    }

    public String guessLanguage(String text) {
        if (text == null || text.length() == 0) {
            return UNKNOWN;
        }
        text = normalize(text);

        return identify(text, findRuns(text));
    }

    public String guessLanguageTag(String text) {
        return guessLanguage(text);
    }

    public LanguageInfo guessLanguageInfo(String text) {
        String tag = guessLanguage(text);
        if (tag.equals(UNKNOWN)) {
            return new LanguageInfo(UNKNOWN, UNKNOWN_ID, UNKNOWN);
        }
        return new LanguageInfo(tag, getId(tag), getName(tag));
    }

    public int getId(String iana) {
        return IANA_MAP.get(iana) != null ? IANA_MAP.get(iana) : UNKNOWN_ID;
    }

    public String getName(String iana) {
        return NAME_MAP.get(iana) != null ? NAME_MAP.get(iana) : UNKNOWN;
    }

    public int guessLanguageId(String text) {
        String lang = guessLanguage(text);
        return getId(lang);
    }

    public String guessLanguageName(String text) {
        String lang = guessLanguage(text);
        return getName(lang);
    }

    private void loadModels() {
        new LoadModels().execute();
    }

    private List<String> findRuns(String text) {
        Map<String, Integer> runTypes = new HashMap<>();
        int totalCount = 0;
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                String block = blocks.unicodeBlock(c);
                if (runTypes.get(block) == null) {
                    runTypes.put(block, 0);
                }
                runTypes.put(block, runTypes.get(block) + 1);
                totalCount += 1;
            }
        }
        List<String> relevantRuns = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : runTypes.entrySet()) {
            String key = entry.getKey();
            int value = runTypes.get(key);

            int pct = (value * 100) / totalCount;
            if (pct >= 40) {
                relevantRuns.add(key);
            } else if (key.equals("Basic Latin") && (pct >= 15)) {
                relevantRuns.add(key);
            } else if (key.equals("Latin Extended Additional") && (pct >= 10)) {
                relevantRuns.add(key);
            }
        }
        return relevantRuns;
    }

    private String identify(String sample, List<String> scripts) {
        if (sample.length() < 3) {
            return UNKNOWN;
        }

        if (scripts.contains("Hangul Syllables") || scripts.contains("Hangul Jamo")
                || scripts.contains("Hangul Compatibility Jamo") || scripts.contains("Hangul")) {
            return "ko";
        }

        if (scripts.contains("Greek and Coptic")) {
            return "el";
        }

        if (scripts.contains("Katakana")) {
            return "ja";
        }

        if (scripts.contains("CJK Unified Ideographs") || scripts.contains("Bopomofo")
                || scripts.contains("Bopomofo Extended") || scripts.contains("KangXi Radicals")) {
            return "zh";
        }

        if (scripts.contains("Cyrillic")) {
            return check(sample, CYRILLIC);
        }

        if (scripts.contains("Arabic") || scripts.contains("Arabic Presentation Forms-A") || scripts.contains("Arabic Presentation Forms-B")) {
            return check(sample, ARABIC);
        }

        if (scripts.contains("Devanagari")) {
            return check(sample, DEVANAGARI);
        }

        // Try languages with unique scripts
        for (int i = 0; i < SINGLETONS.size(); i += 2) {
            if (scripts.contains(SINGLETONS.get(i))) {
                return SINGLETONS.get(i + 1);
            }
        }

        if (scripts.contains("Latin Extended Additional")) {
            return "vi";
        }

        if (scripts.contains("Extended Latin")) {
            String latinLang = check(sample, EXTENDED_LATIN);
            if (latinLang.equals("pt")) {
                return check(sample, PT);
            } else {
                return latinLang;
            }
        }

        if (scripts.contains("Basic Latin")) {
            return check(sample, ALL_LATIN);
        }

        return UNKNOWN;
    }

    private String check(String sample, List<String> langs) {

        if (sample.length() < MIN_LENGTH) {
            return UNKNOWN;
        }
        List<Map.Entry<Integer, String>> scores = new ArrayList<>();
        List<String> model = createOrderedModel(sample);

        for (String key : langs) {
            String lkey = key.toLowerCase(Locale.getDefault());

            if (models.get(lkey) != null) {
                scores.add(new AbstractMap.SimpleEntry<>(distance(model, models.get(lkey)), key));
            }
        }

        if (scores.size() == 0) {
            return UNKNOWN;
        }

        Map.Entry<Integer, String> min = scores.get(0);
        for (int i = 1; i < scores.size(); i++) {
            if (scores.get(i).getKey() < min.getKey()) {
                min = scores.get(i);
            }
        }

        return min.getValue();
    }

    public List<String> createOrderedModel(String content) {
        Map<String, Integer> trigrams = new HashMap<>();

        content = content.toLowerCase(Locale.getDefault());

        for (int i = 0; i < (content.length() - 2); ++i) {
            String tri = content.substring(i, i + 3);

            if (trigrams.get(tri) == null) {
                trigrams.put(tri, 0);
            }
            trigrams.put(tri, trigrams.get(tri) + 1);
        }

        List<Map.Entry<String, Integer>> sortedEntries =
                new ArrayList<Map.Entry<String, Integer>>(trigrams.entrySet());

        Collections.sort(sortedEntries,
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        int cmp1 = e2.getValue().compareTo(e1.getValue()); // values - descending
                        int cmp2 = e1.getKey().compareTo(e2.getKey());     // keys - ascending

                        return cmp1 != 0 ? cmp1 : cmp2;

                    }
                }
        );

        List<String> lst = new ArrayList<>();
        for (int i = 0; i < sortedEntries.size(); i++) {
            lst.add(sortedEntries.get(i).getKey());
        }

        return lst;
    }

    private static final Pattern spRe = Pattern.compile("\\s\\s");
    private static final int MAXGRAMS = 300;

    public int distance(List<String> model, Map<String, Integer> knownModel) {
        int dist = 0;
        for (int i = 0; i < model.size(); i++) {
            String value = model.get(i);

            // if (spRe.matcher(value).find()) {

            if (knownModel.get(value) != null) {
                dist += Math.abs(i - knownModel.get(value));
            } else {
                dist += MAXGRAMS;
            }
            //}


        }
        return dist;
    }

    private static Pattern makeNonAlphaRe() {
        StringBuffer nonAlpha = new StringBuffer("[^");
        for (int i = 0; i < Character.MAX_CODE_POINT; i++) {
            if (Character.isLetter((char) i)) {
                nonAlpha.append((char) i);
            }
        }
        nonAlpha.append("]");
        return Pattern.compile(nonAlpha.toString());
    }

    Pattern nonAlphaRe = makeNonAlphaRe();
    Pattern spaceRe = Pattern.compile("\\s+");

    private String normalize(String u) {
        u = Normalizer.normalize(u, Normalizer.Form.NFC);
        u = nonAlphaRe.matcher(u).replaceAll(" ");
        u = spaceRe.matcher(u).replaceAll(" ");
        return u;
    }

    private class LoadModels extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            models = new HashMap<>();
            Pattern pat = Pattern.compile("(.{3})\\s+(.*)");

            Field[] fields = R.raw.class.getFields();
            for (int count = 0; count < fields.length; count++) {
                String dictName = fields[count].getName();
                if (dictName.startsWith("silpa_sdk_guess_language_dic_")) {
                    Map<String, Integer> model = new HashMap<>();
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader
                                (mContext.getResources().openRawResource(
                                        mContext.getResources().getIdentifier(dictName,
                                                "raw", mContext.getPackageName())
                                )));

                        String str = br.readLine();
                        while (str != null) {
                            Matcher mat = pat.matcher(str);
                            if (mat.find()) {
                                model.put(mat.group(1), Integer.parseInt(mat.group(2)));
                            }
                            str = br.readLine();
                        }
                        models.put(dictName.replace("silpa_sdk_guess_language_dic_", "").toLowerCase(Locale.getDefault()), model);
                        br.close();
                    } catch (IOException ioe) {
                        Log.e(LOG_TAG, "Error : " + ioe.getMessage());
                    }
                }
            }
            return null;
        }
    }
}
