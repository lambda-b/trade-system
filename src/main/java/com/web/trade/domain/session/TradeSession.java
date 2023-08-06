package com.web.trade.domain.session;

import java.math.BigDecimal;

import com.web.trade.domain.Security;
import com.web.trade.enums.TradeSessionStatus;

/**
 * TradeSession
 */
public interface TradeSession {

	/**
	 * セッション状態を取得
	 * @return セッション状態
	 */
	public TradeSessionStatus getStatus();

	/**
	 * 市場が開いているかどうか
	 * @return true: あいている / false: しまっている
	 */
	public boolean isExchangeOpen();

	/**
	 * 注文が出せるかどうか
	 * @return true: 注文が出せる / false: 注文が出せない
	 */
	public boolean isOrderAcceptable();

	/**
	 * セキュリティトークンの参考価格を算出
	 * @return 参考価格
	 */
	public BigDecimal getReferencePrice(Security security);

}
