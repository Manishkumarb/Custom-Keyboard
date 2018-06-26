package com.example.android.keyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

/**
 * Created by Manish Kumar B on 6/26/2018.
 */

public class MyKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    // interface contains the methods that are called when keys of the soft keyboard are tapped or pressed.

    private KeyboardView keyboardView; //referencing the view defined in the layout

    private Keyboard keyboard;

    private boolean caps=false; //telling us if the caps lock is enabled

    @Override
    // When the keyboard is created, the onCreateInputView method is called
    public View onCreateInputView() {
        keyboardView = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;

    }

    @Override
    public void onPress(int i) {
        // Called when the user presses a key.

    }

    @Override
    public void onRelease(int i) {
        // Called when the user releases a key.
    }

    @Override
    public void onKey(int primaryCode, int[] ints) {
        InputConnection currentInputConnection = getCurrentInputConnection();

        switch (primaryCode) {
            // if key pressed id backspace
            case Keyboard.KEYCODE_DELETE:
                currentInputConnection.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                // key pressed id shift or caps
                caps = !caps;
                keyboard.setShifted(caps);
                keyboardView.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                // when done key is pressed
                currentInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char) primaryCode; // get the char value from primary code
                if (Character.isLetter(code) && caps) {
                    code = Character.toUpperCase(code);
                }
                currentInputConnection.commitText(String.valueOf(code), 1);
                String text= String.valueOf(code);
                Log.d("MYkeyboard",text);

        }
    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {
        //Called when the user quickly moves the finger from right to left.

    }

    @Override
    public void swipeRight() {
        //Called when the user quickly moves the finger from left to right.

    }

    @Override
    public void swipeDown() {
        // Called when the user quickly moves the finger from up to down.

    }

    @Override
    public void swipeUp() {
      //  Called when the user quickly moves the finger from down to up.

    }
}
