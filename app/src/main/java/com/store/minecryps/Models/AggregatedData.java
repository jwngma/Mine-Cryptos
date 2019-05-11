package com.store.minecryps.Models;

import com.google.gson.annotations.SerializedName;

public class AggregatedData {

    @SerializedName("TYPE")
    private String tYPE;
    @SerializedName("MARKET")
    private String mARKET;
    @SerializedName("FROMSYMBOL")
    private String fROMSYMBOL;
    @SerializedName("TOSYMBOL")
    private String tOSYMBOL;
    @SerializedName("FLAGS")
    private String fLAGS;
    @SerializedName("PRICE")
    private String pRICE;
    @SerializedName("LASTUPDATE")
    private String lASTUPDATE;
    @SerializedName("LASTVOLUME")
    private String lASTVOLUME;
    @SerializedName("LASTVOLUMETO")
    private String lASTVOLUMETO;
    @SerializedName("LASTTRADEID")
    private String lASTTRADEID;
    @SerializedName("VOLUMEDAY")
    private String vOLUMEDAY;
    @SerializedName("VOLUMEDAYTO")
    private String vOLUMEDAYTO;
    @SerializedName("VOLUME24HOUR")
    private String vOLUME24HOUR;
    @SerializedName("VOLUME24HOURTO")
    private String vOLUME24HOURTO;
    @SerializedName("OPENDAY")
    private String oPENDAY;
    @SerializedName("HIGHDAY")
    private String hIGHDAY;
    @SerializedName("LOWDAY")
    private String lOWDAY;
    @SerializedName("OPEN24HOUR")
    private String oPEN24HOUR;
    @SerializedName("HIGH24HOUR")
    private String hIGH24HOUR;
    @SerializedName("LOW24HOUR")
    private String lOW24HOUR;
    @SerializedName("LASTMARKET")
    private String lASTMARKET;
    @SerializedName("VOLUMEHOUR")
    private String vOLUMEHOUR;
    @SerializedName("VOLUMEHOURTO")
    private String vOLUMEHOURTO;
    @SerializedName("OPENHOUR")
    private String oPENHOUR;
    @SerializedName("HIGHHOUR")
    private String hIGHHOUR;
    @SerializedName("LOWHOUR")
    private String lOWHOUR;
    @SerializedName("CHANGE24HOUR")
    private String cHANGE24HOUR;
    @SerializedName("CHANGEPCT24HOUR")
    private String cHANGEPCT24HOUR;
    @SerializedName("CHANGEDAY")
    private String cHANGEDAY;
    @SerializedName("CHANGEPCTDAY")
    private String cHANGEPCTDAY;
    @SerializedName("SUPPLY")
    private String  sUPPLY;
    @SerializedName("MKTCAP")
    private String mKTCAP;
    @SerializedName("TOTALVOLUME24H")
    private String tOTALVOLUME24H;
    @SerializedName("TOTALVOLUME24HTO")
    private String tOTALVOLUME24HTO;
    @SerializedName("IMAGEURL")
    private String iMAGEURL;

    public AggregatedData(String tYPE, String mARKET, String fROMSYMBOL, String tOSYMBOL, String fLAGS, String pRICE, String lASTUPDATE, String lASTVOLUME, String lASTVOLUMETO, String lASTTRADEID, String vOLUMEDAY, String vOLUMEDAYTO, String vOLUME24HOUR, String vOLUME24HOURTO, String oPENDAY, String hIGHDAY, String lOWDAY, String oPEN24HOUR, String hIGH24HOUR, String lOW24HOUR, String lASTMARKET, String vOLUMEHOUR, String vOLUMEHOURTO, String oPENHOUR, String hIGHHOUR, String lOWHOUR, String cHANGE24HOUR, String cHANGEPCT24HOUR, String cHANGEDAY, String cHANGEPCTDAY, String sUPPLY, String mKTCAP, String tOTALVOLUME24H, String tOTALVOLUME24HTO, String iMAGEURL) {
        this.tYPE = tYPE;
        this.mARKET = mARKET;
        this.fROMSYMBOL = fROMSYMBOL;
        this.tOSYMBOL = tOSYMBOL;
        this.fLAGS = fLAGS;
        this.pRICE = pRICE;
        this.lASTUPDATE = lASTUPDATE;
        this.lASTVOLUME = lASTVOLUME;
        this.lASTVOLUMETO = lASTVOLUMETO;
        this.lASTTRADEID = lASTTRADEID;
        this.vOLUMEDAY = vOLUMEDAY;
        this.vOLUMEDAYTO = vOLUMEDAYTO;
        this.vOLUME24HOUR = vOLUME24HOUR;
        this.vOLUME24HOURTO = vOLUME24HOURTO;
        this.oPENDAY = oPENDAY;
        this.hIGHDAY = hIGHDAY;
        this.lOWDAY = lOWDAY;
        this.oPEN24HOUR = oPEN24HOUR;
        this.hIGH24HOUR = hIGH24HOUR;
        this.lOW24HOUR = lOW24HOUR;
        this.lASTMARKET = lASTMARKET;
        this.vOLUMEHOUR = vOLUMEHOUR;
        this.vOLUMEHOURTO = vOLUMEHOURTO;
        this.oPENHOUR = oPENHOUR;
        this.hIGHHOUR = hIGHHOUR;
        this.lOWHOUR = lOWHOUR;
        this.cHANGE24HOUR = cHANGE24HOUR;
        this.cHANGEPCT24HOUR = cHANGEPCT24HOUR;
        this.cHANGEDAY = cHANGEDAY;
        this.cHANGEPCTDAY = cHANGEPCTDAY;
        this.sUPPLY = sUPPLY;
        this.mKTCAP = mKTCAP;
        this.tOTALVOLUME24H = tOTALVOLUME24H;
        this.tOTALVOLUME24HTO = tOTALVOLUME24HTO;
        this.iMAGEURL = iMAGEURL;
    }

    public String gettYPE() {
        return tYPE;
    }

    public void settYPE(String tYPE) {
        this.tYPE = tYPE;
    }

    public String getmARKET() {
        return mARKET;
    }

    public void setmARKET(String mARKET) {
        this.mARKET = mARKET;
    }

    public String getfROMSYMBOL() {
        return fROMSYMBOL;
    }

    public void setfROMSYMBOL(String fROMSYMBOL) {
        this.fROMSYMBOL = fROMSYMBOL;
    }

    public String gettOSYMBOL() {
        return tOSYMBOL;
    }

    public void settOSYMBOL(String tOSYMBOL) {
        this.tOSYMBOL = tOSYMBOL;
    }

    public String getfLAGS() {
        return fLAGS;
    }

    public void setfLAGS(String fLAGS) {
        this.fLAGS = fLAGS;
    }

    public String getpRICE() {
        return pRICE;
    }

    public void setpRICE(String pRICE) {
        this.pRICE = pRICE;
    }

    public String getlASTUPDATE() {
        return lASTUPDATE;
    }

    public void setlASTUPDATE(String lASTUPDATE) {
        this.lASTUPDATE = lASTUPDATE;
    }

    public String getlASTVOLUME() {
        return lASTVOLUME;
    }

    public void setlASTVOLUME(String lASTVOLUME) {
        this.lASTVOLUME = lASTVOLUME;
    }

    public String getlASTVOLUMETO() {
        return lASTVOLUMETO;
    }

    public void setlASTVOLUMETO(String lASTVOLUMETO) {
        this.lASTVOLUMETO = lASTVOLUMETO;
    }

    public String getlASTTRADEID() {
        return lASTTRADEID;
    }

    public void setlASTTRADEID(String lASTTRADEID) {
        this.lASTTRADEID = lASTTRADEID;
    }

    public String getvOLUMEDAY() {
        return vOLUMEDAY;
    }

    public void setvOLUMEDAY(String vOLUMEDAY) {
        this.vOLUMEDAY = vOLUMEDAY;
    }

    public String getvOLUMEDAYTO() {
        return vOLUMEDAYTO;
    }

    public void setvOLUMEDAYTO(String vOLUMEDAYTO) {
        this.vOLUMEDAYTO = vOLUMEDAYTO;
    }

    public String getvOLUME24HOUR() {
        return vOLUME24HOUR;
    }

    public void setvOLUME24HOUR(String vOLUME24HOUR) {
        this.vOLUME24HOUR = vOLUME24HOUR;
    }

    public String getvOLUME24HOURTO() {
        return vOLUME24HOURTO;
    }

    public void setvOLUME24HOURTO(String vOLUME24HOURTO) {
        this.vOLUME24HOURTO = vOLUME24HOURTO;
    }

    public String getoPENDAY() {
        return oPENDAY;
    }

    public void setoPENDAY(String oPENDAY) {
        this.oPENDAY = oPENDAY;
    }

    public String gethIGHDAY() {
        return hIGHDAY;
    }

    public void sethIGHDAY(String hIGHDAY) {
        this.hIGHDAY = hIGHDAY;
    }

    public String getlOWDAY() {
        return lOWDAY;
    }

    public void setlOWDAY(String lOWDAY) {
        this.lOWDAY = lOWDAY;
    }

    public String getoPEN24HOUR() {
        return oPEN24HOUR;
    }

    public void setoPEN24HOUR(String oPEN24HOUR) {
        this.oPEN24HOUR = oPEN24HOUR;
    }

    public String gethIGH24HOUR() {
        return hIGH24HOUR;
    }

    public void sethIGH24HOUR(String hIGH24HOUR) {
        this.hIGH24HOUR = hIGH24HOUR;
    }

    public String getlOW24HOUR() {
        return lOW24HOUR;
    }

    public void setlOW24HOUR(String lOW24HOUR) {
        this.lOW24HOUR = lOW24HOUR;
    }

    public String getlASTMARKET() {
        return lASTMARKET;
    }

    public void setlASTMARKET(String lASTMARKET) {
        this.lASTMARKET = lASTMARKET;
    }

    public String getvOLUMEHOUR() {
        return vOLUMEHOUR;
    }

    public void setvOLUMEHOUR(String vOLUMEHOUR) {
        this.vOLUMEHOUR = vOLUMEHOUR;
    }

    public String getvOLUMEHOURTO() {
        return vOLUMEHOURTO;
    }

    public void setvOLUMEHOURTO(String vOLUMEHOURTO) {
        this.vOLUMEHOURTO = vOLUMEHOURTO;
    }

    public String getoPENHOUR() {
        return oPENHOUR;
    }

    public void setoPENHOUR(String oPENHOUR) {
        this.oPENHOUR = oPENHOUR;
    }

    public String gethIGHHOUR() {
        return hIGHHOUR;
    }

    public void sethIGHHOUR(String hIGHHOUR) {
        this.hIGHHOUR = hIGHHOUR;
    }

    public String getlOWHOUR() {
        return lOWHOUR;
    }

    public void setlOWHOUR(String lOWHOUR) {
        this.lOWHOUR = lOWHOUR;
    }

    public String getcHANGE24HOUR() {
        return cHANGE24HOUR;
    }

    public void setcHANGE24HOUR(String cHANGE24HOUR) {
        this.cHANGE24HOUR = cHANGE24HOUR;
    }

    public String getcHANGEPCT24HOUR() {
        return cHANGEPCT24HOUR;
    }

    public void setcHANGEPCT24HOUR(String cHANGEPCT24HOUR) {
        this.cHANGEPCT24HOUR = cHANGEPCT24HOUR;
    }

    public String getcHANGEDAY() {
        return cHANGEDAY;
    }

    public void setcHANGEDAY(String cHANGEDAY) {
        this.cHANGEDAY = cHANGEDAY;
    }

    public String getcHANGEPCTDAY() {
        return cHANGEPCTDAY;
    }

    public void setcHANGEPCTDAY(String cHANGEPCTDAY) {
        this.cHANGEPCTDAY = cHANGEPCTDAY;
    }

    public String getsUPPLY() {
        return sUPPLY;
    }

    public void setsUPPLY(String sUPPLY) {
        this.sUPPLY = sUPPLY;
    }

    public String getmKTCAP() {
        return mKTCAP;
    }

    public void setmKTCAP(String mKTCAP) {
        this.mKTCAP = mKTCAP;
    }

    public String gettOTALVOLUME24H() {
        return tOTALVOLUME24H;
    }

    public void settOTALVOLUME24H(String tOTALVOLUME24H) {
        this.tOTALVOLUME24H = tOTALVOLUME24H;
    }

    public String gettOTALVOLUME24HTO() {
        return tOTALVOLUME24HTO;
    }

    public void settOTALVOLUME24HTO(String tOTALVOLUME24HTO) {
        this.tOTALVOLUME24HTO = tOTALVOLUME24HTO;
    }

    public String getiMAGEURL() {
        return iMAGEURL;
    }

    public void setiMAGEURL(String iMAGEURL) {
        this.iMAGEURL = iMAGEURL;
    }
}
