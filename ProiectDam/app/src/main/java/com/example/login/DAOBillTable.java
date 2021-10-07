package com.example.login;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAOBillTable {
    @Insert
    void inserareFacturaInBD(BillTable billTable);

    @Query("Select * from BillTable")
    List<BillTable> selectAllBills();

    @Query("UPDATE BillTable SET suma=:suma WHERE id= :id")
    void update(Float suma, int id);

    @Delete()
    void delete(BillTable billTable);

    @Query("DELETE FROM BillTable")
    void delete();



}
