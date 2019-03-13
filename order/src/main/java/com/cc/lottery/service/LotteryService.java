/**
 * 
 */
package com.cc.lottery.service;

import com.cc.common.web.Page;
import com.cc.lottery.bean.LotteryBean;
import com.cc.lottery.bean.LotteryCustomerBean;
import com.cc.lottery.form.LotteryCustomerQueryForm;
import com.cc.lottery.form.LotteryQueryForm;
import com.cc.lottery.result.LotteryCustomerListResult;
import com.cc.lottery.result.LotteryListResult;

/**
 * @author Administrator
 *
 */
public interface LotteryService {

	/**
	 * 保存抽奖
	 * @param lotteryBean
	 */
	void saveLottery(LotteryBean lotteryBean);
	
	/**
	 * 分页查询抽奖列表
	 * @param form
	 * @return
	 */
	Page<LotteryListResult> queryLotteryPage(LotteryQueryForm form);

	/**
	 * 修改抽奖
	 * @param lotteryBean
	 */
	void updateLottery(LotteryBean lotteryBean);
	
	/**
	 * 分页查询中奖列表
	 * @param form
	 * @return
	 */
	Page<LotteryCustomerListResult> queryLotteryCustomerPage(LotteryCustomerQueryForm form);
	
	/**
	 * 查询用户参与抽奖次数
	 * @param customerId
	 * @param lotteryId
	 * @return
	 */
	int queryLotteryCustomerCount(Long customerId, Long lotteryId);
	
	/**
	 * 保存抽奖结果
	 * @param lotteryCustomerBean
	 */
	void saveLotteryCustomer(LotteryCustomerBean lotteryCustomerBean);
}
