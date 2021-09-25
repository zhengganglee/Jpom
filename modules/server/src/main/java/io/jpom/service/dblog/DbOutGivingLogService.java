/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 码之科技工作室
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.jpom.service.dblog;

import cn.hutool.db.Entity;
import io.jpom.model.data.OutGivingNodeProject;
import io.jpom.model.log.OutGivingLog;
import io.jpom.service.h2db.BaseDbCommonService;
import org.springframework.stereotype.Service;

/**
 * 分发日志
 *
 * @author bwcx_jzy
 * @date 2019/7/20
 */
@Service
public class DbOutGivingLogService extends BaseDbCommonService<OutGivingLog> {

	public DbOutGivingLogService() {
		super(OutGivingLog.TABLE_NAME, "id", OutGivingLog.class);
	}

	@Override
	public void insert(OutGivingLog outGivingLog) {
		outGivingLog.setStartTime(System.currentTimeMillis());
		if (outGivingLog.getStatus() == OutGivingNodeProject.Status.Cancel.getCode()) {
			outGivingLog.setEndTime(System.currentTimeMillis());
		}
		super.insert(outGivingLog);
	}

	@Override
	public int update(OutGivingLog outGivingLog) {
		Entity entity = new Entity();
		entity.set("status", outGivingLog.getStatus());
		// 结束
		entity.set("endTime", System.currentTimeMillis());
		entity.set("result", outGivingLog.getResult());
		//
		Entity where = new Entity();
		where.set("id", outGivingLog.getId());
		return super.update(entity, where);
	}
}
