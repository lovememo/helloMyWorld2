package com.opm.core.app.template.trade;

/**
 * 每个交易的配置信息
 * @author kfzx-zhengyy1
 *
 */
public class TradeInfo {
	private ITrade trade;
	private Object tradeParam;
	private Long tradeOrder;

	public TradeInfo(ITrade trade, Object userParam,long order) {
		super();
		this.trade = trade;
		this.tradeParam = userParam;
		this.tradeOrder = order;
	}

	public ITrade getTrade() {
		return trade;
	}

	public void setTrade(ITrade trade) {
		this.trade = trade;
	}

	public Object getParams() {
		return tradeParam;
	}

	public void setTradeParam(Object tradeParam) {
		this.tradeParam = tradeParam;
	}

	public Long getTradeOrder() {
		return tradeOrder;
	}

	public void setTradeOrder(Long tradeOrder) {
		this.tradeOrder = tradeOrder;
	}
}