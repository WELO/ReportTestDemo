package untitled.example.com.reporttestdemo.domain.repository;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import untitled.example.com.reporttestdemo.domain.repository.entity.Report;

@android.arch.persistence.room.Database(entities = {Report.class}, version = 1, exportSchema = false)

public abstract class ReportDb extends RoomDatabase {

    public abstract ReportDao reportDao();

    private static ReportDb instance;

    public static void init(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ReportDb.class, "database")
                    .fallbackToDestructiveMigration()//當DB架構被更新時，刪除原有的資料
                    .build();
        }
    }

    public ReportDb() {
    }

    public static ReportDao getReportDao() {
        return getInstance().reportDao();
    }

    public static ReportDb getInstance() {
        if (instance == null) {
            throw new RuntimeException("Database not init!!");
        }
        return instance;
    }

}