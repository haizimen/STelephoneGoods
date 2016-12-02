package com.phone1000.stelephonegoods.model;

import java.util.List;

/**
 * Created by my on 2016/11/30.
 */
public class BankContent {

    /**
     * body : {"data":[{"id":12,"acId":1,"bankCode":"03090010","bankName":"兴业银行","bankIndicate":"CIB","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/03090010.png","status":"1","color":"#325592"},{"id":3,"acId":1,"bankCode":"03060000","bankName":"广发银行","bankIndicate":"ECITIC","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/03060000.png","status":"1","color":"#ec3545"},{"id":6,"acId":1,"bankCode":"01020000","bankName":"工商银行","bankIndicate":"ICBC","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/01020000.png","status":"1","color":"#e75b66"},{"id":9,"acId":1,"bankCode":"03050000","bankName":"民生银行","bankIndicate":"CMBC","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/03050000.png","status":"1","color":"#125da8"},{"id":8,"acId":1,"bankCode":"01050000","bankName":"建设银行","bankIndicate":"CCB","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/01050000.png","status":"1","color":"#4570b4"},{"id":10,"acId":1,"bankCode":"01030000","bankName":"农业银行","bankIndicate":"ABC","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/01030000.png","status":"1","color":"#11a7a5"},{"id":7,"acId":1,"bankCode":"03030000","bankName":"光大银行","bankIndicate":"CEB","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/03030000.png","status":"1","color":"#743188"},{"id":1,"acId":1,"bankCode":"01040000","bankName":"中国银行","bankIndicate":"BOC","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/01040000.png","status":"1","color":"#d6284f"},{"id":5,"acId":1,"bankCode":"03080000","bankName":"招商银行","bankIndicate":"CMB","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/03080000.png","status":"1","color":"#da3f4b"},{"id":2,"acId":1,"bankCode":"01000000","bankName":"邮政储蓄银行","bankIndicate":"PSBC","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/61000000.png","status":"1","color":"#25864e"},{"id":4,"acId":1,"bankCode":"03100000","bankName":"浦发银行","bankIndicate":"SPDB","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/03100000.png","status":"1","color":"#195086"},{"id":11,"acId":1,"bankCode":"05105840","bankName":"平安银行","bankIndicate":"PAB","bankLogo":"http://api.xiaoxiangyoupin.com/access/static/05105840.png","status":"1","color":"#fa752f"}]}
     * heads : {"message":"success","code":200}
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
             * id : 12
             * acId : 1
             * bankCode : 03090010
             * bankName : 兴业银行
             * bankIndicate : CIB
             * bankLogo : http://api.xiaoxiangyoupin.com/access/static/03090010.png
             * status : 1
             * color : #325592
             */

            private int id;
            private int acId;
            private String bankCode;
            private String bankName;
            private String bankIndicate;
            private String bankLogo;
            private String status;
            private String color;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAcId() {
                return acId;
            }

            public void setAcId(int acId) {
                this.acId = acId;
            }

            public String getBankCode() {
                return bankCode;
            }

            public void setBankCode(String bankCode) {
                this.bankCode = bankCode;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getBankIndicate() {
                return bankIndicate;
            }

            public void setBankIndicate(String bankIndicate) {
                this.bankIndicate = bankIndicate;
            }

            public String getBankLogo() {
                return bankLogo;
            }

            public void setBankLogo(String bankLogo) {
                this.bankLogo = bankLogo;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }
    }

    public static class HeadsBean {
        /**
         * message : success
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
