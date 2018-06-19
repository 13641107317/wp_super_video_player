package com.example.wp.wp_super_video_player.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.wp.wp_super_video_player.App;

/**
 * Created by WangPeng on 2018/6/16.
 */
public class Album implements Parcelable {
    private String albumId;//专辑
    private int videoTotal;//集数
    private String title;//专辑名称
    private String subTitle;
    private String director;
    private String mainActor;
    private String verImgUrl;
    private String horImgUrl;
    private String albumDesc;
    private Site site;//网站
    private String tip;//提示
    private boolean isComplected;
    private String letvStyle;

    public Album(int siteId) {
        site = new Site(siteId);
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public int getVideoTotal() {
        return videoTotal;
    }

    public void setVideoTotal(int videoTotal) {
        this.videoTotal = videoTotal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getVerImgUrl() {
        return verImgUrl;
    }

    public void setVerImgUrl(String verImgUrl) {
        this.verImgUrl = verImgUrl;
    }

    public String getHorImgUrl() {
        return horImgUrl;
    }

    public void setHorImgUrl(String horImgUrl) {
        this.horImgUrl = horImgUrl;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isComplected() {
        return isComplected;
    }

    public void setComplected(boolean complected) {
        isComplected = complected;
    }

    public String getLetvStyle() {
        return letvStyle;
    }

    public void setLetvStyle(String letvStyle) {
        this.letvStyle = letvStyle;
    }

    protected Album(Parcel in) {
        albumId = in.readString();
        videoTotal = in.readInt();
        title = in.readString();
        subTitle = in.readString();
        director = in.readString();
        mainActor = in.readString();
        verImgUrl = in.readString();
        horImgUrl = in.readString();
        albumDesc = in.readString();
        site = new Site(in.readInt());
        tip = in.readString();
        isComplected = in.readByte() != 0;
        letvStyle = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Albnm{" +
                "albumId='" + albumId + '\'' +
                ", videoTotal=" + videoTotal +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", director='" + director + '\'' +
                ", mainActor='" + mainActor + '\'' +
                ", verImgUrl='" + verImgUrl + '\'' +
                ", horImgUrl='" + horImgUrl + '\'' +
                ", albumDesc='" + albumDesc + '\'' +
                ", site=" + site +
                ", tip='" + tip + '\'' +
                ", isComplected=" + isComplected +
                ", letvStyle='" + letvStyle + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(albumId);
        parcel.writeInt(videoTotal);
        parcel.writeString(title);
        parcel.writeString(subTitle);
        parcel.writeString(director);
        parcel.writeString(mainActor);
        parcel.writeString(verImgUrl);
        parcel.writeString(horImgUrl);
        parcel.writeString(albumDesc);
        parcel.writeInt(site.getSiteId());
        parcel.writeString(tip);
        parcel.writeByte((byte) (isComplected ? 1 : 0));
        parcel.writeString(letvStyle);
    }
    public String toJson(){
        String res = App.getGson().toJson(this);
        return res;
    }
    public Album fromJson(String json){
        Album album = App.getGson().fromJson(json,Album.class);
        return album;
    }
}
