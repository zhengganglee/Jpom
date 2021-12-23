package io.jpom.model.data;

import cn.hutool.core.io.FileUtil;
import io.jpom.model.BaseEnum;
import io.jpom.model.BaseWorkspaceModel;
import io.jpom.service.h2db.TableName;
import io.jpom.system.ConfigBean;

import java.io.File;

/**
 * @author bwcx_jzy
 * @since 2021/12/22
 */
@TableName(value = "COMMAND_EXEC_LOG", name = "命令执行记录")
public class CommandExecLogModel extends BaseWorkspaceModel {

	/**
	 * 命令ID
	 */
	private String commandId;

	/**
	 * 批次ID
	 */
	private String batchId;

	/**
	 * ssh Id
	 */
	private String sshId;
	/**
	 * @see Status
	 */
	private Integer status;

	/**
	 * 命令名称
	 */
	private String commandName;

	/**
	 * ssh 名称
	 */
	private String sshName;

	/**
	 * 参数
	 */
	private String params;
	/**
	 * 触发类型
	 */
	private Integer triggerExecType;

	public File logFile() {
		return FileUtil.file(CommandExecLogModel.logFileDir(this.getCommandId()), batchId, this.getId() + ".log");
	}

	/**
	 * log 存储目录
	 *
	 * @param commandId 命令ID
	 * @return 文件
	 */
	public static File logFileDir(String commandId) {
		return FileUtil.file(ConfigBean.getInstance().getDataPath(), "command_log", commandId);
	}

	public Integer getTriggerExecType() {
		return triggerExecType;
	}

	public void setTriggerExecType(Integer triggerExecType) {
		this.triggerExecType = triggerExecType;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public String getSshId() {
		return sshId;
	}

	public void setSshId(String sshId) {
		this.sshId = sshId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String getSshName() {
		return sshName;
	}

	public void setSshName(String sshName) {
		this.sshName = sshName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public enum Status implements BaseEnum {
		/**
		 *
		 */
		ING(0, "执行中"),
		DONE(1, "执行结束"),
		ERROR(2, "执行错误"),
		SESSION_ERROR(3, "会话异常"),
		;
		private final int code;
		private final String desc;

		Status(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		@Override
		public int getCode() {
			return code;
		}

		@Override
		public String getDesc() {
			return desc;
		}
	}
}