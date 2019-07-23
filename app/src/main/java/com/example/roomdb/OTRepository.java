package com.example.roomdb;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

class OTRepository {
    private OTDataBase db;
    private int cnt;
    private OtItem otItem;
    private List<OtItem> otItems;

    OTRepository(Context context) {
        db = OTDataBase.getInstance(context);
    }

    void getOTItem(final otInterface databaseCallback, final String empId, final String des, final String post) {
        Single.create(new SingleOnSubscribe<OtItem>() {
            @Override
            public void subscribe(SingleEmitter<OtItem> emitter) {
                try {
                    otItem = db.getOtDao().getOTItem(empId, des, post);
                    emitter.onSuccess(otItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<OtItem>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(OtItem otItem) {
                        databaseCallback.getOTItem(otItem);
                    }

                    @Override
                    public void onError(Throwable e) {
                        databaseCallback.onDataNotAvailable();
                    }
                });
    }

    @SuppressLint("CheckResult")
    void getAllOTs(final otInterface databaseCallback) {

        Flowable.create(new FlowableOnSubscribe<List<OtItem>>() {
            @Override
            public void subscribe(FlowableEmitter<List<OtItem>> emitter) throws Exception {
                otItems = db.getOtDao().getAllOts();
                emitter.onNext(otItems);
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<OtItem>>() {
                    @Override
                    public void accept(List<OtItem> otItems) throws Exception {
                        databaseCallback.onGetAllOts(otItems);
                    }
                });

//        Single.create(new SingleOnSubscribe<List<OtItem>>() {
//            @Override
//            public void subscribe(SingleEmitter<List<OtItem>> emitter) throws Exception {
//                otItems = db.getOtDao().getAllOts();
//                emitter.onSuccess(otItems);
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<OtItem>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(List<OtItem> otItems) {
//                databaseCallback.onGetAllOts(otItems);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                databaseCallback.onDataNotAvailable();
//            }
//        });
    }

    void insertOT(final otInterface databaseCallback, final OtItem otItem) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.getOtDao().insert(otItem);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                databaseCallback.onOtItemAdded();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

    void deleteOT(final otInterface databaseCallback, final OtItem otItem) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.getOtDao().delete(otItem);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onOtItemDeleted();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

    void deleteAllOts(final otInterface databaseCallback) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.getOtDao().deleteAll();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onOtItemDeleted();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

    void updateOT(final otInterface databaseCallback, final OtItem updatedOtItem, final OtItem otItem) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.getOtDao().updateOT(updatedOtItem.getOts(), otItem.getEmpId(), otItem.getDesignation(), otItem.getPostType());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onOtItemUpdated();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }


    void getOTsCount(final otInterface databaseCallback) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                cnt = db.getOtDao().otCount();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.otCount(cnt);
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }
}
