package com.potato369.find.dynamic.utils;

/**
 * <pre>
 * @PackageName com.potato369.find.dynamic.utils
 * @ClassName EsPage
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/12/18 13:52
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class EsPage {

	// 指定的或是页面参数
	private int currentPage; // 当前页
	private int pageSize; // 每页显示多少条

	// 查询es结果
	private int recordCount; // 总记录数
	private List<Map<String, Object>> recordList; // 本页的数据列表

	// 计算
	private int pageCount; 		// 总页数
	private int beginPageIndex; // 页码列表的开始索引（包含）
	private int endPageIndex; 	// 页码列表的结束索引（包含）

	/**
	 * 只接受前4个必要的属性，会自动的计算出其他3个属性的值
	 *
	 * @param currentPage
	 * @param pageSize
	 * @param recordCount
	 * @param recordList
	 */
	public EsPage(int currentPage, int pageSize, int recordCount, List<Map<String, Object>> recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;

		// 计算总页码
		pageCount = (recordCount + pageSize - 1) / pageSize;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页数多于10页，则显示当前页附近的共10个页码
		else {
			// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			// 当前面的页码不足4个时，则显示前10个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 当后面的页码不足5个时，则显示后10个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1;
			}
		}
	}
}
