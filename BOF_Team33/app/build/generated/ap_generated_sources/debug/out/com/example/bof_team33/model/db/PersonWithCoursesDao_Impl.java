package com.example.bof_team33.model.db;

import android.database.Cursor;
import androidx.collection.LongSparseArray;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PersonWithCoursesDao_Impl implements PersonWithCoursesDao {
  private final RoomDatabase __db;

  public PersonWithCoursesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<PersonWithCourses> getAll() {
    final String _sql = "SELECT * FROM persons";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
      try {
        final int _cursorIndexOfPersonId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
        final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
        final int _cursorIndexOfPhoto = CursorUtil.getColumnIndexOrThrow(_cursor, "photo");
        final LongSparseArray<ArrayList<Course>> _collectionCourses = new LongSparseArray<ArrayList<Course>>();
        while (_cursor.moveToNext()) {
          if (!_cursor.isNull(_cursorIndexOfPersonId)) {
            final long _tmpKey = _cursor.getLong(_cursorIndexOfPersonId);
            ArrayList<Course> _tmpCoursesCollection = _collectionCourses.get(_tmpKey);
            if (_tmpCoursesCollection == null) {
              _tmpCoursesCollection = new ArrayList<Course>();
              _collectionCourses.put(_tmpKey, _tmpCoursesCollection);
            }
          }
        }
        _cursor.moveToPosition(-1);
        __fetchRelationshipcourseAscomExampleBofTeam33ModelDbCourse(_collectionCourses);
        final List<PersonWithCourses> _result = new ArrayList<PersonWithCourses>(_cursor.getCount());
        while(_cursor.moveToNext()) {
          final PersonWithCourses _item;
          final Person _tmpPerson;
          if (! (_cursor.isNull(_cursorIndexOfPersonId) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfPhoto))) {
            _tmpPerson = new Person();
            _tmpPerson.personId = _cursor.getInt(_cursorIndexOfPersonId);
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpPerson.name = null;
            } else {
              _tmpPerson.name = _cursor.getString(_cursorIndexOfName);
            }
            if (_cursor.isNull(_cursorIndexOfPhoto)) {
              _tmpPerson.photo = null;
            } else {
              _tmpPerson.photo = _cursor.getString(_cursorIndexOfPhoto);
            }
          }  else  {
            _tmpPerson = null;
          }
          ArrayList<Course> _tmpCoursesCollection_1 = null;
          if (!_cursor.isNull(_cursorIndexOfPersonId)) {
            final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfPersonId);
            _tmpCoursesCollection_1 = _collectionCourses.get(_tmpKey_1);
          }
          if (_tmpCoursesCollection_1 == null) {
            _tmpCoursesCollection_1 = new ArrayList<Course>();
          }
          _item = new PersonWithCourses();
          _item.person = _tmpPerson;
          _item.courses = _tmpCoursesCollection_1;
          _result.add(_item);
        }
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public PersonWithCourses get(final int id) {
    final String _sql = "SELECT * FROM persons WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
    try {
      final int _cursorIndexOfPersonId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
      final int _cursorIndexOfPhoto = CursorUtil.getColumnIndexOrThrow(_cursor, "photo");
      final LongSparseArray<ArrayList<Course>> _collectionCourses = new LongSparseArray<ArrayList<Course>>();
      while (_cursor.moveToNext()) {
        if (!_cursor.isNull(_cursorIndexOfPersonId)) {
          final long _tmpKey = _cursor.getLong(_cursorIndexOfPersonId);
          ArrayList<Course> _tmpCoursesCollection = _collectionCourses.get(_tmpKey);
          if (_tmpCoursesCollection == null) {
            _tmpCoursesCollection = new ArrayList<Course>();
            _collectionCourses.put(_tmpKey, _tmpCoursesCollection);
          }
        }
      }
      _cursor.moveToPosition(-1);
      __fetchRelationshipcourseAscomExampleBofTeam33ModelDbCourse(_collectionCourses);
      final PersonWithCourses _result;
      if(_cursor.moveToFirst()) {
        final Person _tmpPerson;
        if (! (_cursor.isNull(_cursorIndexOfPersonId) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfPhoto))) {
          _tmpPerson = new Person();
          _tmpPerson.personId = _cursor.getInt(_cursorIndexOfPersonId);
          if (_cursor.isNull(_cursorIndexOfName)) {
            _tmpPerson.name = null;
          } else {
            _tmpPerson.name = _cursor.getString(_cursorIndexOfName);
          }
          if (_cursor.isNull(_cursorIndexOfPhoto)) {
            _tmpPerson.photo = null;
          } else {
            _tmpPerson.photo = _cursor.getString(_cursorIndexOfPhoto);
          }
        }  else  {
          _tmpPerson = null;
        }
        ArrayList<Course> _tmpCoursesCollection_1 = null;
        if (!_cursor.isNull(_cursorIndexOfPersonId)) {
          final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfPersonId);
          _tmpCoursesCollection_1 = _collectionCourses.get(_tmpKey_1);
        }
        if (_tmpCoursesCollection_1 == null) {
          _tmpCoursesCollection_1 = new ArrayList<Course>();
        }
        _result = new PersonWithCourses();
        _result.person = _tmpPerson;
        _result.courses = _tmpCoursesCollection_1;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int count() {
    final String _sql = "SELECT COUNT(*) FROM persons";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipcourseAscomExampleBofTeam33ModelDbCourse(
      final LongSparseArray<ArrayList<Course>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<Course>> _tmpInnerMap = new LongSparseArray<ArrayList<Course>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipcourseAscomExampleBofTeam33ModelDbCourse(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<Course>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipcourseAscomExampleBofTeam33ModelDbCourse(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`person_id`,`year`,`quarter`,`subject`,`course_num` FROM `course` WHERE `person_id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "person_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfCourseId = 0;
      final int _cursorIndexOfPersonId = 1;
      final int _cursorIndexOfYear = 2;
      final int _cursorIndexOfQuarter = 3;
      final int _cursorIndexOfSubject = 4;
      final int _cursorIndexOfCourseNum = 5;
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final long _tmpKey = _cursor.getLong(_itemKeyIndex);
          ArrayList<Course> _tmpRelation = _map.get(_tmpKey);
          if (_tmpRelation != null) {
            final Course _item_1;
            final int _tmpCourseId;
            _tmpCourseId = _cursor.getInt(_cursorIndexOfCourseId);
            final int _tmpPersonId;
            _tmpPersonId = _cursor.getInt(_cursorIndexOfPersonId);
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final String _tmpQuarter;
            if (_cursor.isNull(_cursorIndexOfQuarter)) {
              _tmpQuarter = null;
            } else {
              _tmpQuarter = _cursor.getString(_cursorIndexOfQuarter);
            }
            final String _tmpSubject;
            if (_cursor.isNull(_cursorIndexOfSubject)) {
              _tmpSubject = null;
            } else {
              _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
            }
            final String _tmpCourse_num;
            if (_cursor.isNull(_cursorIndexOfCourseNum)) {
              _tmpCourse_num = null;
            } else {
              _tmpCourse_num = _cursor.getString(_cursorIndexOfCourseNum);
            }
            _item_1 = new Course(_tmpCourseId,_tmpPersonId,_tmpYear,_tmpQuarter,_tmpSubject,_tmpCourse_num);
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
