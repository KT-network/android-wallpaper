package com.kt.simpleWallPaper.api.Phone.base;

import java.util.List;

public class resDataType {
    private List<CategoryList> category;

    public List<CategoryList> getCategory() {
        return category;
    }

    public static class CategoryList {
        private int count;
        private String ename;
        private String rname;
        private String cover_temp;
        private String name;
        private String cover;
        private int rank;
        private String type;
        private String id;

        public int getCount() {
            return count;
        }

        public String getEname() {
            return ename;
        }

        public String getName() {
            return name;
        }

        public String getCover() {
            return cover;
        }

        public String getType() {
            return type;
        }

        public String getId() {
            return id;
        }
    }
}
