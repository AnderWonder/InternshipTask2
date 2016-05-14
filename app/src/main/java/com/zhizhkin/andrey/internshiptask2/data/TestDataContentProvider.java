package com.zhizhkin.andrey.internshiptask2.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import com.zhizhkin.andrey.internshiptask2.R;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestDataContentProvider extends ContentProvider {

    private final int PICTURES_IN_REQUEST = 3;

    private final int ASYNC_EMULATION_TIME = 3;

    private MatrixCursor mDataCursor;

    public static final Uri PROVIDER_URI = Uri.parse("content://zhizhkin.andrey.internshiptask2.data");

    @Override
    public boolean onCreate() {
        mDataCursor = new MatrixCursor(new String[]{"_id", "id", "likes", "status", "type", "requestInfo", "address",
                "responsible", "creationDate", "registrationDate", "solveDate", "pictures"});
        for (UserRequest.StatusType status : UserRequest.StatusType.values())
            for (int i = 0; i < 3; i++)
                addRow(status);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (ASYNC_EMULATION_TIME > 0) {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(ASYNC_EMULATION_TIME));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String[] columns=mDataCursor.getColumnNames();
        MatrixCursor answer = new MatrixCursor(columns);
        synchronized (this) {
            if (mDataCursor.moveToFirst()) {
                while (!mDataCursor.isAfterLast()) {
                    if ((mDataCursor.getInt(mDataCursor.getColumnIndex("status")) == Integer.parseInt(selection))&&
                            (sortOrder==String.valueOf(-1)||mDataCursor.getInt(mDataCursor.getColumnIndex("type"))==Integer.parseInt(sortOrder))) {
                        MatrixCursor.RowBuilder b = answer.newRow();
                        for (String column : columns) {
                            int colIndex = mDataCursor.getColumnIndex(column);
                            switch (mDataCursor.getType(colIndex)) {
                                case Cursor.FIELD_TYPE_STRING:
                                    b.add(mDataCursor.getString(colIndex));
                                    break;
                                case Cursor.FIELD_TYPE_INTEGER:
                                    b.add(mDataCursor.getInt(colIndex));
                                    break;
                            }
                        }
                    }
                    mDataCursor.moveToNext();
                }
            }
        }
        answer.setNotificationUri(getContext().getContentResolver(), PROVIDER_URI);
        return answer;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        UserRequest.StatusType mStatus=UserRequest.StatusType.IN_PROCESS;
        int status = contentValues.getAsInteger("status");
        switch (status) {
            case 0:
                mStatus = UserRequest.StatusType.IN_PROCESS;
                break;
            case 1:
                mStatus = UserRequest.StatusType.DONE;
                break;
            case 2:
                mStatus = UserRequest.StatusType.WAITING;
                break;
        }
        addRow(mStatus);
        getContext().getContentResolver().notifyChange(PROVIDER_URI,null);
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    public void addRow(UserRequest.StatusType userRequestStatus) {
        Resources resources = getContext().getResources();
        Random random = new Random();
        String[] requestInfoArray = resources.getStringArray(R.array.requests_info);
        String[] streetsArray = resources.getStringArray(R.array.streets_array);
        String[] responsiblesArray = resources.getStringArray(R.array.responcibles);
        Date startDate;
        switch (userRequestStatus) {
            case DONE:
                startDate = getDate("01/02/16");
                break;
            default:
                startDate = getDate("01/04/16");
        }
        Date creationDate = addRandomDays(startDate, 0, 15);
        Date registrationDate = addRandomDays(creationDate, 1, 3);
        Date solveDate = addRandomDays(registrationDate, 15, 30);
        mDataCursor.addRow(new Object[]{
                mDataCursor.getCount() + 1, //_id
                resources.getString(R.string.user_request_id_prefix) + String.valueOf(1000000 + random.nextInt(1000000)), //id
                random.nextInt(100),//likes
                userRequestStatus.getId(),//status
                random.nextInt(4),//type
                requestInfoArray[random.nextInt(requestInfoArray.length)],//requestInfo
                streetsArray[random.nextInt(streetsArray.length)] + ", " + (1 + random.nextInt(150)),//address
                responsiblesArray[random.nextInt(responsiblesArray.length)],//responsible
                dateToString(creationDate),//creationDate
                dateToString(registrationDate),//registrationDate
                dateToString(solveDate),//solveDate
                getPictures(resources)//pictures
        });
    }

    private String getPictures(Resources resources) {
        String picturesFolder = resources.getString(R.string.request_viewer_pictures_assets_folder);
        StringBuilder builder = new StringBuilder();
        List<String> filenames = new ArrayList<>();
        try {
            filenames = Arrays.asList(resources.getAssets().list(picturesFolder));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(filenames);
        for (int i = 0; i < PICTURES_IN_REQUEST; i++)
            builder.append(resources.getString(R.string.request_viewer_uri_part_assets))
                    .append(picturesFolder)
                    .append('/')
                    .append(filenames.get(i))
                    .append(";");
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private Date addRandomDays(Date startDate, int from, int to) {
        int addDays = new Random().nextInt(to) + from;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, addDays);
        return calendar.getTime();
    }

    private Date getDate(String strDate) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yy").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private String dateToString(Date date) {
        return new SimpleDateFormat("dd/MM/yy").format(date);
    }

}
