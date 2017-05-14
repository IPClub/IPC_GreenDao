package greendao.ipc.am.ipc_greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import greendao.ipc.am.ipc_greendao.dao.DaoMaster;
import greendao.ipc.am.ipc_greendao.dao.DaoSession;

/**
 * Created by haykc on 05/14/2017.
 */

public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public DaoSession daoSession(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }
}
