package me.gfred.gads;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.gfred.gads.model.HoursEntry;
import me.gfred.gads.model.IQEntry;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<IQEntry>> iqEntryLiveData;
    private MutableLiveData<List<HoursEntry>> hoursEntryLiveData;

    public MutableLiveData<List<IQEntry>> getIQEntry() {
        if (iqEntryLiveData == null) {
            iqEntryLiveData = new MutableLiveData<>();
        }
        return iqEntryLiveData;
    }

    public MutableLiveData<List<HoursEntry>> getHoursEntry() {
        if (hoursEntryLiveData == null) {
            hoursEntryLiveData = new MutableLiveData<>();
        }
        return hoursEntryLiveData;
    }

}
