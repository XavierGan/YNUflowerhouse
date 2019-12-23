package com.demo.ynuflowerhouse.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("12月3日 今日新到鲜花 --- 康乃馨");
    }

    public LiveData<String> getText() {
        return mText;
    }
}