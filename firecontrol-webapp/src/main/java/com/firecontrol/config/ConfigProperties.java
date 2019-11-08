package com.firecontrol.config;

import com.firecontrol.boot.FirecontrolWebappApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 把数据库连接池配置封装到bean并注入spring容器
 * Created by mariry on 2019/6/21.
 */
@Component
public class ConfigProperties {

    private static final Logger log = LoggerFactory.getLogger(ConfigProperties.class);

    //@Value("${spring.datasource.druid.url}")
    @Value("${spring.datasource.iotaccess.url}")
    private String jmuMpUrl;

    @Value("${spring.datasource.iotaccess.driver-class-name}")
    private String jmuMpDriverClassName;

    @Value("${spring.datasource.iotaccess.username}")
    private String jmuMpUsername;

    @Value("${spring.datasource.iotaccess.password}")
    private String jmuMpPassword;


    public String getJmuMpUrl() {
        return jmuMpUrl;
    }

    public void setJmuMpUrl(String jmuMpUrl) {
        this.jmuMpUrl = jmuMpUrl;
    }

    public String getJmuMpDriverClassName() {
        return jmuMpDriverClassName;
    }

    public void setJmuMpDriverClassName(String jmuMpDriverClassName) {
        this.jmuMpDriverClassName = jmuMpDriverClassName;
    }

    public String getJmuMpUsername() {
        return jmuMpUsername;
    }

    public void setJmuMpUsername(String jmuMpUsername) {
        this.jmuMpUsername = jmuMpUsername;
    }

    public String getJmuMpPassword() {
        return jmuMpPassword;
    }

    public void setJmuMpPassword(String jmuMpPassword) {
        this.jmuMpPassword = jmuMpPassword;
    }


    //平台管理( 云大 )的数据库连接

    @Value("${spring.datasource.smartcity.url}")
    private String ydUrl;

    @Value("${spring.datasource.smartcity.driver-class-name}")
    private String ydDriverClassName;

    @Value("${spring.datasource.smartcity.username}")
    private String ydUsername;

    @Value("${spring.datasource.smartcity.password}")
    private String ydPassword;

    public String getYdUrl() {
        return ydUrl;
    }

    public void setYdUrl(String ydUrl) {
        this.ydUrl = ydUrl;
    }

    public String getYdDriverClassName() {
        return ydDriverClassName;
    }

    public void setYdDriverClassName(String ydDriverClassName) {
        this.ydDriverClassName = ydDriverClassName;
    }

    public String getYdUsername() {
        return ydUsername;
    }

    public void setYdUsername(String ydUsername) {
        this.ydUsername = ydUsername;
    }

    public String getYdPassword() {
        return ydPassword;
    }

    public void setYdPassword(String ydPassword) {
        this.ydPassword = ydPassword;
    }

    /*    @Value("${spring.datasource.jmuv3.jdbc-url}")
    private String jmuv3Url;

    @Value("${spring.datasource.jmuv3.driver-class-name}")
    private String jmuv3DriverClassName;

    @Value("${spring.datasource.jmuv3.username}")
    private String jmuv3Username;

    @Value("${spring.datasource.jmuv3.password}")
    private String jmuv3Password;

    @Value("${spring.datasource.ccjoin-settlement.jdbc-url}")
    private String ccjoinSettlementUrl;

    @Value("${spring.datasource.ccjoin-settlement.driver-class-name}")
    private String ccjoinSettlementDriverClassName;

    @Value("${spring.datasource.ccjoin-settlement.username}")
    private String ccjoinSettlementUsername;

    @Value("${spring.datasource.ccjoin-settlement.password}")
    private String ccjoinSettlementPassword;

    @Value("${spring.datasource.ccjoin-settlement.type}")
    private String ccjoinSettlementType;*/

/*

    */
/**
     * jmuv3Url的取得
     *
     * @return String jmuv3Url
     *//*

    public String getJmuv3Url() {
        return jmuv3Url;
    }

    */
/**
     * jmuv3Url的设定
     *
     * @param jmuv3Url
     *            jmuv3Url
     *//*

    public void setJmuv3Url(String jmuv3Url) {
        this.jmuv3Url = jmuv3Url;
    }

    */
/**
     * jmuv3DriverClassName的取得
     *
     * @return String jmuv3DriverClassName
     *//*

    public String getJmuv3DriverClassName() {
        return jmuv3DriverClassName;
    }

    */
/**
     * jmuv3DriverClassName的设定
     *
     * @param jmuv3DriverClassName
     *            jmuv3DriverClassName
     *//*

    public void setJmuv3DriverClassName(String jmuv3DriverClassName) {
        this.jmuv3DriverClassName = jmuv3DriverClassName;
    }

    */
/**
     * jmuv3Username的取得
     *
     * @return String jmuv3Username
     *//*

    public String getJmuv3Username() {
        return jmuv3Username;
    }

    */
/**
     * jmuv3Username的设定
     *
     * @param jmuv3Username
     *            jmuv3Username
     *//*

    public void setJmuv3Username(String jmuv3Username) {
        this.jmuv3Username = jmuv3Username;
    }

    */
/**
     * jmuv3Password的取得
     *
     * @return String jmuv3Password
     *//*

    public String getJmuv3Password() {
        return jmuv3Password;
    }

    */
/**
     * jmuv3Password的设定
     *
     * @param jmuv3Password
     *            jmuv3Password
     *//*

    public void setJmuv3Password(String jmuv3Password) {
        this.jmuv3Password = jmuv3Password;
    }

    */
/**
     * ccjoinSettlementUrl的取得
     *
     * @return String ccjoinSettlementUrl
     *//*

    public String getCcjoinSettlementUrl() {
        return ccjoinSettlementUrl;
    }

    */
/**
     * ccjoinSettlementUrl的设定
     *
     * @param ccjoinSettlementUrl
     *            ccjoinSettlementUrl
     *//*

    public void setCcjoinSettlementUrl(String ccjoinSettlementUrl) {
        this.ccjoinSettlementUrl = ccjoinSettlementUrl;
    }

    */
/**
     * ccjoinSettlementDriverClassName的取得
     *
     * @return String ccjoinSettlementDriverClassName
     *//*

    public String getCcjoinSettlementDriverClassName() {
        return ccjoinSettlementDriverClassName;
    }

    */
/**
     * ccjoinSettlementDriverClassName的设定
     *
     * @param ccjoinSettlementDriverClassName
     *            ccjoinSettlementDriverClassName
     *//*

    public void setCcjoinSettlementDriverClassName(String ccjoinSettlementDriverClassName) {
        this.ccjoinSettlementDriverClassName = ccjoinSettlementDriverClassName;
    }

    */
/**
     * ccjoinSettlementUsername的取得
     *
     * @return String ccjoinSettlementUsername
     *//*

    public String getCcjoinSettlementUsername() {
        return ccjoinSettlementUsername;
    }

    */
/**
     * ccjoinSettlementUsername的设定
     *
     * @param ccjoinSettlementUsername
     *            ccjoinSettlementUsername
     *//*

    public void setCcjoinSettlementUsername(String ccjoinSettlementUsername) {
        this.ccjoinSettlementUsername = ccjoinSettlementUsername;
    }

    */
/**
     * ccjoinSettlementPassword的取得
     *
     * @return String ccjoinSettlementPassword
     *//*

    public String getCcjoinSettlementPassword() {
        return ccjoinSettlementPassword;
    }

    */
/**
     * ccjoinSettlementPassword的设定
     *
     * @param ccjoinSettlementPassword
     *            ccjoinSettlementPassword
     *//*

    public void setCcjoinSettlementPassword(String ccjoinSettlementPassword) {
        this.ccjoinSettlementPassword = ccjoinSettlementPassword;
    }

    */
/**
     * ccjoinSettlementType的取得
     *
     * @return String ccjoinSettlementType
     *//*

    public String getCcjoinSettlementType() {
        return ccjoinSettlementType;
    }

    */
/**
     * ccjoinSettlementType的设定
     *
     * @param ccjoinSettlementType
     *            ccjoinSettlementType
     *//*

    public void setCcjoinSettlementType(String ccjoinSettlementType) {
        this.ccjoinSettlementType = ccjoinSettlementType;
    }
*/


}
