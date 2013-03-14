/*
 * Copyright (C) 2008-2009 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.looprecur.vinkeyboard.android;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.Keyboard.Row;
import android.view.inputmethod.EditorInfo;

public class VinKeyboard extends Keyboard {

    private Key mEnterKey;
    private Context context;
    
    public VinKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
        context = context;
    }

    public VinKeyboard(Context context, int layoutTemplateResId, 
            CharSequence characters, int columns, int horizontalPadding) {
        super(context, layoutTemplateResId, characters, columns, horizontalPadding);
        context = context;
    }

    @Override
    protected Key createKeyFromXml(Resources res, Row parent, int x, int y, 
            XmlResourceParser parser) {
        Key key = new VinKey(res, parent, x, y, parser);
        if (key.codes[0] == 10) {
            mEnterKey = key;
        }
        return key;
    }
    
    /**
     * This looks at the ime options given by the current editor, to set the
     * appropriate label on the keyboard's enter key (if it has one).
     */
    void setImeOptions(Resources res, int options) {
        if (mEnterKey == null) {
            return;
        }
        
        switch (options&(EditorInfo.IME_MASK_ACTION|EditorInfo.IME_FLAG_NO_ENTER_ACTION)) {
            case EditorInfo.IME_ACTION_GO:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                int labelGoKeyId = context.getResources().getIdentifier("label_go_key", "string", "com.looprecur.vinkeyboard.android");
                mEnterKey.label = res.getText(labelGoKeyId);
                break;
            case EditorInfo.IME_ACTION_NEXT:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                int labelNextKeyId = context.getResources().getIdentifier("label_next_key", "string", "com.looprecur.vinkeyboard.android"); 
                mEnterKey.label = res.getText(labelNextKeyId);
                break;
            case EditorInfo.IME_ACTION_SEARCH:
                int symKeyboardSearchId = context.getResources().getIdentifier("sym_keyboard_search", "drawable", "com.looprecur.vinkeyboard.android");
                mEnterKey.icon = res.getDrawable(symKeyboardSearchId);
                mEnterKey.label = null;
                break;
            case EditorInfo.IME_ACTION_SEND:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                int labelSendKeyId = context.getResources().getIdentifier("label_send_key", "string", "com.looprecur.vinkeyboard.android");
                mEnterKey.label = res.getText(labelSendKeyId);
                break;
            default:
                int symKeyboardReturnId = context.getResources().getIdentifier("sym_keyboard_return", "drawable", "com.looprecur.vinkeyboard.android");
                mEnterKey.icon = res.getDrawable(symKeyboardReturnId);
                mEnterKey.label = null;
                break;
        }
    }
    
    static class VinKey extends Keyboard.Key {
        
        public VinKey(Resources res, Keyboard.Row parent, int x, int y, XmlResourceParser parser) {
            super(res, parent, x, y, parser);
        }
        
        /**
         * Overriding this method so that we can reduce the target area for the key that
         * closes the keyboard. 
         */
        @Override
        public boolean isInside(int x, int y) {
            return super.isInside(x, codes[0] == KEYCODE_CANCEL ? y - 10 : y);
        }
    }

}
