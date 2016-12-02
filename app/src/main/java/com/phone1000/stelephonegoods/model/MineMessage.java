package com.phone1000.stelephonegoods.model;

/**
 * Created by my on 2016/12/2.
 */
public class MineMessage {

    /**
     * body : {"data":{"totalCredit":310000,"usable":310000,"userId":805191,"needToPay7":0,"mobile":"18363999935","loginName":"18363999935","isAuthorization":false,"isRealnameApprove":true,"mxCount":2,"xjCount":1}}
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
        /**
         * data : {"totalCredit":310000,"usable":310000,"userId":805191,"needToPay7":0,"mobile":"18363999935","loginName":"18363999935","isAuthorization":false,"isRealnameApprove":true,"mxCount":2,"xjCount":1}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * totalCredit : 310000
             * usable : 310000
             * userId : 805191
             * needToPay7 : 0
             * mobile : 18363999935
             * loginName : 18363999935
             * isAuthorization : false
             * isRealnameApprove : true
             * mxCount : 2
             * xjCount : 1
             */

            private int totalCredit;
            private int usable;
            private int userId;
            private int needToPay7;
            private String mobile;
            private String loginName;
            private boolean isAuthorization;
            private boolean isRealnameApprove;
            private int mxCount;
            private int xjCount;

            public int getTotalCredit() {
                return totalCredit;
            }

            public void setTotalCredit(int totalCredit) {
                this.totalCredit = totalCredit;
            }

            public int getUsable() {
                return usable;
            }

            public void setUsable(int usable) {
                this.usable = usable;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getNeedToPay7() {
                return needToPay7;
            }

            public void setNeedToPay7(int needToPay7) {
                this.needToPay7 = needToPay7;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public boolean isIsAuthorization() {
                return isAuthorization;
            }

            public void setIsAuthorization(boolean isAuthorization) {
                this.isAuthorization = isAuthorization;
            }

            public boolean isIsRealnameApprove() {
                return isRealnameApprove;
            }

            public void setIsRealnameApprove(boolean isRealnameApprove) {
                this.isRealnameApprove = isRealnameApprove;
            }

            public int getMxCount() {
                return mxCount;
            }

            public void setMxCount(int mxCount) {
                this.mxCount = mxCount;
            }

            public int getXjCount() {
                return xjCount;
            }

            public void setXjCount(int xjCount) {
                this.xjCount = xjCount;
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
