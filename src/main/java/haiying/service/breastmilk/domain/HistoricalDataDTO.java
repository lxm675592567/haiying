package haiying.service.breastmilk.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;


public class HistoricalDataDTO implements Serializable {

	/**
	 * 患者guid
	 */
	private String hzguid;

	/**
	 * 患者weixinid
	 */
	private String weixinid;

	/**
	 * 组织
	 */
	private String org;

	/**
	 *
	 */
	private String location;

	/**
	 * 设备代码
	 */
	private String devicType;

	/**
	 * 设备名称
	 */
	private String bgdname;

	/**
	 * 数据
	 */
	private Map<String, Object> data;

	/**
	 * 检测日期
	 */
	private String bgdate;

	/**
	 * 数据guid
	 */
	private String dataGuid;

	/**
	 * pdf
	 */
	private String pdfdata;

	public String getHzguid() {
		return hzguid;
	}

	public HistoricalDataDTO setHzguid(String hzguid) {
		this.hzguid = hzguid;
		return this;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public HistoricalDataDTO setWeixinid(String weixinid) {
		this.weixinid = weixinid;
		return this;
	}

	public String getOrg() {
		return org;
	}

	public HistoricalDataDTO setOrg(String org) {
		this.org = org;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public HistoricalDataDTO setLocation(String location) {
		this.location = location;
		return this;
	}

	public String getDevicType() {
		return devicType;
	}

	public HistoricalDataDTO setDevicType(String devicType) {
		this.devicType = devicType;
		return this;
	}

	public String getBgdname() {
		return bgdname;
	}

	public HistoricalDataDTO setBgdname(String bgdname) {
		this.bgdname = bgdname;
		return this;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public HistoricalDataDTO setData(Map<String, Object> data) {
		this.data = data;
		return this;
	}

	public String getBgdate() {
		return bgdate;
	}

	public HistoricalDataDTO setBgdate(String bgdate) {
		this.bgdate = bgdate;
		return this;
	}

	public String getDataGuid() {
		return dataGuid;
	}

	public HistoricalDataDTO setDataGuid(String dataGuid) {
		this.dataGuid = dataGuid;
		return this;
	}

	public String getPdfdata() {
		return pdfdata;
	}

	public HistoricalDataDTO setPdfdata(String pdfdata) {
		this.pdfdata = pdfdata;
		return this;
	}
}
