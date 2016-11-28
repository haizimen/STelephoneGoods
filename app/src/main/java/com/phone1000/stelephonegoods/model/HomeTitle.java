package com.phone1000.stelephonegoods.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class HomeTitle {

    /**
     * body : {"pageViews":[{"id":1,"titleName":"小象精选","isHome":1,"titleIndex":1},{"id":2,"titleName":"3C数码","isHome":0,"titleIndex":3},{"id":4,"titleName":"饰品轻奢","isHome":0,"titleIndex":4},{"id":6,"titleName":"美妆个护","isHome":0,"titleIndex":4},{"id":8,"titleName":"潮品精选","isHome":0,"titleIndex":5},{"id":10,"titleName":"家居好物","isHome":0,"titleIndex":6},{"id":12,"titleName":"诗和远方","isHome":0,"titleIndex":7}]}
     * heads : {"message":"","code":200}
     */

    private BodyBean body;
    private HeadsBean heads;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public HeadsBean getHeads() {
        return heads;
    }

    public void setHeads(HeadsBean heads) {
        this.heads = heads;
    }

    public static class BodyBean {
        private List<PageViewsBean> pageViews;

        public List<PageViewsBean> getPageViews() {
            return pageViews;
        }

        public void setPageViews(List<PageViewsBean> pageViews) {
            this.pageViews = pageViews;
        }

        public static class PageViewsBean {
            /**
             * id : 1
             * titleName : 小象精选
             * isHome : 1
             * titleIndex : 1
             */

            private int id;
            private String titleName;
            private int isHome;
            private int titleIndex;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public int getIsHome() {
                return isHome;
            }

            public void setIsHome(int isHome) {
                this.isHome = isHome;
            }

            public int getTitleIndex() {
                return titleIndex;
            }

            public void setTitleIndex(int titleIndex) {
                this.titleIndex = titleIndex;
            }
        }
    }

    public static class HeadsBean {
        /**
         * message :
         * code : 200
         */

        private String message;
        private int code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
