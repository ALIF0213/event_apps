package com.example.appevent.data.local.room;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.appevent.data.local.entity.FavoriteEvent;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FavoriteEventDao_Impl implements FavoriteEventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FavoriteEvent> __insertionAdapterOfFavoriteEvent;

  private final EntityDeletionOrUpdateAdapter<FavoriteEvent> __deletionAdapterOfFavoriteEvent;

  public FavoriteEventDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavoriteEvent = new EntityInsertionAdapter<FavoriteEvent>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `favorite_events` (`id`,`name`,`ownerName`,`beginTime`,`endTime`,`quota`,`registrants`,`description`,`imageLogo`,`link`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FavoriteEvent value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getOwnerName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getOwnerName());
        }
        if (value.getBeginTime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getBeginTime());
        }
        if (value.getEndTime() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEndTime());
        }
        stmt.bindLong(6, value.getQuota());
        stmt.bindLong(7, value.getRegistrants());
        if (value.getDescription() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDescription());
        }
        if (value.getImageLogo() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getImageLogo());
        }
        if (value.getLink() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLink());
        }
      }
    };
    this.__deletionAdapterOfFavoriteEvent = new EntityDeletionOrUpdateAdapter<FavoriteEvent>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `favorite_events` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FavoriteEvent value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public Object insertFavorite(final FavoriteEvent event,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFavoriteEvent.insert(event);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteFavorite(final FavoriteEvent event,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFavoriteEvent.handle(event);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public LiveData<List<FavoriteEvent>> getAllFavorites() {
    final String _sql = "SELECT * FROM favorite_events";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"favorite_events"}, false, new Callable<List<FavoriteEvent>>() {
      @Override
      public List<FavoriteEvent> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfOwnerName = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerName");
          final int _cursorIndexOfBeginTime = CursorUtil.getColumnIndexOrThrow(_cursor, "beginTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfQuota = CursorUtil.getColumnIndexOrThrow(_cursor, "quota");
          final int _cursorIndexOfRegistrants = CursorUtil.getColumnIndexOrThrow(_cursor, "registrants");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfImageLogo = CursorUtil.getColumnIndexOrThrow(_cursor, "imageLogo");
          final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
          final List<FavoriteEvent> _result = new ArrayList<FavoriteEvent>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FavoriteEvent _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpOwnerName;
            if (_cursor.isNull(_cursorIndexOfOwnerName)) {
              _tmpOwnerName = null;
            } else {
              _tmpOwnerName = _cursor.getString(_cursorIndexOfOwnerName);
            }
            final String _tmpBeginTime;
            if (_cursor.isNull(_cursorIndexOfBeginTime)) {
              _tmpBeginTime = null;
            } else {
              _tmpBeginTime = _cursor.getString(_cursorIndexOfBeginTime);
            }
            final String _tmpEndTime;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmpEndTime = null;
            } else {
              _tmpEndTime = _cursor.getString(_cursorIndexOfEndTime);
            }
            final int _tmpQuota;
            _tmpQuota = _cursor.getInt(_cursorIndexOfQuota);
            final int _tmpRegistrants;
            _tmpRegistrants = _cursor.getInt(_cursorIndexOfRegistrants);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpImageLogo;
            if (_cursor.isNull(_cursorIndexOfImageLogo)) {
              _tmpImageLogo = null;
            } else {
              _tmpImageLogo = _cursor.getString(_cursorIndexOfImageLogo);
            }
            final String _tmpLink;
            if (_cursor.isNull(_cursorIndexOfLink)) {
              _tmpLink = null;
            } else {
              _tmpLink = _cursor.getString(_cursorIndexOfLink);
            }
            _item = new FavoriteEvent(_tmpId,_tmpName,_tmpOwnerName,_tmpBeginTime,_tmpEndTime,_tmpQuota,_tmpRegistrants,_tmpDescription,_tmpImageLogo,_tmpLink);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getFavoriteById(final int eventId,
      final Continuation<? super FavoriteEvent> continuation) {
    final String _sql = "SELECT * FROM favorite_events WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, eventId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<FavoriteEvent>() {
      @Override
      public FavoriteEvent call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfOwnerName = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerName");
          final int _cursorIndexOfBeginTime = CursorUtil.getColumnIndexOrThrow(_cursor, "beginTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfQuota = CursorUtil.getColumnIndexOrThrow(_cursor, "quota");
          final int _cursorIndexOfRegistrants = CursorUtil.getColumnIndexOrThrow(_cursor, "registrants");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfImageLogo = CursorUtil.getColumnIndexOrThrow(_cursor, "imageLogo");
          final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
          final FavoriteEvent _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpOwnerName;
            if (_cursor.isNull(_cursorIndexOfOwnerName)) {
              _tmpOwnerName = null;
            } else {
              _tmpOwnerName = _cursor.getString(_cursorIndexOfOwnerName);
            }
            final String _tmpBeginTime;
            if (_cursor.isNull(_cursorIndexOfBeginTime)) {
              _tmpBeginTime = null;
            } else {
              _tmpBeginTime = _cursor.getString(_cursorIndexOfBeginTime);
            }
            final String _tmpEndTime;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmpEndTime = null;
            } else {
              _tmpEndTime = _cursor.getString(_cursorIndexOfEndTime);
            }
            final int _tmpQuota;
            _tmpQuota = _cursor.getInt(_cursorIndexOfQuota);
            final int _tmpRegistrants;
            _tmpRegistrants = _cursor.getInt(_cursorIndexOfRegistrants);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpImageLogo;
            if (_cursor.isNull(_cursorIndexOfImageLogo)) {
              _tmpImageLogo = null;
            } else {
              _tmpImageLogo = _cursor.getString(_cursorIndexOfImageLogo);
            }
            final String _tmpLink;
            if (_cursor.isNull(_cursorIndexOfLink)) {
              _tmpLink = null;
            } else {
              _tmpLink = _cursor.getString(_cursorIndexOfLink);
            }
            _result = new FavoriteEvent(_tmpId,_tmpName,_tmpOwnerName,_tmpBeginTime,_tmpEndTime,_tmpQuota,_tmpRegistrants,_tmpDescription,_tmpImageLogo,_tmpLink);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
