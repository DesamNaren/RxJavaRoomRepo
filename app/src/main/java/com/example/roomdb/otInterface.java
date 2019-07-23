package com.example.roomdb;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public interface otInterface {
    void onGetAllOts(List<OtItem> otItems);

    void onOtItemDeleted();

    void onOtItemAdded();

    void onDataNotAvailable();

    void onOtItemUpdate(OtItem otItem);

    void onOtItemUpdated();

    void onOtItemDelete(OtItem otItem);

    void otCount(int cnt);

    void getOTItem(OtItem otItem);

}
