package com.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 商品详情
 */
public class GoodsDetailBean implements Serializable {
    String business_status;
    List<String> carousel_images;
    String cate_id;
    String contact_name;
    String contact_way;
    String expire_time;
    String goods_unit_name;
    String id;
    String loc_address;
    String loc_city;
    String loc_country;
    String loc_province;
    String main_img;
    String name;
    String place_origin;
    String product_code;
    List<Properties> properties;
    String secondary;
    List<SkuList> sku_list;
    String sku_pkid;
    String store_id;
    String synopsis;
    String template_id;
    String total_sales;
    String uid;
    String update_time;
    String view_cnt;
    String weight;
    String favorite_cnt;
    String under_guaranty;
    String consignment_time;
    String can_mixed_batch;
    String onshelf;
    String has_sample;
    String has_receipt;
    String support_replace;

    List<SkuInfo> sku_info;

    public List<SkuInfo> getSku_info() {
        return sku_info;
    }

    public void setSku_info(List<SkuInfo> sku_info) {
        this.sku_info = sku_info;
    }

    public String getCan_mixed_batch() {
        return can_mixed_batch;
    }

    public void setCan_mixed_batch(String can_mixed_batch) {
        this.can_mixed_batch = can_mixed_batch;
    }

    public String getFavorite_cnt() {
        return favorite_cnt;
    }

    public void setFavorite_cnt(String favorite_cnt) {
        this.favorite_cnt = favorite_cnt;
    }

    public String getBusiness_status() {
        return business_status;
    }

    public void setBusiness_status(String business_status) {
        this.business_status = business_status;
    }

    public List<String> getCarousel_images() {
        return carousel_images;
    }

    public void setCarousel_images(List<String> carousel_images) {
        this.carousel_images = carousel_images;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getConsignment_time() {
        return consignment_time;
    }

    public void setConsignment_time(String consignment_time) {
        this.consignment_time = consignment_time;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_way() {
        return contact_way;
    }

    public void setContact_way(String contact_way) {
        this.contact_way = contact_way;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public String getGoods_unit_name() {
        return goods_unit_name;
    }

    public void setGoods_unit_name(String goods_unit_name) {
        this.goods_unit_name = goods_unit_name;
    }

    public String getHas_receipt() {
        return has_receipt;
    }

    public void setHas_receipt(String has_receipt) {
        this.has_receipt = has_receipt;
    }

    public String getHas_sample() {
        return has_sample;
    }

    public void setHas_sample(String has_sample) {
        this.has_sample = has_sample;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoc_address() {
        return loc_address;
    }

    public void setLoc_address(String loc_address) {
        this.loc_address = loc_address;
    }

    public String getLoc_city() {
        return loc_city;
    }

    public void setLoc_city(String loc_city) {
        this.loc_city = loc_city;
    }

    public String getLoc_country() {
        return loc_country;
    }

    public void setLoc_country(String loc_country) {
        this.loc_country = loc_country;
    }

    public String getLoc_province() {
        return loc_province;
    }

    public void setLoc_province(String loc_province) {
        this.loc_province = loc_province;
    }

    public String getMain_img() {
        return main_img;
    }

    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnshelf() {
        return onshelf;
    }

    public void setOnshelf(String onshelf) {
        this.onshelf = onshelf;
    }

    public String getPlace_origin() {
        return place_origin;
    }

    public void setPlace_origin(String place_origin) {
        this.place_origin = place_origin;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public List<SkuList> getSku_list() {
        return sku_list;
    }

    public void setSku_list(List<SkuList> sku_list) {
        this.sku_list = sku_list;
    }

    public String getSku_pkid() {
        return sku_pkid;
    }

    public void setSku_pkid(String sku_pkid) {
        this.sku_pkid = sku_pkid;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getSupport_replace() {
        return support_replace;
    }

    public void setSupport_replace(String support_replace) {
        this.support_replace = support_replace;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(String total_sales) {
        this.total_sales = total_sales;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUnder_guaranty() {
        return under_guaranty;
    }

    public void setUnder_guaranty(String under_guaranty) {
        this.under_guaranty = under_guaranty;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(String view_cnt) {
        this.view_cnt = view_cnt;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public class SkuList implements Serializable {
        String cnt1;
        String cnt2;
        String cnt3;
        String icon_url;
        String ori_price;
        String price;
        String price2;
        String price3;
        String product_code;
        String quantity;
        String sku_desc;
        String sku_id;
        String sku_pkid;
        boolean selected;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public String getCnt1() {
            return cnt1;
        }

        public void setCnt1(String cnt1) {
            this.cnt1 = cnt1;
        }

        public String getCnt2() {
            return cnt2;
        }

        public void setCnt2(String cnt2) {
            this.cnt2 = cnt2;
        }

        public String getCnt3() {
            return cnt3;
        }

        public void setCnt3(String cnt3) {
            this.cnt3 = cnt3;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public String getOri_price() {
            return ori_price;
        }

        public void setOri_price(String ori_price) {
            this.ori_price = ori_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice2() {
            return price2;
        }

        public void setPrice2(String price2) {
            this.price2 = price2;
        }

        public String getPrice3() {
            return price3;
        }

        public void setPrice3(String price3) {
            this.price3 = price3;
        }

        public String getProduct_code() {
            return product_code;
        }

        public void setProduct_code(String product_code) {
            this.product_code = product_code;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getSku_desc() {
            return sku_desc;
        }

        public void setSku_desc(String sku_desc) {
            this.sku_desc = sku_desc;
        }

        public String getSku_id() {
            return sku_id;
        }

        public void setSku_id(String sku_id) {
            this.sku_id = sku_id;
        }

        public String getSku_pkid() {
            return sku_pkid;
        }

        public void setSku_pkid(String sku_pkid) {
            this.sku_pkid = sku_pkid;
        }

    }

    public class Properties {
        String prop_id;
        String prop_name;
        List<C> prop_value_list;

        public String getProp_id() {
            return prop_id;
        }

        public void setProp_id(String prop_id) {
            this.prop_id = prop_id;
        }

        public String getProp_name() {
            return prop_name;
        }

        public void setProp_name(String prop_name) {
            this.prop_name = prop_name;
        }

        public List<C> getProp_value_list() {
            return prop_value_list;
        }

        public void setProp_value_list(List<C> prop_value_list) {
            this.prop_value_list = prop_value_list;
        }

    }

    public class C {
        String value_id;
        String value_name;

        public String getValue_id() {
            return value_id;
        }

        public void setValue_id(String value_id) {
            this.value_id = value_id;
        }

        public String getValue_name() {
            return value_name;
        }

        public void setValue_name(String value_name) {
            this.value_name = value_name;
        }

    }
    public class SkuInfo {
        String sku_id;
        String sku_name;

        List<ValueList> value_list;

        public List<ValueList> getValue_list() {
            return value_list;
        }

        public void setValue_list(List<ValueList> value_list) {
            this.value_list = value_list;
        }

        public String getSku_name() {
            return sku_name;
        }

        public void setSku_name(String sku_name) {
            this.sku_name = sku_name;
        }

        public String getSku_id() {
            return sku_id;
        }

        public void setSku_id(String sku_id) {
            this.sku_id = sku_id;
        }
    }
    public class ValueList {
        String value_id;
        String value_name;

        public String getValue_id() {
            return value_id;
        }

        public void setValue_id(String value_id) {
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
