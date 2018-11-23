package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-28 16:44:08
 */
@TableName("mis_yy_ques")
public class MisYyQuesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 问题
	 */
	private String question;
	/**
	 * 
	 */
	private String answerA;
	/**
	 * 
	 */
	private String answerB;
	/**
	 * 
	 */
	private String answerC;
	/**
	 * 
	 */
	private String answerD;
	/**
	 * 关键字
	 */
	private String keyword;
	/**
	 * 使用情况0：未使用1：已使用
	 */
	private Integer status;
	/**
	 * 奖励类型0：信用钻1：算力2：ctc
	 */
	private Integer type;
	/**
	 * 奖励数量
	 */
	private BigDecimal count;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 题目时间
	 */
	private String useTime;
	/**
	 * 正确答案
	 */
	private String answerRight;
	/**
	 * 语言设置0:中文1：英文
	 */
	private String language;

	/**
	 * 格式化更新时间
	 */
	@TableField(exist = false)
	private String updateTimeVo;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：问题
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * 获取：问题
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * 设置：
	 */
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	/**
	 * 获取：
	 */
	public String getAnswerA() {
		return answerA;
	}
	/**
	 * 设置：
	 */
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	/**
	 * 获取：
	 */
	public String getAnswerB() {
		return answerB;
	}
	/**
	 * 设置：
	 */
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	/**
	 * 获取：
	 */
	public String getAnswerC() {
		return answerC;
	}
	/**
	 * 设置：
	 */
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	/**
	 * 获取：
	 */
	public String getAnswerD() {
		return answerD;
	}
	/**
	 * 设置：关键字
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * 获取：关键字
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * 设置：使用情况0：未使用1：已使用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：使用情况0：未使用1：已使用
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：奖励类型0：信用钻1：算力2：ctc
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：奖励类型0：信用钻1：算力2：ctc
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：奖励数量
	 */
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	/**
	 * 获取：奖励数量
	 */
	public BigDecimal getCount() {
		return count;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：题目时间
	 */
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	/**
	 * 获取：题目时间
	 */
	public String getUseTime() {
		return useTime;
	}
	/**
	 * 设置：正确答案
	 */
	public void setAnswerRight(String answerRight) {
		this.answerRight = answerRight;
	}
	/**
	 * 获取：正确答案
	 */
	public String getAnswerRight() {
		return answerRight;
	}
	/**
	 * 设置：语言设置0:中文1：英文
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * 获取：语言设置0:中文1：英文
	 */
	public String getLanguage() {
		return language;
	}

	public String getUpdateTimeVo() {
		if (null == getUpdateTime()) {
			return null;
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getUpdateTime()),
				io.credittag.mis.modules.ctc.constant.ConstantField.dateFormatPattern);
	}
}
