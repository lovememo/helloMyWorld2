package com.opm.core.agency.model;

/**
 * Created by kfzx-jinjf on 2016/12/5.
 * 外部机构Model类
 */
public class AgencyModel {
    public static int AGENCY_STATUS_TYPE_UNKNOWN = 0;
    public static int AGENCY_STATUS_TYPE_VALUE_1 = 1;
    public static int AGENCY_STATUS_TYPE_VALUE_2 = 2;

    public static String  AGENCY_INTERFACE_USER_UNKNOWN = "UN";
    public static String  AGENCY_INTERFACE_USER_VALUE_Y = "Y";
    public static String  AGENCY_INTERFACE_USER_VALUE_N = "N";

    public static String AGENCY_STATUS_FLAG_UNKNOWN = "UN";
    public static String AGENCY_STATUS_FLAG_VALUE_1 = "1";
    public static String AGENCY_STATUS_FLAG_VALUE_2 = "2";

    public static String AGENCY_INNER_FLAG_UNKNOWN="UN";
    public static String AGENCY_INNER_FLAG_VALUE_Y = "Y";
    public static String AGENCY_INNER_FLAG_VALUE_N = "N";

    public static String String = "UN";
    public static String AGENCY_INTERFACE_STATE_VALUE_Y = "Y";
    public static String AGENCY_INTERFACE_STATE_VALUE_N = "N";

    private String trdNo;
    /**
     * 申请编号
     */
    private String appNo;

    /**
     * 外部机构编码
     */
    private String id;

    /**
     * 资格类型
     */
    private int instuType;

    /**
     * 证书编号
     */
    private String certId;

    /**
     * 机构名称
     */
    private String agencyName;

    /**
     * 机构简称
     */
    private String agencySname;

    /**
     * 账户名
     */
    private String acctName;

    /**
     * 账号
     */
    private String acctNo;

    /**
     * 开户行
     */
    private String acctBranch;

    /**
     * 注册资本
     */
    private String regPrcpl;

    /**
     * 资格期限
     */
    private String expiration;

    /**
     * 员工人数
     */
    private String employerNum;

    /**
     * 管理人介绍
     */
    private String intro;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 联系人
     */
    private String busiMgr;

    /**
     * 联系人职务
     */
    private String position;

    /**
     * 注册地址
     */
    private String addr;

    /**
     * 通信地址
     */
    private String postAddress;

    /**
     * 网络地址
     */
    private String networkAddress;

    /**
     * 备注
     */
    private String memo;

    /**
     * 邮编
     */
    private String zipcode;

    /**
     * 传真
     */
    private String fax;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 机构代码
     */
    private String agencyCode;

    /**
     * 机构号
     */
    private String agencyNo;

    /**
     * 是否接口用户
     */
    private String    interfaceUser;

    /**
     * 资格标志仅受托人用1、理事会2、法人
     */
    private String    statusFlag;

    /**
     * 合作方接收服务器IP地址
     */
    private String srvip;

    /**
     * 合作方接收服务器端口号
     */
    private String srvpOrt;

    /**
     * 是否工行(1、是，0、否)
     */
    private String    innerFlag;

    /**
     * 法定代表人
     */
    private String legRepresentative;

    /**
     * 新增用户所属机构
     */
    private String newStru;

    /**
     * 开户网点
     */
    private String openBranch;

    /**
     *文件发送加密密码
     */
    private String zipEnPassword;

    /**
     * 文件发送解密密码
     */
    private String zipDePassword;

    /**
     * 接口状态（0打开；1关闭）
     */
    private String    interfaceState;

    /**
     * 年金基金管理机构代码（1.0使用）
     */
    private String fundCode;

    /**
     * 报表密码机构层
     */
    private String rptPwdBranch;


    /**
     * 报表密码生效时间机构层
     */
    private String modifyTimeRptPwdBranch;

    public AgencyModel() {

    }

    public String getTrdNo() {
        return trdNo;
    }
    public void setTrdNo(String trdNo) {
        this.trdNo = trdNo;
    }

    /**
     * 申请编码
     */
    public String getAppNo() {
        return this.appNo;
    }


    /**
     * 申请编码
     */
    public void setAppNo(String appNo) {
        this.appNo=appNo;
    }



    /**
     * 外部机构编码
     */
    public String getId() {
        return id;
    }

    /**
     * 外部机构编码
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 资格类型
     */
    public int getInstuType() {
        return instuType;
    }

    /**
     * 资格类型
     */
    public void setInstuType(int instuType) {
        this.instuType = instuType;
    }

    /**
     * 证书编号
     */
    public String getCertId() {
        return certId;
    }

    /**
     * 证书编号
     */
    public void setCertId(String certId) {
        this.certId = certId;
    }

    /**
     * 机构名称
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * 机构名称
     */
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    /**
     * 机构简称
     */
    public String getAgencySname() {
        return agencySname;
    }

    /**
     * 机构简称
     */
    public void setAgencySname(String agencySname) {
        this.agencySname = agencySname;
    }

    /**
     * 账户名
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 账户名
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    /**
     * 账号
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 账号
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * 开户行
     */
    public String getAcctBranch() {
        return acctBranch;
    }

    /**
     * 开户行
     */
    public void setAcctBranch(String acctBranch) {
        this.acctBranch = acctBranch;
    }

    /**
     * 注册资本
     */
    public String getRegPrcpl() {
        return regPrcpl;
    }

    /**
     * 注册资本
     */
    public void setRegPrcpl(String regPrcpl) {
        this.regPrcpl = regPrcpl;
    }

    /**
     * 资格期限
     */
    public String getExpiration() {
        return expiration;
    }

    /**
     * 资格期限
     */
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    /**
     * 员工人数
     */
    public String getEmployerNum() {
        return employerNum;
    }

    /**
     * 员工人数
     */
    public void setEmployerNum(String employerNum) {
        this.employerNum = employerNum;
    }

    /**
     * 管理人介绍
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 管理人介绍
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 联系电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 联系电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 联系人
     */
    public String getBusiMgr() {
        return busiMgr;
    }

    /**
     * 联系人
     */
    public void setBusiMgr(String busiMgr) {
        this.busiMgr = busiMgr;
    }

    /**
     * 联系人职务
     */
    public String getPosition() {
        return position;
    }

    /**
     * 联系人职务
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 注册地址
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 注册地址
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(String networkAddress) {
        this.networkAddress = networkAddress;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getAgencyNo() {
        return agencyNo;
    }

    public void setAgencyNo(String agencyNo) {
        this.agencyNo = agencyNo;
    }

    public String getInterfaceUser() {
        return interfaceUser;
    }

    public void setInterfaceUser(String interfaceUser) {
        this.interfaceUser = interfaceUser;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getSrvip() {
        return srvip;
    }

    public void setSrvip(String srvip) {
        this.srvip = srvip;
    }

    public String getSrvpOrt() {
        return srvpOrt;
    }

    public void setSrvpOrt(String srvpOrt) {
        this.srvpOrt = srvpOrt;
    }

    public String getInnerFlag() {
        return innerFlag;
    }

    public void setInnerFlag(String innerFlag) {
        this.innerFlag = innerFlag;
    }

    public String getLegRepresentative() {
        return legRepresentative;
    }

    public void setLegRepresentative(String legRepresentative) {
        this.legRepresentative = legRepresentative;
    }

    public String getNewStru() {
        return newStru;
    }

    public void setNewStru(String newStru) {
        this.newStru = newStru;
    }

    public String getOpenBranch() {
        return openBranch;
    }

    public void setOpenBranch(String openBranch) {
        this.openBranch = openBranch;
    }

    public String getZipEnPassword() {
        return zipEnPassword;
    }

    public void setZipEnPassword(String zipEnPassword) {
        this.zipEnPassword = zipEnPassword;
    }

    public String getZipDePassword() {
        return zipDePassword;
    }

    public void setZipDePassword(String zipDePassword) {
        this.zipDePassword = zipDePassword;
    }

    public String getInterfaceState() {
        return interfaceState;
    }

    public void setInterfaceState(String interfaceState) {
        this.interfaceState = interfaceState;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getRptPwdBranch() {
        return rptPwdBranch;
    }

    public void setRptPwdBranch(String rptPwdBranch) {
        this.rptPwdBranch = rptPwdBranch;
    }

    public String getModifyTimeRptPwdBranch() {
        return modifyTimeRptPwdBranch;
    }

    public void setModifyTimeRptPwdBranch(String modifyTimeRptPwdBranch) {
        this.modifyTimeRptPwdBranch = modifyTimeRptPwdBranch;
    }

    public void reset() {
        appNo =null;
        id = null;
        instuType = AGENCY_STATUS_TYPE_UNKNOWN;
        certId = null;
        agencyName = null;
        agencySname = null;
        acctName = null;
        acctNo = null;
        acctBranch = null;
        regPrcpl = null;
        expiration = null;
        employerNum = null;
        intro = null;
        tel = null;
        busiMgr = null;
        position = null;
        addr = null;
        postAddress = null;
        networkAddress = null;
        memo = null;
        zipcode = null;
        fax = null;
        email = null;
        agencyCode = null;
        agencyNo = null;
        interfaceUser = AGENCY_INTERFACE_USER_UNKNOWN;
        statusFlag = AGENCY_STATUS_FLAG_UNKNOWN;
        srvip = null;
        srvpOrt = null;
        innerFlag = AGENCY_INNER_FLAG_UNKNOWN;
        legRepresentative = null;
        newStru = null;
        openBranch = null;
        zipEnPassword = null;
        zipDePassword = null;
        interfaceState = String;
        fundCode = null;
        rptPwdBranch = null;
        modifyTimeRptPwdBranch = null;
    }

    public void assignFrom(AgencyModel AObj) {
        if (AObj == null) {
            reset();
            return;
        }
        id = AObj.id;
        instuType = AObj.instuType;
        certId = AObj.certId;
        agencyName = AObj.agencyName;
        agencySname = AObj.agencySname;
        acctName = AObj.acctName;
        acctNo = AObj.acctNo;
        acctBranch = AObj.acctBranch;
        regPrcpl = AObj.regPrcpl;
        expiration = AObj.expiration;
        employerNum = AObj.employerNum;
        intro = AObj.intro;
        tel = AObj.tel;
        busiMgr = AObj.busiMgr;
        position = AObj.position;
        addr = AObj.addr;
        postAddress = AObj.postAddress;
        networkAddress = AObj.networkAddress;
        memo = AObj.memo;
        zipcode = AObj.zipcode;
        fax = AObj.fax;
        email = AObj.email;
        agencyCode = AObj.agencyCode;
        agencyNo = AObj.agencyNo;
        interfaceUser = AObj.interfaceUser;
        statusFlag = AObj.statusFlag;
        srvip = AObj.srvip;
        srvpOrt = AObj.srvpOrt;
        innerFlag = AObj.innerFlag;
        legRepresentative = AObj.legRepresentative;
        newStru = AObj.newStru;
        openBranch = AObj.openBranch;
        zipEnPassword = AObj.zipEnPassword;
        zipDePassword = AObj.zipDePassword;
        interfaceState = AObj.interfaceState;
        fundCode = AObj.fundCode;
        rptPwdBranch = AObj.rptPwdBranch;
        modifyTimeRptPwdBranch = AObj.modifyTimeRptPwdBranch;
        appNo=AObj.appNo;
    }

    public static void main(String[] args) {
        AgencyModel agency = new AgencyModel();
        agency.setAcctName("xxx机构！");
        System.out.println("args = [" + agency.getAcctName() + "]");
    }
}
