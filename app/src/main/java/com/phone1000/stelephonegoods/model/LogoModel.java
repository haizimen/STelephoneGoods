package com.phone1000.stelephonegoods.model;

/**
 * Created by Administrator on 2016/11/28.
 */
public class LogoModel {

    /**
     * body : {"handle":{"id":64,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/150x150_628094214739070976_handle.png","targetType":2,"group":999,"index":0,"type":3,"status":1,"gotoUrl":"bsdlk://cdn.xiaoxiangyoupin.com/prod/activity/huiyuan/index.html","smallImgUrl":"http://img.xiaoxiangyoupin.com/image/120x120_611801024935497728.png","innerSeq":0,"hotFlag":0}}
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
        /**
         * handle : {"id":64,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/150x150_628094214739070976_handle.png","targetType":2,"group":999,"index":0,"type":3,"status":1,"gotoUrl":"bsdlk://cdn.xiaoxiangyoupin.com/prod/activity/huiyuan/index.html","smallImgUrl":"http://img.xiaoxiangyoupin.com/image/120x120_611801024935497728.png","innerSeq":0,"hotFlag":0}
         */

        private HandleBean handle;

        public HandleBean getHandle() {
            return handle;
        }

        public void setHandle(HandleBean handle) {
            this.handle = handle;
        }

        public static class HandleBean {
            /**
             * id : 64
             * thumbnailUrl : http://img.xiaoxiangyoupin.com/image/150x150_628094214739070976_handle.png
             * targetType : 2
             * group : 999
             * index : 0
             * type : 3
             * status : 1
             * gotoUrl : bsdlk://cdn.xiaoxiangyoupin.com/prod/activity/huiyuan/index.html
             * smallImgUrl : http://img.xiaoxiangyoupin.com/image/120x120_611801024935497728.png
             * innerSeq : 0
             * hotFlag : 0
             */

            private int id;
            private String thumbnailUrl;
            private int targetType;
            private int group;
            private int index;
            private int type;
            private int status;
            private String gotoUrl;
            private String smallImgUrl;
            private int innerSeq;
            private int hotFlag;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getThumbnailUrl() {
                return thumbnailUrl;
            }

            public void setThumbnailUrl(String thumbnailUrl) {
                this.thumbnailUrl = thumbnailUrl;
            }

            public int getTargetType() {
                return targetType;
            }

            public void setTargetType(int targetType) {
                this.targetType = targetType;
            }

            public int getGroup() {
                return group;
            }

            public void setGroup(int group) {
                this.group = group;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getGotoUrl() {
                return gotoUrl;
            }

            public void setGotoUrl(String gotoUrl) {
                this.gotoUrl = gotoUrl;
            }

            public String getSmallImgUrl() {
                return smallImgUrl;
            }

            public void setSmallImgUrl(String smallImgUrl) {
                this.smallImgUrl = smallImgUrl;
            }

            public int getInnerSeq() {
                return innerSeq;
            }

            public void setInnerSeq(int innerSeq) {
                this.innerSeq = innerSeq;
            }

            public int getHotFlag() {
                return hotFlag;
            }

            public void setHotFlag(int hotFlag) {
                this.hotFlag = hotFlag;
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
