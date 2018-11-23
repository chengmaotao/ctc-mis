package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 区块链交易表
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-02 18:35:29
 */
@TableName("tbl_bc_transaction")
public class TblBcTransactionEntity implements Serializable {
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getInorout() {
		return inorout;
	}
	public void setInorout(String inorout) {
		this.inorout = inorout;
	}
	private static final long serialVersionUID = 1L;

	/**
	 * 交易ID
	 */
	@TableId
	private String trxId;
	/**
	 * 快号ID
	 */
	private String blockId;
	/**
	 * 块号
	 */
	private Long blockNum;
	/**
	 * 出账地址
	 */
	@TableField(exist = false)
	private String account;
	@TableField(exist = false)
	private String inorout;
	public String getTime() {
		if(time==null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy0-MM-dd HH:mm:ss");
		return sdf.format(trxTime);
	}
	public void setTime(String time) {
		this.time = time;
	}
	@TableField(exist = false)
	private String time;
	
	private String fromAccount;
	/**
	 * 出账名称
	 */
	private String fromAccountName;
	/**
	 * 入账地址
	 */
	private String toAccount;
	/**
	 * 入账名称
	 */
	private String toAccountName;
	/**
	 * 出账金额
	 */
	private Long amount;
	/**
	 * 交易手续费
	 */
	private Long fee;
	/**
	 * 备注信息
	 */
	private String memo;
	/**
	 * 交易被确认时间
	 */
	private Date trxTime;
	/**
	 * 调用的合约函数，非合约交易该字段为空
	 */
	private String calledAbi;
	/**
	 * 调用合约函数时传入的参数，非合约交易该字段为空
	 */
	private String abiParams;
	/**
	 * 结果交易id
仅针对合约交易
	 */
	private String extraTrxId;
	/**
	 * 合约调用结果
0 - 成功
1- 失败
	 */
	private Integer isCompleted;
	/**
	 * 0 - 普通转账
1 - 代理领工资
2 - 注册账户
3 - 注册代理
10 - 注册合约
11 - 合约充值
12 - 合约升级
	 */
	private Integer trxType;
	/**
	 * 资产标识
	 */
	private Integer assetId;
	/**
	 * 如果是合约交易则记录合约ID
	 */
	private String contractId;
	/**
	 * 创建时间
	 */
	private Date createdAt;
	/**
	 * 更新时间
	 */
	private Date updatedAt;
	/**
	 * 删除时间
	 */
	private Date deletedAt;

	/**
	 * 设置：交易ID
	 */
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	/**
	 * 获取：交易ID
	 */
	public String getTrxId() {
		return trxId;
	}
	/**
	 * 设置：快号ID
	 */
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	/**
	 * 获取：快号ID
	 */
	public String getBlockId() {
		return blockId;
	}
	/**
	 * 设置：块号
	 */
	public void setBlockNum(Long blockNum) {
		this.blockNum = blockNum;
	}
	/**
	 * 获取：块号
	 */
	public Long getBlockNum() {
		return blockNum;
	}
	/**
	 * 设置：出账地址
	 */
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	/**
	 * 获取：出账地址
	 */
	public String getFromAccount() {
		return fromAccount;
	}
	/**
	 * 设置：出账名称
	 */
	public void setFromAccountName(String fromAccountName) {
		this.fromAccountName = fromAccountName;
	}
	/**
	 * 获取：出账名称
	 */
	public String getFromAccountName() {
		return fromAccountName;
	}
	/**
	 * 设置：入账地址
	 */
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	/**
	 * 获取：入账地址
	 */
	public String getToAccount() {
		return toAccount;
	}
	/**
	 * 设置：入账名称
	 */
	public void setToAccountName(String toAccountName) {
		this.toAccountName = toAccountName;
	}
	/**
	 * 获取：入账名称
	 */
	public String getToAccountName() {
		return toAccountName;
	}
	/**
	 * 设置：出账金额
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	/**
	 * 获取：出账金额
	 */
	public Long getAmount() {
		return amount;
	}
	/**
	 * 设置：交易手续费
	 */
	public void setFee(Long fee) {
		this.fee = fee;
	}
	/**
	 * 获取：交易手续费
	 */
	public Long getFee() {
		return fee;
	}
	/**
	 * 设置：备注信息
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取：备注信息
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * 设置：交易被确认时间
	 */
	public void setTrxTime(Date trxTime) {
		this.trxTime = trxTime;
	}
	/**
	 * 获取：交易被确认时间
	 */
	public Date getTrxTime() {
		return trxTime;
	}
	/**
	 * 设置：调用的合约函数，非合约交易该字段为空
	 */
	public void setCalledAbi(String calledAbi) {
		this.calledAbi = calledAbi;
	}
	/**
	 * 获取：调用的合约函数，非合约交易该字段为空
	 */
	public String getCalledAbi() {
		return calledAbi;
	}
	/**
	 * 设置：调用合约函数时传入的参数，非合约交易该字段为空
	 */
	public void setAbiParams(String abiParams) {
		this.abiParams = abiParams;
	}
	/**
	 * 获取：调用合约函数时传入的参数，非合约交易该字段为空
	 */
	public String getAbiParams() {
		return abiParams;
	}
	/**
	 * 设置：结果交易id
仅针对合约交易
	 */
	public void setExtraTrxId(String extraTrxId) {
		this.extraTrxId = extraTrxId;
	}
	/**
	 * 获取：结果交易id
仅针对合约交易
	 */
	public String getExtraTrxId() {
		return extraTrxId;
	}
	/**
	 * 设置：合约调用结果
0 - 成功
1- 失败
	 */
	public void setIsCompleted(Integer isCompleted) {
		this.isCompleted = isCompleted;
	}
	/**
	 * 获取：合约调用结果
0 - 成功
1- 失败
	 */
	public Integer getIsCompleted() {
		return isCompleted;
	}
	/**
	 * 设置：0 - 普通转账
1 - 代理领工资
2 - 注册账户
3 - 注册代理
10 - 注册合约
11 - 合约充值
12 - 合约升级
	 */
	public void setTrxType(Integer trxType) {
		this.trxType = trxType;
	}
	/**
	 * 获取：0 - 普通转账
1 - 代理领工资
2 - 注册账户
3 - 注册代理
10 - 注册合约
11 - 合约充值
12 - 合约升级
	 */
	public Integer getTrxType() {
		return trxType;
	}
	/**
	 * 设置：资产标识
	 */
	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}
	/**
	 * 获取：资产标识
	 */
	public Integer getAssetId() {
		return assetId;
	}
	/**
	 * 设置：如果是合约交易则记录合约ID
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：如果是合约交易则记录合约ID
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * 设置：删除时间
	 */
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	/**
	 * 获取：删除时间
	 */
	public Date getDeletedAt() {
		return deletedAt;
	}
}
