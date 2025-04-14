package com.app.financial.quotation.adapter.b3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeitorExcelParaJson {
    public static void main(String[] args) {
        String caminhoArquivo = "src/main/resources/importB3.xlsx";

        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Pular cabeçalho
            if (rowIterator.hasNext()) rowIterator.next();

            List<RegistroExcel> registros = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                RegistroExcel registro = new RegistroExcel();

//                private String produto; 0
//                private String instituicao; 1
//                private String conta; 2
//                private String codigoNegociacao; 3
//                private String cnpjFundo; 4
//                private String codigoIsinDistribuicao; 5
//                private String tipo; 6
//                private String administrador; 7
//                private String quantidade; 8
//                private String quantidadeDisponivel; 9
//                private String quantidadeIndisponivel; 10
//                private String motivo; 11
//                private String precoFechamento; 12
//                private String valorAtualizado; 13


                registro.setProduto(getCellValue(row.getCell(0)));
                registro.setInstituicao(getCellValue(row.getCell(1)));
                registro.setConta(getCellValue(row.getCell(2)));
                registro.setCodigoNegociacao(getCellValue(row.getCell(3)));
                registro.setCnpjFundo(getCellValue(row.getCell(4)));
                registro.setCodigoIsinDistribuicao(getCellValue(row.getCell(5)));
                registro.setTipo(getCellValue(row.getCell(6)));
                registro.setAdministrador(getCellValue(row.getCell(7)));
                registro.setQuantidade(getCellValue(row.getCell(8)));
                registro.setQuantidadeDisponivel(getCellValue(row.getCell(9)));
                registro.setQuantidadeIndisponivel(getCellValue(row.getCell(10)));
                registro.setMotivo(getCellValue(row.getCell(11)));
                registro.setPrecoFechamento(getCellValue(row.getCell(12)));
                registro.setValorAtualizado(getCellValue(row.getCell(13)));

                registros.add(registro);
            }

            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            String json = mapper.writeValueAsString(registros);
            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getCellValue(Cell cell) {
        return (cell != null) ? cell.toString() : "";
    }
}
