package com.kimvan.hung.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by MSI on 8/30/2017.
 */

public class VerbWordMeaning extends NormalWordMeaning implements Parcelable {

    ArrayList<Conjugation> lesTemps = new ArrayList<>();

    public VerbWordMeaning() {
    }

    public VerbWordMeaning(String _enAnglais, String _enVietnamien) {
        super(_enAnglais, _enVietnamien);
    }

    public class Conjugation{
        private int _id;
        private String _leTemps,_je,_tu;

        public Conjugation(String _leTemps, String _je, String _tu) {
            this._leTemps = _leTemps;
            this._je = _je;
            this._tu = _tu;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public String get_leTemps() {
            return _leTemps;
        }

        public void set_leTemps(String _leTemps) {
            this._leTemps = _leTemps;
        }

        public String get_je() {
            return _je;
        }

        public void set_je(String _je) {
            this._je = _je;
        }

        public String get_tu() {
            return _tu;
        }

        public void set_tu(String _tu) {
            this._tu = _tu;
        }
    }

    public ArrayList<Conjugation> getLesTemps() {
        return lesTemps;
    }

    public Conjugation setAnConjugation(int stt,String letemp,String _enAnlais,String _enVietnamien){
        Conjugation result = new Conjugation(letemp,_enAnlais,_enVietnamien);
        result.set_id(stt);
        return result;
    }

    public void setLesTemps(ArrayList<Conjugation> lesTemps) {
        this.lesTemps = lesTemps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel pc, int flags) {
        pc.writeArray(lesTemps.toArray());
        pc.writeStringArray(new String[]{get_enAnglais(),get_enVietnamien()});
        pc.writeInt(get_stt());
    }

    /** Static field used to regenerate object, individually or as arrays */
    public static final Parcelable.Creator<VerbWordMeaning> CREATOR = new Parcelable.Creator<VerbWordMeaning>() {
        public VerbWordMeaning createFromParcel(Parcel pc) {
            return new VerbWordMeaning(pc);
        }
        public VerbWordMeaning[] newArray(int size) {
            return new VerbWordMeaning[size];
        }
    };

    /**Ctor from Parcel, reads back fields IN THE ORDER they were written */
    public VerbWordMeaning(Parcel pc){
        set_stt(pc.readInt());
        String[] data = new String[2];
        pc.readStringArray(data);
        set_enAnglais(data[0]);
        set_enVietnamien(data[1]);
    }
}
