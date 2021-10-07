package com.example.login;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAOProviderTable {
    @Insert
    void inserareProviderInBD(ProviderTable providerTable);

    @Query("Select * from ProviderTable")
    List<ProviderTable> selectAllUsers();

    @Delete
    void delete(ProviderTable providerTable);
}
