package pe.edu.cibertec.agendaroom;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class ContactProvider extends ContentProvider {

    public static final int CONTACTS = 1;
    public static final int CONTACT = 2;

    // Permite enlazar las URI con un identificador
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(ContactContrat.AUTHORITY, ContactContrat.CONTENT_PATH, CONTACTS);
        uriMatcher.addURI(ContactContrat.AUTHORITY, ContactContrat.CONTENT_PATH + "/#", CONTACT);

    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {

        ContactDao contactDao = AppDatabase.getInstance(getContext()).contactDao();
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                return contactDao.getAllCursor();

            case CONTACT:
                return contactDao.getByIdCursor(ContentUris.parseId(uri));

        }
        return null;
    }

    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                return ContactContrat.MULTIPLE_ITEMS;
            case CONTACT:
                return ContactContrat.SINGLE_ITEM;
        }
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Contact contact = new Contact();

        String name = values.getAsString("name");
        String telephone = values.getAsString("telephone");
        contact.setName(name);
        contact.setTelephone(telephone);

        ContactDao contactDao = AppDatabase.getInstance(getContext()).contactDao();
        Long id = contactDao.insertCP(contact);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        ContactDao contactDao = AppDatabase.getInstance(getContext()).contactDao();
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                return contactDao.deleteAllCursor();
            case CONTACT:
                return contactDao.deleteByIdCursor(ContentUris.parseId(uri));
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
