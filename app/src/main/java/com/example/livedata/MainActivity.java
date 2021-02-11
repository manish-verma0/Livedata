package com.example.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;


import android.os.Bundle;
import android.view.View;

import com.example.livedata.databinding.ActivityMainBinding;

import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        final Observable<Integer> observable=Observable.empty();
        final Observer<Integer> observer=new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                activityMainBinding.textView.setText(integer.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribeWith(observer);

        activityMainBinding.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                observer.onNext(count);

            }
        });
        final MutableLiveData<Integer> data=new MutableLiveData<>();
        data.observe(this, new androidx.lifecycle.Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                activityMainBinding.textView2.setText(integer.toString());
            }
        });

        activityMainBinding.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                data.postValue(count);
            }
        });
    }
}