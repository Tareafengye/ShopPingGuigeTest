package com.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 商品详情
 */
public class GoodsBean implements Serializable {

    private List<SkuInfoBean> sku_info;

    public List<SkuInfoBean> getSku_info() {
        return sku_info;
    }

    public void setSku_info(List<SkuInfoBean> sku_info) {
        this.sku_info = sku_info;
    }

    public static class SkuInfoBean {
        /**
         * sku_id : 203
         * sku_name : 颜色
         * value_list : [{"value_id":"893","value_name":"白色"},{"value_id":"894","value_name":"黑色"},{"value_id":"944","value_name":"蓝色"}]
         */

        private int sku_id;
        private String sku_name;
        private List<ValueListBean> value_list;

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public String getSku_name() {
            return sku_name;
        }

        public void setSku_name(String sku_name) {
            this.sku_name = sku_name;
        }

        public List<ValueListBean> getValue_list() {
            return value_list;
        }

        public void setValue_list(List<ValueListBean> value_list) {
            this.value_list = value_list;
        }

        public static class ValueListBean {
            /**
             * value_id : 893
             * value_name : 白色
             */

            private int value_id;
            private String value_name;

            public int getValue_id() {
                return value_id;
            }

            public void setValue_id(int value_id) {
                this.value_id = value_id;
            }

            public String getValue_name() {
                return value_name;
            }

            public void setValue_name(String value_name) {
                this.value_name = value_name;
            }
        }
    }
}
