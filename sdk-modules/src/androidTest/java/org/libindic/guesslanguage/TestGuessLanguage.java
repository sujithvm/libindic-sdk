package org.libindic.guesslanguage;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sujith on 28/7/14.
 */
public class TestGuessLanguage extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGuessLanguage() {

        GuessLanguage gl = new GuessLanguage(getContext());

        Map<String, String> tests = new HashMap<>();

        tests.put("This is a test of the language checker", "en");
        tests.put("Verifions que le détecteur de langues marche", "fr");
        tests.put("Sprawdźmy, czy odgadywacz języków pracuje", "pl");
        tests.put("авай проверить  узнает ли наш угадатель русски язык", "ru");
        tests.put("La respuesta de los acreedores a la oferta argentina para salir del default no ha sido muy positiv", "es");
        tests.put("Сайлау нәтижесінде дауыстардың басым бөлігін ел премьер министрі Виктор Янукович пен оның қарсыласы, оппозиция жетекшісі Виктор Ющенко алды.", "kk"); // Kazakh
        tests.put("милиция ва уч солиқ идораси ходимлари яраланган. Шаҳарда хавфсизлик чоралари кучайтирилган.", "uz"); // uzbek
        tests.put("көрбөгөндөй элдик толкундоо болуп, Кокон шаарынын көчөлөрүндө бир нече миң киши нааразылык билдирди.", "ky"); // kyrgyz
        tests.put("yakın tarihin en çekişmeli başkanlık seçiminde oy verme işlemi sürerken, katılımda rekor bekleniyor.", "tr");
        tests.put("Daxil olan xəbərlərdə deyilir ki, 6 nəfər Bağdadın mərkəzində yerləşən Təhsil Nazirliyinin binası yaxınlığında baş vermiş partlayış zamanı həlak olub.", "az"); // Azerbaijani

        tests.put(" ملايين الناخبين الأمريكيين يدلون بأصواتهم وسط إقبال قياسي على انتخابات هي الأشد تنافسا منذ عقود", "ar");
        tests.put("Американське суспільство, поділене суперечностями, збирається взяти активну участь у голосуванні", "uk"); // ukrainian
        tests.put("Francouzský ministr financí zmírnil výhrady vůči nízkým firemním daním v nových členských státech EU", "cs"); // czech
        tests.put("biće prilično izjednačena, sugerišu najnovije ankete. Oba kandidata tvrde da su sposobni da dobiju rat protiv terorizma", "hr"); // croatian
        tests.put(" е готов да даде гаранции, че няма да прави ядрено оръжие, ако му се разреши мирна атомна програма", "bg"); // bulgarian
        tests.put("на јавното мислење покажуваат дека трката е толку тесна, што се очекува двајцата соперници да ја прекршат традицијата и да се појават и на самиот изборен ден.", "mk"); // macedonian
        tests.put("în acest sens aparţinînd Adunării Generale a organizaţiei, în ciuda faptului că mai multe dintre solicitările organizaţiei privind organizarea scrutinului nu au fost soluţionate", "ro"); // romanian
        tests.put("kaluan ditën e fundit të fushatës në shtetet kryesore për të siguruar sa më shumë votues.", "sq"); // albanian
        tests.put("αναμένεται να σπάσουν παράδοση δεκαετιών και να συνεχίσουν την εκστρατεία τους ακόμη και τη μέρα των εκλογών", "el"); // greek
        tests.put(" 美国各州选民今天开始正式投票。据信，", "zh"); // chinese
        tests.put(" Die kritiek was volgens hem bitter hard nodig, omdat Nederland binnen een paar jaar in een soort Belfast zou dreigen te veranderen", "nl"); // dutch
        tests.put("På denne side bringer vi billeder fra de mange forskellige forberedelser til arrangementet, efterhånden som vi får dem ", "da"); // danish
        tests.put("Vi säger att Frälsningen är en gåva till alla, fritt och för intet.  Men som vi nämnt så finns det två villkor som måste", "sv"); // swedish
        tests.put("Nominasjonskomiteen i Akershus KrF har skviset ut Einar Holstad fra stortingslisten. Ytre Enebakk-mannen har plass p Stortinget s lenge Valgerd Svarstad Haugland sitter i", "nb"); // norwegian
        tests.put("on julkishallinnon verkkopalveluiden yhteinen osoite. Kansalaisten arkielämää helpottavaa tietoa on koottu eri aihealueisiin", "fi"); // finnish
        tests.put("Ennetamaks reisil ebameeldivaid vahejuhtumeid vii end kurssi reisidokumentide ja viisade reeglitega ning muu praktilise informatsiooniga", "et"); // estonian
        tests.put("Hiába jön létre az önkéntes magyar haderő, hiába nem lesz többé bevonulás, változatlanul fennmarad a hadkötelezettség intézménye", "hu"); // hungarian
        tests.put("հարաբերական", "hy"); // armenian
        tests.put("Hai vấn đề khó chịu với màn hình thường gặp nhất khi bạn dùng laptop là vết trầy xước và điểm chết. Sau đây là vài cách xử lý chú", "vi");
        tests.put("ii", "UNKNOWN");

        // This text has a mix of Hirigana, Katakana and CJK which requires the fix for issue:3 to classify correctly
        tests.put("トヨタ自動車、フィリピンの植林活動で第三者認証取得　トヨタ自動車(株)（以下、トヨタ）は、2007年９月よりフィリピンのルソン島北部に位置するカガヤン州ペニャブラン", "ja");

        // Indic languages
        tests.put("नमस्कार सिल्पा की दुनिया में आपका स्वागत है", "hi");
        tests.put("മലയാളം", "ml");
        tests.put("ಕನ್ನಡ", "kn");
        tests.put("தமிழ்", "ta");
        tests.put("తెలుగు", "te");
        tests.put("ଓଡ଼ିଆ", "or");
        tests.put("ગુજરાતી", "gu");
        tests.put("ਪੰਜਾਬੀ", "pa");
        tests.put("বাংলা", "bn");


        for (Map.Entry<String, String> entry : tests.entrySet()) {
            String text = entry.getKey();
            String lang = entry.getValue();

            assertEquals(lang, gl.guessLanguage(text));
        }

        String text = "Verifions que le détecteur de langues marche";
        assertEquals("fr", gl.guessLanguageTag(text));
        assertEquals("French", gl.guessLanguageName(text));
        assertEquals(26150, gl.guessLanguageId(text));

        LanguageInfo info = gl.guessLanguageInfo(text);
        assertEquals("fr", info.getLanguageTag());
        assertEquals(26150, info.getLanguageId());
        assertEquals("French", info.getLanguageName());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
