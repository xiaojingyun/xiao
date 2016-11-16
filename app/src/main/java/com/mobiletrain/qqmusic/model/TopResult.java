package com.mobiletrain.qqmusic.model;

import java.util.List;

/**
 * Created by idea on 2016/10/8.
 */
public class TopResult {

    List<Song> songlist;

    public List<Song> getSonglist() {
        return songlist;
    }

    public void setSonglist(List<Song> songlist) {
        this.songlist = songlist;
    }

    @Override
    public String toString() {
        return "TopResult{" +
                "songlist=" + songlist +
                '}';
    }

    public static class Song {
        String songname;
        String singername;
        String downloadUrl;
        String albumpic_big;
        String albumpic_small;

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public String getSingername() {
            return singername;
        }

        public void setSingername(String singername) {
            this.singername = singername;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getAlbumpic_big() {
            return albumpic_big;
        }

        public void setAlbumpic_big(String albumpic_big) {
            this.albumpic_big = albumpic_big;
        }

        public String getAlbumpic_small() {
            return albumpic_small;
        }

        public void setAlbumpic_small(String albumpic_small) {
            this.albumpic_small = albumpic_small;
        }

        @Override
        public String toString() {
            return "Song{" +
                    "songname='" + songname + '\'' +
                    ", singername='" + singername + '\'' +
                    ", downloadUrl='" + downloadUrl + '\'' +
                    ", albumpic_big='" + albumpic_big + '\'' +
                    ", albumpic_small='" + albumpic_small + '\'' +
                    '}';
        }
    }

}
