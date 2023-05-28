package it_school.sumdu.edu.individualwork;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile TourismDao _tourismDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `tourism` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `country` TEXT, `city` TEXT, `title` TEXT, `description` TEXT, `price` REAL, `rating` REAL, `dateStart` TEXT, `dateEnd` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"96ef9f93e898fdebd34e96104b4754c0\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `tourism`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTourism = new HashMap<String, TableInfo.Column>(9);
        _columnsTourism.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsTourism.put("country", new TableInfo.Column("country", "TEXT", false, 0));
        _columnsTourism.put("city", new TableInfo.Column("city", "TEXT", false, 0));
        _columnsTourism.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsTourism.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsTourism.put("price", new TableInfo.Column("price", "REAL", false, 0));
        _columnsTourism.put("rating", new TableInfo.Column("rating", "REAL", false, 0));
        _columnsTourism.put("dateStart", new TableInfo.Column("dateStart", "TEXT", false, 0));
        _columnsTourism.put("dateEnd", new TableInfo.Column("dateEnd", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTourism = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTourism = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTourism = new TableInfo("tourism", _columnsTourism, _foreignKeysTourism, _indicesTourism);
        final TableInfo _existingTourism = TableInfo.read(_db, "tourism");
        if (! _infoTourism.equals(_existingTourism)) {
          throw new IllegalStateException("Migration didn't properly handle tourism(it_school.sumdu.edu.individualwork.Tourism).\n"
                  + " Expected:\n" + _infoTourism + "\n"
                  + " Found:\n" + _existingTourism);
        }
      }
    }, "96ef9f93e898fdebd34e96104b4754c0", "86f78107ce9cb7507df7179df928b776");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "tourism");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `tourism`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public TourismDao tourismDao() {
    if (_tourismDao != null) {
      return _tourismDao;
    } else {
      synchronized(this) {
        if(_tourismDao == null) {
          _tourismDao = new TourismDao_Impl(this);
        }
        return _tourismDao;
      }
    }
  }
}
