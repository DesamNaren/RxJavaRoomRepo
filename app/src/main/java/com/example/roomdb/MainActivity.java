package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.CompositeDisposable;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements otInterface {

    private OTRepository otRepository;
    private Button insertBtn;
    private Button selectAllBtn;
    private Button select;
    private Button deleteAllBtn;
    private Button otCount;
    private List<OtItem> otItems;
    private RecyclerView rv;
    private OTAdapter otAdapter;
    CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertBtn = findViewById(R.id.insert);
        selectAllBtn = findViewById(R.id.selectAll);
        select = findViewById(R.id.select);
        deleteAllBtn = findViewById(R.id.deleteAll);
        otCount = findViewById(R.id.otCount);
        rv = findViewById(R.id.rv);

        otRepository = new OTRepository(this);

        insertBtn.setOnClickListener(onBtnClick);
        selectAllBtn.setOnClickListener(onBtnClick);
        deleteAllBtn.setOnClickListener(onBtnClick);
        otCount.setOnClickListener(onBtnClick);
        select.setOnClickListener(onBtnClick);
        otItems = new ArrayList<>();

    }

    View.OnClickListener onBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.insert:
                    int randomNum = randomNum();
                    OtItem otItem = new OtItem(randomNum + "ots1", randomNum + "123", randomNum + "1234", randomNum + "",
                            randomNum + "naren1", randomNum + "pwd", randomNum + "123", randomNum + "SSD",
                            randomNum + "SSD");
                    otRepository.insertOT(MainActivity.this, otItem);
                    break;
                case R.id.selectAll:
                    getAllOTs();
                    break;
                case R.id.deleteAll:
                    otRepository.deleteAllOts(MainActivity.this);
                    break;
                case R.id.otCount:
                    otRepository.getOTsCount(MainActivity.this);
                    break;
                case R.id.select:
                    if (otItems != null && otItems.size() > 0)
                        otRepository.getOTItem(MainActivity.this,
                                otItems.get(0).getEmpId(),
                                otItems.get(0).getDesignation(),
                                otItems.get(0).getPostType());
                    break;
            }
        }
    };

    private void getAllOTs() {
        otRepository.getAllOTs(MainActivity.this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.clear();
        }
    }


    @Override
    public void onGetAllOts(List<OtItem> otItems) {
        this.otItems = otItems;
        otAdapter = new OTAdapter(this, otItems);
        rv.setAdapter(otAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public void onOtItemDeleted() {
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        getAllOTs();
    }

    @Override
    public void onOtItemAdded() {
        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        getAllOTs();
    }

    @Override
    public void onDataNotAvailable() {
        Toast.makeText(this, "error occurred", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOtItemUpdate(OtItem otItem) {
        int randomNum = randomNum();
        OtItem updatedOtItem = new OtItem(randomNum + otItem.getOts(), randomNum + otItem.getStrId(),
                randomNum + otItem.getStrNo(), randomNum + otItem.getPhoto(),
                randomNum + otItem.getUserName(), randomNum + otItem.getUserPwd(),
                randomNum + otItem.getEmpId(), randomNum + otItem.getDesignation(),
                randomNum + otItem.getPostType());
        otRepository.updateOT(MainActivity.this, updatedOtItem, otItem);
    }

    @Override
    public void onOtItemUpdated() {
        Toast.makeText(this, "UPdated", Toast.LENGTH_SHORT).show();
        getAllOTs();
    }

    @Override
    public void onOtItemDelete(OtItem otItem) {
        otRepository.deleteOT(MainActivity.this, otItem);
    }

    @Override
    public void otCount(int cnt) {
        Toast.makeText(this, "OTs Count: " + cnt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getOTItem(OtItem otItem) {
        Toast.makeText(this, "Exist", Toast.LENGTH_SHORT).show();
        getAllOTs();
    }

    private int randomNum() {
        return new Random().nextInt(61) + 20; // [0, 60] + 20 => [20, 80]
    }
}
