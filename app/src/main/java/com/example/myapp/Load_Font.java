package com.example.myapp;

import android.graphics.Typeface;
import android.widget.Button;

public class Load_Font {
    Button btn;

    public Load_Font(Button btn){
        this.btn = btn;
    }

    public void Bool_black(){
        Typeface customFont = Typeface.createFromAsset(this.btn.getContext().getAssets(), "./font/robotoblack.ttf");
        this.btn.setTypeface(customFont);
    }
}
