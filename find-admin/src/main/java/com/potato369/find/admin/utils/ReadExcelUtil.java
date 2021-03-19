package com.potato369.find.admin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadExcelUtil {
	private static final String EXCEL_XLS = ".xls";
	private static final String EXCEL_XLSX = ".xlsx";

	/**
	 * 读取excel数据
	 * 
	 * @throws Exception
	 *
	 */
	public static List<List<String>> readExcelInfo(String url) throws Exception {
		/*
		 * workbook:工作簿,就是整个Excel文档 sheet:工作表 row:行 cell:单元格
		 */

//      BufferedWriter bw = new BufferedWriter(new FileWriter(new File(url)));
//      支持excel2003、2007
		File excelFile = new File(url);// 创建excel文件对象
		InputStream is = new FileInputStream(excelFile);// 创建输入流对象
		checkExcelVaild(excelFile);
		Workbook workbook = getWorkBook(is, excelFile);
//      Workbook workbook = WorkbookFactory.create(is);//同时支持2003、2007、2010
//      获取Sheet数量
		int sheetNum = workbook.getNumberOfSheets();
//      创建二维数组保存所有读取到的行列数据，外层存行数据，内层存单元格数据
		List<List<String>> dataList = new ArrayList<List<String>>();
//      FormulaEvaluator formulaEvaluator = null;
//      遍历工作簿中的sheet,第一层循环所有sheet表
		for (int index = 0; index < sheetNum; index++) {
			Sheet sheet = workbook.getSheetAt(index);
			if (sheet == null) {
				continue;
			}
			log.info("表单行数：{}", sheet.getLastRowNum());
//            如果当前行没有数据跳出循环，第二层循环单sheet表中所有行
			for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
//                根据文件头可以控制从哪一行读取，在下面if中进行控制
				if (row == null) {
					continue;
				}
//                遍历每一行的每一列，第三层循环行中所有单元格
				List<String> cellList = new ArrayList<String>();
				for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
					Cell cell = row.getCell(cellIndex);
					System.out.println("遍历行中cell数据:" + getCellValue(cell));
					log.info("遍历行中cell数据：{}", getCellValue(cell));
					cellList.add(getCellValue(cell));
					log.info("第{}个:     cell个数：", cellIndex, cellList.size());
				}
				dataList.add(cellList);
				log.info("第{}行:     共几行：", rowIndex, dataList.size());
			}

		}
		is.close();
		return dataList;
	}

	/**
	 * 获取单元格的数据,暂时不支持公式
	 *
	 *
	 */
	public static String getCellValue(Cell cell) {
		CellType cellType = cell.getCellType();
		String cellValue = "";
		if (cell == null || cell.toString().trim().equals("")) {
			return null;
		}

		if (cellType == CellType.STRING) {
			cellValue = cell.getStringCellValue().trim();
			return cellValue = StrUtil.isEmpty(cellValue) ? "" : cellValue;
		}
		if (cellType == CellType.NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(cell)) { // 判断日期类型
				DateUtil.formatBetween(cell.getDateCellValue().getTime());
			} else { // 否
				cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
			}
			return cellValue;
		}
		if (cellType == CellType.BOOLEAN) {
			cellValue = String.valueOf(cell.getBooleanCellValue());
			return cellValue;
		}
		return null;

	}

	/**
	 * 判断excel的版本，并根据文件流数据获取workbook
	 * 
	 * @throws IOException
	 *
	 */
	public static Workbook getWorkBook(InputStream is, File file) throws Exception {

		Workbook workbook = null;
		if (file.getName().endsWith(EXCEL_XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (file.getName().endsWith(EXCEL_XLSX)) {
			workbook = new XSSFWorkbook(is);
		}

		return workbook;
	}

	/**
	 * 校验文件是否为excel
	 * 
	 * @throws Exception
	 *
	 *
	 */
	public static void checkExcelVaild(File file) throws Exception {
		String message = "该文件是EXCEL文件！";
		if (!file.exists()) {
			message = "文件不存在！";
			throw new Exception(message);
		}
		if (!file.isFile() || ((!file.getName().endsWith(EXCEL_XLS) && !file.getName().endsWith(EXCEL_XLSX)))) {
			log.info("{}==={}==={}", file.isFile(), file.getName().endsWith(EXCEL_XLS), file.getName().endsWith(EXCEL_XLSX));
			log.info("file.getName()==={}", file.getName());
			message = "文件不是Excel";
			throw new Exception(message);
		}
	}
	/*
	 * public static void main(String[] args) throws Exception {
	 * readExcelInfo("g://批量新增设备表.xlsx"); }
	 */
}
