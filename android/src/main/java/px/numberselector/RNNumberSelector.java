
package px.numberselector;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Arrays;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.shawnlin.numberpicker.NumberPicker;

public class RNNumberSelector extends ViewGroupManager<ViewGroup> {

    public static final String REACT_CLASS = "RNNumberSelector";
    private static String[] itms;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected FrameLayout createViewInstance(final ThemedReactContext reactContext) {

        final FrameLayout frameLayout = new FrameLayout(reactContext.getCurrentActivity());
        NumberPicker numberPicker = new NumberPicker(reactContext.getCurrentActivity());
        frameLayout.addView(numberPicker);

        numberPicker.setOrientation(LinearLayout.HORIZONTAL);

        // Set fading edge enabled
        numberPicker.setFadingEdgeEnabled(true);

        // Set scroller enabled
        numberPicker.setScrollerEnabled(true);

        // Set wrap selector wheel
        numberPicker.setWrapSelectorWheel(true);

        // OnClickListener
        numberPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("", "Click on current value");
            }
        });

        // OnValueChangeListener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int id = frameLayout.getId();

                reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher()
                        .dispatchEvent(new NumberSelectorEvent(id, newVal));
            }
        });

        return frameLayout;
    }

    @ReactProp(name = "items")
    public void setItems(FrameLayout numberPickerFrame, ReadableArray items) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        itms = new String[items.size()];
        Object[] mItems = items.toArrayList().toArray();

        for (int i = 0; i < mItems.length; i++) {
            itms[i] = mItems[i].toString();
        }

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(mItems.length - 1);
        numberPicker.setDisplayedValues(itms);
    }

    @ReactProp(name = "selectedItem")
    public void setSelectedItem(FrameLayout numberPickerFrame, int selectedItem) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setValue(selectedItem);

    }

    @ReactProp(name = "textColor")
    public void setTextColor(FrameLayout numberPickerFrame, String textColor) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setTextColor(Color.parseColor(textColor));
    }

    @ReactProp(name = "highlightedTextColor")
    public void setHighlightedTextColor(FrameLayout numberPickerFrame, String highlightedTextColor) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setSelectedTextColor(Color.parseColor(highlightedTextColor));
    }

    @ReactProp(name = "fontSize")
    public void setFontSize(FrameLayout numberPickerFrame, float fontSize) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setTextSize(fontSize);
    }

    @ReactProp(name = "highlightedFontSize")
    public void setHighlightedFontSize(FrameLayout numberPickerFrame, float highlightedFontSize) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setSelectedTextSize(highlightedFontSize);
    }

    @ReactProp(name = "dividerColor")
    public void setDividerColor(FrameLayout numberPickerFrame, String dividerColor) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setDividerColor(Color.parseColor(dividerColor));
    }

    @ReactProp(name = "wheelItemCount")
    public void setWheelItemCount(FrameLayout numberPickerFrame, int wheelItemCount) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setWheelItemCount(wheelItemCount);
    }
}