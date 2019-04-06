package pe.edu.cibertec.agendaroom;

public class ContactContrat {

    public static final String AUTHORITY = "pe.edu.cibertec.agendaroom.provider";

    public static final String CONTENT_PATH = "contact";

    public static final String CONTENT_URI = "content://" + AUTHORITY + "/" + CONTENT_PATH;

    // Para obtener múltiples filas
    public static final String MULTIPLE_ITEMS = "vnd.android.cursor.dir/vnd."
            + AUTHORITY
            + "."
            + CONTENT_PATH;

    // Para obtener una única fila
    public static final String SINGLE_ITEM = "vnd.android.cursor.item/vnd."
            + AUTHORITY
            + "."
            + CONTENT_PATH;

}
