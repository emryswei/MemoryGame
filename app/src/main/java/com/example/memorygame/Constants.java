package com.example.memorygame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    //直接用Arrays.asList()產生的list，如果使用shuffle方法
    //shuffle後會改變原list，所以用下面這個方法
    public static List<Integer> IMAGE_ICONS = new ArrayList<>(Arrays.asList(
            R.drawable.ic_pizza,
            R.drawable.ic_alarm,
            R.drawable.ic_album,
            R.drawable.ic_display,
            R.drawable.ic_face,
            R.drawable.ic_moon,
            R.drawable.ic_motocycle,
            R.drawable.ic_pizza,
            R.drawable.ic_sun,
            R.drawable.ic_taxi,
            R.drawable.ic_train,
            R.drawable.ic_watch
    ));
}
