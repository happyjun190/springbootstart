package com.ysb.web.model.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangchun on 2016/12/1.
 */
@ApiModel
public class BaseInModel {

    @ApiModelProperty(value = "用户token", position=-2, example="iamatoken7b14a1986df8888")
    private String usertoken;

    @ApiModelProperty(value = "authcode，非必须", position=-1, example="123456")
    private String authcode;

    /** 终端类型：Android, iOS, web, pc */
    @ApiModelProperty(hidden = true)
    private String platform;

    /** 前端软件版本号 */
    @ApiModelProperty(hidden = true)
    private String version;

    /** 设备id */
    @ApiModelProperty(hidden = true)
    private String ueDeviceId;

    /** 经度 */
    @ApiModelProperty(hidden = true)
    private Double ueLon;

    /** 纬度 */
    @ApiModelProperty(hidden = true)
    private Double ueLat;

    /** 安卓特有，不知是什么。。。 */
    @ApiModelProperty(hidden = true)
    private String invokePath;
    /** 安卓特有，不知是什么。。。 */
    @ApiModelProperty(hidden = true)
    private String currentPath;

    /** 产品型号 */
    @ApiModelProperty(hidden = true)
    private String modelName;

    /** 厂商名 */
    @ApiModelProperty(hidden = true)
    private String manufacturerName;

    /** 安卓特有，安卓api版本 */
    @ApiModelProperty(hidden = true)
    private Integer androidAPILevel;

    /** 是否有wifi，0-无网络， 1-wifi，2-2g网络， 3-3g网络，4-4g网络 */
    @ApiModelProperty(hidden = true)
    private Integer iW;

    /** 安卓特有，IMEI 号 */
    @ApiModelProperty(hidden = true)
    private String imei;

    /** 是否越狱，1-越狱；0-非越狱 */
    @ApiModelProperty(hidden = true)
    private Integer iJ;

    /** iOS特有，系统版本号 */
    @ApiModelProperty(hidden = true)
    private String osVersion;

    /**  Android独有，当前堆栈页面总数 */
    @ApiModelProperty(hidden = true)
    private String als;

    /** 扩展字段，可记录上述字段内容以外信息 */
    @ApiModelProperty(hidden = true)
    private String ex;

    /** --------------------分割线，以下为log所需参数------------------------  */
    @ApiModelProperty(hidden = true)
    private int userId;

    @ApiModelProperty(hidden = true)
    private int cityId;

    @ApiModelProperty(hidden = true)
    private String cityName;

    @ApiModelProperty(hidden = true)
    private int storeId;

    public BaseInModel() {
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public String getPlatform() {
        return platform==null ? "unknown" : platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version==null ? "" : version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUeDeviceId() {
        return ueDeviceId;
    }

    public void setUeDeviceId(String ueDeviceId) {
        this.ueDeviceId = ueDeviceId;
    }

    public Double getUeLon() {
        return ueLon;
    }

    public void setUeLon(Double ueLon) {
        this.ueLon = ueLon;
    }

    public Double getUeLat() {
        return ueLat;
    }

    public void setUeLat(Double ueLat) {
        this.ueLat = ueLat;
    }

    public String getInvokePath() {
        return invokePath;
    }

    public void setInvokePath(String invokePath) {
        this.invokePath = invokePath;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Integer getAndroidAPILevel() {
        return androidAPILevel;
    }

    public void setAndroidAPILevel(Integer androidAPILevel) {
        this.androidAPILevel = androidAPILevel;
    }

    public Integer getiW() {
        return iW;
    }

    public void setiW(Integer iW) {
        this.iW = iW;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getiJ() {
        return iJ;
    }

    public void setiJ(Integer iJ) {
        this.iJ = iJ;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAls() {
        return als;
    }

    public void setAls(String als) {
        this.als = als;
    }
}
