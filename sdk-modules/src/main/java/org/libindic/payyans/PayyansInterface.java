package org.libindic.payyans;

/**
 * Created by sujith on 12/6/14.
 */
public interface PayyansInterface {

    /**
     * To get current font map
     *
     * @return returns font map
     */
    public int getFontMap();

    /**
     * To get direction of conversion
     *
     * @return int
     */
    public int getDirection();

    /**
     * To get payyans object used for conversion
     *
     * @return payyans object
     */
    public Payyans getPayyans();

    /**
     * To get converted text from respective view
     *
     * @return string
     */
    public String getConvertedText();

    /**
     * To get name of module
     *
     * @return string
     */
    public String getModuleName();

    /**
     * To get brief information fo the module
     *
     * @return string
     */
    public String getModuleInformation();

    /**
     * To set font map
     *
     * @param fontMap font map value from
     *                Payyans.FONT_MAP_AMBILI
     *                Payyans.FONT_MAP_CHARAKA
     *                Payyans.FONT_MAP_HARITHA
     *                Payyans.FONT_MAP_INDULEKHA
     *                Payyans.FONT_MAP_KARTHIKA
     *                Payyans.FONT_MAP_MANORAMA
     *                Payyans.FONT_MAP_MATWEB
     *                Payyans.FONT_MAP_NANDINI
     *                Payyans.FONT_MAP_PANCHARI
     *                Payyans.FONT_MAP_REVATHI
     *                Payyans.FONT_MAP_TEMPLATE
     *                Payyans.FONT_MAP_UMA
     *                Payyans.FONT_MAP_VALLUVAR
     */
    public void setFontMap(int fontMap);

    /**
     * To set direction of conversion
     *
     * @param direction direction value from
     *                  Payyans.ASCII_TO_UNICODE
     *                  Payyans.UNICODE_TO_ASCII
     */
    public void setDirection(int direction);

    /**
     * To set payyans object for conversion
     *
     * @param payyans
     */
    public void setPayyans(Payyans payyans);

}
