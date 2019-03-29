package pe.cibertec.agenda.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacto implements Parcelable {

    private int id;
    private String nombre;
    private String telefono;
    private boolean favorito;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    @Override
    public String toString() {
        return nombre + " - " + telefono;
    }

    public Contacto() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.telefono);
        dest.writeByte(this.favorito ? (byte) 1 : (byte) 0);
    }

    protected Contacto(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.telefono = in.readString();
        this.favorito = in.readByte() != 0;
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel source) {
            return new Contacto(source);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };
}
