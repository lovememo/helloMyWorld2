package com.opm.core.agency.model;

/**
 * Created by kfzx-jinjf on 2016/12/5.
 * �ⲿ����Model��
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
     * ������
     */
    private String appNo;

    /**
     * �ⲿ��������
     */
    private String id;

    /**
     * �ʸ�����
     */
    private int instuType;

    /**
     * ֤����
     */
    private String certId;

    /**
     * ��������
     */
    private String agencyName;

    /**
     * �������
     */
    private String agencySname;

    /**
     * �˻���
     */
    private String acctName;

    /**
     * �˺�
     */
    private String acctNo;

    /**
     * ������
     */
    private String acctBranch;

    /**
     * ע���ʱ�
     */
    private String regPrcpl;

    /**
     * �ʸ�����
     */
    private String expiration;

    /**
     * Ա������
     */
    private String employerNum;

    /**
     * �����˽���
     */
    private String intro;

    /**
     * ��ϵ�绰
     */
    private String tel;

    /**
     * ��ϵ��
     */
    private String busiMgr;

    /**
     * ��ϵ��ְ��
     */
    private String position;

    /**
     * ע���ַ
     */
    private String addr;

    /**
     * ͨ�ŵ�ַ
     */
    private String postAddress;

    /**
     * �����ַ
     */
    private String networkAddress;

    /**
     * ��ע
     */
    private String memo;

    /**
     * �ʱ�
     */
    private String zipcode;

    /**
     * ����
     */
    private String fax;

    /**
     * ��������
     */
    private String email;

    /**
     * ��������
     */
    private String agencyCode;

    /**
     * ������
     */
    private String agencyNo;

    /**
     * �Ƿ�ӿ��û�
     */
    private String    interfaceUser;

    /**
     * �ʸ��־����������1�����»�2������
     */
    private String    statusFlag;

    /**
     * ���������շ�����IP��ַ
     */
    private String srvip;

    /**
     * ���������շ������˿ں�
     */
    private String srvpOrt;

    /**
     * �Ƿ���(1���ǣ�0����)
     */
    private String    innerFlag;

    /**
     * ����������
     */
    private String legRepresentative;

    /**
     * �����û���������
     */
    private String newStru;

    /**
     * ��������
     */
    private String openBranch;

    /**
     *�ļ����ͼ�������
     */
    private String zipEnPassword;

    /**
     * �ļ����ͽ�������
     */
    private String zipDePassword;

    /**
     * �ӿ�״̬��0�򿪣�1�رգ�
     */
    private String    interfaceState;

    /**
     * ���������������루1.0ʹ�ã�
     */
    private String fundCode;

    /**
     * �������������
     */
    private String rptPwdBranch;


    /**
     * ����������Чʱ�������
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
     * �������
     */
    public String getAppNo() {
        return this.appNo;
    }


    /**
     * �������
     */
    public void setAppNo(String appNo) {
        this.appNo=appNo;
    }



    /**
     * �ⲿ��������
     */
    public String getId() {
        return id;
    }

    /**
     * �ⲿ��������
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * �ʸ�����
     */
    public int getInstuType() {
        return instuType;
    }

    /**
     * �ʸ�����
     */
    public void setInstuType(int instuType) {
        this.instuType = instuType;
    }

    /**
     * ֤����
     */
    public String getCertId() {
        return certId;
    }

    /**
     * ֤����
     */
    public void setCertId(String certId) {
        this.certId = certId;
    }

    /**
     * ��������
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * ��������
     */
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    /**
     * �������
     */
    public String getAgencySname() {
        return agencySname;
    }

    /**
     * �������
     */
    public void setAgencySname(String agencySname) {
        this.agencySname = agencySname;
    }

    /**
     * �˻���
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * �˻���
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    /**
     * �˺�
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * �˺�
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * ������
     */
    public String getAcctBranch() {
        return acctBranch;
    }

    /**
     * ������
     */
    public void setAcctBranch(String acctBranch) {
        this.acctBranch = acctBranch;
    }

    /**
     * ע���ʱ�
     */
    public String getRegPrcpl() {
        return regPrcpl;
    }

    /**
     * ע���ʱ�
     */
    public void setRegPrcpl(String regPrcpl) {
        this.regPrcpl = regPrcpl;
    }

    /**
     * �ʸ�����
     */
    public String getExpiration() {
        return expiration;
    }

    /**
     * �ʸ�����
     */
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    /**
     * Ա������
     */
    public String getEmployerNum() {
        return employerNum;
    }

    /**
     * Ա������
     */
    public void setEmployerNum(String employerNum) {
        this.employerNum = employerNum;
    }

    /**
     * �����˽���
     */
    public String getIntro() {
        return intro;
    }

    /**
     * �����˽���
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * ��ϵ�绰
     */
    public String getTel() {
        return tel;
    }

    /**
     * ��ϵ�绰
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * ��ϵ��
     */
    public String getBusiMgr() {
        return busiMgr;
    }

    /**
     * ��ϵ��
     */
    public void setBusiMgr(String busiMgr) {
        this.busiMgr = busiMgr;
    }

    /**
     * ��ϵ��ְ��
     */
    public String getPosition() {
        return position;
    }

    /**
     * ��ϵ��ְ��
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * ע���ַ
     */
    public String getAddr() {
        return addr;
    }

    /**
     * ע���ַ
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
        agency.setAcctName("xxx������");
        System.out.println("args = [" + agency.getAcctName() + "]");
    }
}
