package com.mp.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * excel导入导出工具
 *
 * @author duchong
 * @date 2019-11-13 13:57:26
 */
public class PoiUtil {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(PoiUtil.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    public static void checkFile(MultipartFile file) throws IOException {
        //判断文件是否存在
        if (null == file) {
            logger.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            logger.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }

    public static HSSFWorkbook getWorkBook(MultipartFile file) {
        HSSFWorkbook workbook = null;
        try {
            checkFile(file);
            workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return workbook;
    }


    /**
     * 批量导出
     *
     * @param request
     * @param response
     * @param headers
     * @param keys
     * @param lists
     */
    public static void batchExport(String[] headers, String[] keys, List<JSONObject> lists, HttpServletRequest request, HttpServletResponse response) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Sheet1");
        sheet.setDefaultColumnWidth(18);
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            XSSFCell headerCell = row.createCell(i);
            headerCell.setCellStyle(style);
            headerCell.setCellValue(headers[i]);
        }
        int rowIndex = 0;
        XSSFCell cell = null;
        for (JSONObject obj : lists) {
            XSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            rowIndex++;
            row = sheet.createRow(rowIndex);

            for (int i = 0; i < keys.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(obj.getString(keys[i]));
            }
        }
        String filename = "导出信息" + DateUtil.formatDate(new Date(), DateUtil.PATTERN_YYYYMMDDHHMMSS) + ".xlsx";
        String filepath = request.getRealPath("/") + filename;
        FileOutputStream out = new FileOutputStream(filepath);
        wb.write(out);
        out.close();
        downloadExcel(filepath, response);
    }

    /**
     * 下载
     * @param response
     * @param filepath
     */
    public static void downloadExcel(String filepath, HttpServletResponse response)
            throws IOException {
        File file = new File(filepath);
        String fileName = file.getName();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        response.setCharacterEncoding("utf-8");
        InputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] b = new byte[fis.available()];
        fis.read(b);
        OutputStream out = response.getOutputStream();
        out.write(b);
        out.flush();
        fis.close();
        if (file.exists()) {
            file.delete();
        }
        out.close();
    }
}
