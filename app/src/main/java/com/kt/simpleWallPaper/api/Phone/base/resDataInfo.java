package com.kt.simpleWallPaper.api.Phone.base;

import java.util.List;

public class resDataInfo {
    private List<verticalList> vertical;

    public List<verticalList> getVertical() {
        return vertical;
    }

    public class verticalList {

        private int views;
        private int ncos;
        private int rank;
        private List<String> tag;
        private String wp;
        private String atime;
        private String id;
        private String thumb;
        private String img;
        private String preview;

        public int getViews() {
            return views;
        }

        public int getNcos() {
            return ncos;
        }

        public int getRank() {
            return rank;
        }

        public String getWp() {
            return wp;
        }

        public String getAtime() {
            return atime;
        }

        public String getId() {
            return id;
        }

        public String getThumb() {
            return thumb;
        }

        public String getImg() {
            return img;
        }

        public String getPreview() {
            return preview;
        }


        public List<String> getTag() {
            return tag;
        }
    }
}
