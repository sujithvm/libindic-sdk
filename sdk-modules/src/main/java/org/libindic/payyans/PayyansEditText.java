package org.libindic.payyans;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.libindic.R;
import org.libindic.render.IndicEditText;

/**
 * Created by sujith on 10/6/14.
 */
public class PayyansEditText extends IndicEditText implements PayyansInterface {

    /**
     * Context of application
     */
    private Context mContext;

    /**
     * Payyans object to perform conversion in background
     */
    private Payyans payyans;

    /**
     * Specified font map from layout
     */
    private int mFontMap;

    /**
     * Specified direction of conversion from layout
     */
    private int mDirection;

    /**
     * Specified output field from layout
     */
    private int mOutputResourceId;

    /**
     * View specified from layout to output results
     */
    private View mOutputView;

    /**
     * For converted Text
     */
    private String mConvertedText;

    // Log tag
    private static final String LOG_TAG = "PayyansEditText";


    /**
     * Constructor
     *
     * @param context context of application
     */
    public PayyansEditText(Context context) {
        super(context);
        init(null, 0);
        initView();
    }

    /**
     * Constructor
     *
     * @param context context of application
     * @param attrs   attribute set
     */
    public PayyansEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
        initView();
    }

    /**
     * Constructor
     *
     * @param context  context of application
     * @param attrs    attribute set
     * @param defStyle default style
     */
    public PayyansEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
        initView();
    }

    /**
     * Initialize data members
     *
     * @param attrs    attribute set
     * @param defStyle default style
     */
    private void init(AttributeSet attrs, int defStyle) {
        // Init attrs
        initAttrs(attrs, defStyle);
    }

    /**
     * Initialize attributes used
     *
     * @param attrs    attribute set
     * @param defStyle default style
     */
    private void initAttrs(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PayyansEditText,
                defStyle, defStyle
        );

        try {
            this.mContext = getContext();
            this.mFontMap = a.getInteger(R.styleable.PayyansEditText_payyansFontMap,
                    Payyans.DEFAULT_FONT_MAP);
            this.mDirection = a.getInteger(R.styleable.PayyansEditText_payyansDirectionOfConversion,
                    Payyans.DEFAULT_DIRECTION);
            this.mOutputResourceId = a.getResourceId(R.styleable.PayyansEditText_payyansOutputTo, -1);
            this.payyans = new Payyans(this.mContext, this.mFontMap, this.mDirection);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error : " + e.getMessage());
        } finally {
            a.recycle();
        }
    }

    /**
     * Initialize view
     */
    private void initView() {
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (mOutputView == null) {
                    mOutputView = getRootView().findViewById(mOutputResourceId);
                }
                if (mOutputView != null) {
                    mConvertedText = payyans.getConvertText(editable.toString());
                    if (mOutputView instanceof EditText) {
                        ((EditText) mOutputView).setText(mConvertedText);
                    } else if (mOutputView instanceof TextView) {
                        ((TextView) mOutputView).setText(mConvertedText);
                    }
                }
            }
        });
    }

    /**
     * Explicitly set output field
     *
     * @param resourceId resource id of output field
     */
    public void setOutputField(int resourceId) {
        this.mOutputResourceId = resourceId;
    }


    /**
     * Function to get font map
     *
     * @return int - check Payyans font map constants
     */
    @Override
    public int getFontMap() {
        return this.mFontMap;
    }

    /**
     * Function to get current direction of conversion
     *
     * @return Payyans.ASCII_TO_UNICODE or Payyans.UNICODE_TO_ASCII
     */
    @Override
    public int getDirection() {
        return this.mDirection;
    }

    /**
     * Get payyans object for conversion
     *
     * @return payyans object
     */
    @Override
    public Payyans getPayyans() {
        return this.payyans;
    }

    /**
     * Get converted Text
     *
     * @return converted text base of specified font map and direction
     */
    @Override
    public String getConvertedText() {
        return this.mConvertedText;
    }

    /**
     * This function gives name of the module
     *
     * @return name of module
     */
    @Override
    public String getModuleName() {
        return this.payyans.getModuleName();
    }

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    @Override
    public String getModuleInformation() {
        return this.payyans.getModuleInformation();
    }

    /**
     * Explicitly set font map rules
     *
     * @param fontMap font map
     */
    @Override
    public void setFontMap(int fontMap) {
        if (this.payyans != null) {
            this.mFontMap = fontMap;
            this.payyans.setFontMap(this.mFontMap);
        }
    }

    /**
     * Explicitly set direction of conversion
     *
     * @param direction direction
     */
    @Override
    public void setDirection(int direction) {
        if (this.payyans != null) {
            this.mDirection = direction;
            this.payyans.setDirection(direction);
        }
    }

    /**
     * Explicitly set payyans object
     *
     * @param payyans payyans object
     */
    @Override
    public void setPayyans(Payyans payyans) {
        this.payyans = payyans;
    }
}
