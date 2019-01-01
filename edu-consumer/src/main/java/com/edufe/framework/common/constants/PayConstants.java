package com.edufe.framework.common.constants;

public class PayConstants {
	
	/** 微信支付统一下单接口地址*/
    public static final String UNIFLED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /** 微信支付查询订单接口地址*/
    public static final String QUERY_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    /** 微信支付回调接口*/
    public static final String RETURN_QC_URL = "/bgReturn.action";
    
    
    /**
	 * 支付平台类型 BEGIN
	 */
	/** 微信-支付平台类型 **/
	public static final String PAYTYPE_WECHAT = "0";
	/** 支付宝-支付平台类型 **/
	public static final String PAYTYPE_ALIPAY = "1";
	/** 上海银联-支付平台类型 **/
	public static final String PAYTYPE_CHINAPAY = "2";
	/**
	 * 支付平台类型 END
	 */
	
	/**
	 * 交易类型 BEGIN
	 */
	/** 微信支付方式-扫码支付*/
    public static final String TRADE_TYPE_NATIVE = "NATIVE";
    /** 微信支付方式-公众号支付*/
    public static final String TRADE_TYPE_JSAPI = "JSAPI";
	/**
	 * 交易类型 END
	 */
    
    /**
	 * 交易状态 BEGIN
	 *  0: 待支付
		1：支付成功
		2：支付失败
		9：其它
	 */
	/** 0: 待支付 */
    public static final String TRADE_STATUS_WAIT = "0";
    /** 1：支付成功 */
    public static final String TRADE_STATUS_SUCCESS = "1";
    /** 2：支付失败 */
    public static final String TRADE_STATUS_FAIL = "2";
    /** 9：其它  */
    public static final String TRADE_STATUS_OTHER = "9";
	/**
	 * 交易状态 END
	 */
}
