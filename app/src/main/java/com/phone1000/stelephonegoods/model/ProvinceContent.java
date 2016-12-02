package com.phone1000.stelephonegoods.model;

import java.util.List;

/**
 * Created by my on 2016/11/30.
 */
public class ProvinceContent {

    /**
     * body : {"data":[{"code":"11","fullName":"北京市"},{"code":"12","fullName":"天津市"},{"code":"13","fullName":"河北省"},{"code":"14","fullName":"山西省"},{"code":"15","fullName":"内蒙古自治区"},{"code":"21","fullName":"辽宁省"},{"code":"22","fullName":"吉林省"},{"code":"23","fullName":"黑龙江省"},{"code":"31","fullName":"上海市"},{"code":"32","fullName":"江苏省"},{"code":"33","fullName":"浙江省"},{"code":"34","fullName":"安徽省"},{"code":"35","fullName":"福建省"},{"code":"36","fullName":"江西省"},{"code":"37","fullName":"山东省"},{"code":"41","fullName":"河南省"},{"code":"42","fullName":"湖北省"},{"code":"43","fullName":"湖南省"},{"code":"44","fullName":"广东省"},{"code":"45","fullName":"广西壮族自治区"},{"code":"46","fullName":"海南省"},{"code":"50","fullName":"重庆市"},{"code":"51","fullName":"四川省"},{"code":"52","fullName":"贵州省"},{"code":"53","fullName":"云南省"},{"code":"54","fullName":"西藏自治区"},{"code":"61","fullName":"陕西省"},{"code":"62","fullName":"甘肃省"},{"code":"63","fullName":"青海省"},{"code":"64","fullName":"宁夏回族自治区"},{"code":"65","fullName":"新疆维吾尔自治区"},{"code":"71","fullName":"台湾省"},{"code":"81","fullName":"香港特别行政区"},{"code":"82","fullName":"澳门特别行政区"}]}
     * heads : {"code":200,"message":"success"}
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * code : 11
             * fullName : 北京市
             */

            private String code;
            private String fullName;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }
        }
    }

    public static class HeadsBean {
        /**
         * code : 200
         * message : success
         */

        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
