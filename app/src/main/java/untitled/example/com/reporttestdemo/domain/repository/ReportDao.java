package untitled.example.com.reporttestdemo.domain.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import untitled.example.com.reporttestdemo.domain.repository.entity.Report;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ReportDao {
    
    @Query("SELECT * FROM report")
    Flowable<List<Report>> getReportList();

    @Query("SELECT * FROM report WHERE id = :id")
    Single<Report> getReportById(long id);

    @Delete
    void deleteReport(Report report);

    @Insert(onConflict = REPLACE)
    void setReport(Report report);

    @Update(onConflict = REPLACE)
    void updateReport(Report report);

    @Query("SELECT COUNT(*) FROM report")
    Single<Integer> count();

    @Query("DELETE FROM report")
    void deleteAll();
}
