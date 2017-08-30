package com.kimvan.hung.parcelable;

/**
 * Created by MSI on 8/30/2017.
 */

public class NormalWordMeaning {
    private int _stt;
    private String _enAnglais,_enVietnamien;

    public NormalWordMeaning(){

    }

    public NormalWordMeaning(String _enAnglais, String _enVietnamien) {
        this._enAnglais = _enAnglais;
        this._enVietnamien = _enVietnamien;
    }

    public int get_stt() {
        return _stt;
    }

    public void set_stt(int _stt) {
        this._stt = _stt;
    }

    public String get_enAnglais() {
        return _enAnglais;
    }

    public void set_enAnglais(String _enAnglais) {
        this._enAnglais = _enAnglais;
    }

    public String get_enVietnamien() {
        return _enVietnamien;
    }

    public void set_enVietnamien(String _enVietnamien) {
        this._enVietnamien = _enVietnamien;
    }
}
