package com.phone1000.stelephonegoods.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class NavigationModel {

    /**
     * body : {"data":{"bannerUrl":"http://img.xiaoxiangyoupin.com/image/750x460_626618299974684672.png","goods":[{"instalmentPeriods":12,"goodTitle":"爱奇艺vip会员激活码1个月黄金月卡充值官方自动发货不支持TV端","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_624824583010127872.jpg","goodsOrder":1,"directPaymentAmount":0,"goodId":4414,"goodsStatus":1,"goodsCode":"102547","peroidInstalmentAmount":25,"goodsName":"【新人专享】爱奇艺vip会员1个月"},{"instalmentPeriods":12,"goodTitle":"优酷土豆会员1个月 [官方自动充值]优酷会员月卡 不支持TV端","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_625169383974506496.jpg","goodsOrder":2,"directPaymentAmount":0,"goodId":4416,"goodsStatus":1,"goodsCode":"102548","peroidInstalmentAmount":25,"goodsName":"【新人专享】优酷土豆会员1个月"},{"instalmentPeriods":12,"goodTitle":"搜狐视频黄金会员1个月搜狐视频vip月卡搜狐会员自动充值填手机号","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_624825042479353856.jpg","goodsOrder":3,"directPaymentAmount":0,"goodId":4418,"goodsStatus":1,"goodsCode":"102549","peroidInstalmentAmount":25,"goodsName":"【新人专享】搜狐视频黄金会员1个月"},{"instalmentPeriods":12,"goodTitle":"乐视乐次元影视会员一个月vip1个月月卡送20张观影券 自动充值","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_626979043178188800.jpg","goodsOrder":4,"directPaymentAmount":0,"goodId":4420,"goodsStatus":1,"goodsCode":"102550","peroidInstalmentAmount":25,"goodsName":"【新人专享】乐视乐次元影视会员1个月"},{"instalmentPeriods":12,"goodTitle":"腾讯视频VIP会员1个月会员一个月31天好莱坞会员低价 充值请填Q号","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_626978971048742912.jpg","goodsOrder":5,"directPaymentAmount":0,"goodId":4422,"goodsStatus":1,"goodsCode":"102551","peroidInstalmentAmount":25,"goodsName":"【新人专享】腾讯视频VIP会员1个月"},{"instalmentPeriods":12,"goodTitle":"魔兽世界点卡wow点卡15元15战网点数15元点卡转服转阵营官方卡密","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_625155903712137216.jpg","goodsOrder":6,"directPaymentAmount":0,"goodId":4424,"goodsStatus":1,"goodsCode":"102552","peroidInstalmentAmount":25,"goodsName":"【新人专享】魔兽世界点卡wow点卡15元"}]}}
     * heads : {"code":200}
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
         * data : {"bannerUrl":"http://img.xiaoxiangyoupin.com/image/750x460_626618299974684672.png","goods":[{"instalmentPeriods":12,"goodTitle":"爱奇艺vip会员激活码1个月黄金月卡充值官方自动发货不支持TV端","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_624824583010127872.jpg","goodsOrder":1,"directPaymentAmount":0,"goodId":4414,"goodsStatus":1,"goodsCode":"102547","peroidInstalmentAmount":25,"goodsName":"【新人专享】爱奇艺vip会员1个月"},{"instalmentPeriods":12,"goodTitle":"优酷土豆会员1个月 [官方自动充值]优酷会员月卡 不支持TV端","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_625169383974506496.jpg","goodsOrder":2,"directPaymentAmount":0,"goodId":4416,"goodsStatus":1,"goodsCode":"102548","peroidInstalmentAmount":25,"goodsName":"【新人专享】优酷土豆会员1个月"},{"instalmentPeriods":12,"goodTitle":"搜狐视频黄金会员1个月搜狐视频vip月卡搜狐会员自动充值填手机号","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_624825042479353856.jpg","goodsOrder":3,"directPaymentAmount":0,"goodId":4418,"goodsStatus":1,"goodsCode":"102549","peroidInstalmentAmount":25,"goodsName":"【新人专享】搜狐视频黄金会员1个月"},{"instalmentPeriods":12,"goodTitle":"乐视乐次元影视会员一个月vip1个月月卡送20张观影券 自动充值","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_626979043178188800.jpg","goodsOrder":4,"directPaymentAmount":0,"goodId":4420,"goodsStatus":1,"goodsCode":"102550","peroidInstalmentAmount":25,"goodsName":"【新人专享】乐视乐次元影视会员1个月"},{"instalmentPeriods":12,"goodTitle":"腾讯视频VIP会员1个月会员一个月31天好莱坞会员低价 充值请填Q号","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_626978971048742912.jpg","goodsOrder":5,"directPaymentAmount":0,"goodId":4422,"goodsStatus":1,"goodsCode":"102551","peroidInstalmentAmount":25,"goodsName":"【新人专享】腾讯视频VIP会员1个月"},{"instalmentPeriods":12,"goodTitle":"魔兽世界点卡wow点卡15元15战网点数15元点卡转服转阵营官方卡密","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_625155903712137216.jpg","goodsOrder":6,"directPaymentAmount":0,"goodId":4424,"goodsStatus":1,"goodsCode":"102552","peroidInstalmentAmount":25,"goodsName":"【新人专享】魔兽世界点卡wow点卡15元"}]}
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
             * bannerUrl : http://img.xiaoxiangyoupin.com/image/750x460_626618299974684672.png
             * goods : [{"instalmentPeriods":12,"goodTitle":"爱奇艺vip会员激活码1个月黄金月卡充值官方自动发货不支持TV端","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_624824583010127872.jpg","goodsOrder":1,"directPaymentAmount":0,"goodId":4414,"goodsStatus":1,"goodsCode":"102547","peroidInstalmentAmount":25,"goodsName":"【新人专享】爱奇艺vip会员1个月"},{"instalmentPeriods":12,"goodTitle":"优酷土豆会员1个月 [官方自动充值]优酷会员月卡 不支持TV端","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_625169383974506496.jpg","goodsOrder":2,"directPaymentAmount":0,"goodId":4416,"goodsStatus":1,"goodsCode":"102548","peroidInstalmentAmount":25,"goodsName":"【新人专享】优酷土豆会员1个月"},{"instalmentPeriods":12,"goodTitle":"搜狐视频黄金会员1个月搜狐视频vip月卡搜狐会员自动充值填手机号","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_624825042479353856.jpg","goodsOrder":3,"directPaymentAmount":0,"goodId":4418,"goodsStatus":1,"goodsCode":"102549","peroidInstalmentAmount":25,"goodsName":"【新人专享】搜狐视频黄金会员1个月"},{"instalmentPeriods":12,"goodTitle":"乐视乐次元影视会员一个月vip1个月月卡送20张观影券 自动充值","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_626979043178188800.jpg","goodsOrder":4,"directPaymentAmount":0,"goodId":4420,"goodsStatus":1,"goodsCode":"102550","peroidInstalmentAmount":25,"goodsName":"【新人专享】乐视乐次元影视会员1个月"},{"instalmentPeriods":12,"goodTitle":"腾讯视频VIP会员1个月会员一个月31天好莱坞会员低价 充值请填Q号","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_626978971048742912.jpg","goodsOrder":5,"directPaymentAmount":0,"goodId":4422,"goodsStatus":1,"goodsCode":"102551","peroidInstalmentAmount":25,"goodsName":"【新人专享】腾讯视频VIP会员1个月"},{"instalmentPeriods":12,"goodTitle":"魔兽世界点卡wow点卡15元15战网点数15元点卡转服转阵营官方卡密","originalPrice":300,"bannerId":52,"thumbnailUrl":"http://img.xiaoxiangyoupin.com/image/750x750_625155903712137216.jpg","goodsOrder":6,"directPaymentAmount":0,"goodId":4424,"goodsStatus":1,"goodsCode":"102552","peroidInstalmentAmount":25,"goodsName":"【新人专享】魔兽世界点卡wow点卡15元"}]
             */

            private String bannerUrl;
            private List<GoodsBean> goods;

            public String getBannerUrl() {
                return bannerUrl;
            }

            public void setBannerUrl(String bannerUrl) {
                this.bannerUrl = bannerUrl;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * instalmentPeriods : 12
                 * goodTitle : 爱奇艺vip会员激活码1个月黄金月卡充值官方自动发货不支持TV端
                 * originalPrice : 300
                 * bannerId : 52
                 * thumbnailUrl : http://img.xiaoxiangyoupin.com/image/750x750_624824583010127872.jpg
                 * goodsOrder : 1
                 * directPaymentAmount : 0
                 * goodId : 4414
                 * goodsStatus : 1
                 * goodsCode : 102547
                 * peroidInstalmentAmount : 25
                 * goodsName : 【新人专享】爱奇艺vip会员1个月
                 */

                private int instalmentPeriods;
                private String goodTitle;
                private int originalPrice;
                private int bannerId;
                private String thumbnailUrl;
                private int goodsOrder;
                private int directPaymentAmount;
                private int goodId;
                private int goodsStatus;
                private String goodsCode;
                private int peroidInstalmentAmount;
                private String goodsName;

                public int getInstalmentPeriods() {
                    return instalmentPeriods;
                }

                public void setInstalmentPeriods(int instalmentPeriods) {
                    this.instalmentPeriods = instalmentPeriods;
                }

                public String getGoodTitle() {
                    return goodTitle;
                }

                public void setGoodTitle(String goodTitle) {
                    this.goodTitle = goodTitle;
                }

                public int getOriginalPrice() {
                    return originalPrice;
                }

                public void setOriginalPrice(int originalPrice) {
                    this.originalPrice = originalPrice;
                }

                public int getBannerId() {
                    return bannerId;
                }

                public void setBannerId(int bannerId) {
                    this.bannerId = bannerId;
                }

                public String getThumbnailUrl() {
                    return thumbnailUrl;
                }

                public void setThumbnailUrl(String thumbnailUrl) {
                    this.thumbnailUrl = thumbnailUrl;
                }

                public int getGoodsOrder() {
                    return goodsOrder;
                }

                public void setGoodsOrder(int goodsOrder) {
                    this.goodsOrder = goodsOrder;
                }

                public int getDirectPaymentAmount() {
                    return directPaymentAmount;
                }

                public void setDirectPaymentAmount(int directPaymentAmount) {
                    this.directPaymentAmount = directPaymentAmount;
                }

                public int getGoodId() {
                    return goodId;
                }

                public void setGoodId(int goodId) {
                    this.goodId = goodId;
                }

                public int getGoodsStatus() {
                    return goodsStatus;
                }

                public void setGoodsStatus(int goodsStatus) {
                    this.goodsStatus = goodsStatus;
                }

                public String getGoodsCode() {
                    return goodsCode;
                }

                public void setGoodsCode(String goodsCode) {
                    this.goodsCode = goodsCode;
                }

                public int getPeroidInstalmentAmount() {
                    return peroidInstalmentAmount;
                }

                public void setPeroidInstalmentAmount(int peroidInstalmentAmount) {
                    this.peroidInstalmentAmount = peroidInstalmentAmount;
                }

                public String getGoodsName() {
                    return goodsName;
                }

                public void setGoodsName(String goodsName) {
                    this.goodsName = goodsName;
                }
            }
        }
    }

    public static class HeadsBean {
        /**
         * code : 200
         */

        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
