package com.example.roomdb;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface OTDao {

    @Insert
    void insert(OtItem otItem);

    @Query("UPDATE OtItem SET ots=:ots WHERE empId = :empId AND designation = :designation AND postType = :postType")
    void updateOT(String ots, String empId, String designation, String postType);

    @Update
    void update(OtItem otItem);

    @Delete
    void delete(OtItem otItem);

    @Query("DELETE FROM otitem")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM otitem")
    int otCount();

    @Query("SELECT * FROM OTITEM where  empId LIKE :empId "
            + "AND designation LIKE :des "
            + "AND postType LIKE :post LIMIT 1")
    OtItem getOTItem(String empId, String des, String post);


    @Query("SELECT * FROM OTITEM where empId LIKE :empId "
            + "AND designation LIKE :des "
            + "AND postType LIKE :post LIMIT 1")
    List<OtItem> getAllItems(String empId, String des, String post);

    @Query("SELECT * FROM OTITEM")
    List<OtItem> getAllOts();
}
