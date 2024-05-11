package ao.bolseiro.api.bolseiro.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.LocalDate;

public final class DateUtil {
    public static final String DIA_MES_ANO_PATTERN = "dd/MM/yyyy";
    public static final String ANO_MES_DIA_PATTERN = "yyyy-MM-dd";
    public static final String ANO_MES_DIA_SOMENTE_NUMERO_PATTERN = "yyyyMMdd";
    public static final String MES_DIA_ANO_PATTERN = "MM/dd/yyyy";
    public static final String DIA_MES_ANO_HORA_MINUTO_SEGUNDO_TIMEZONE_PATTERN = "dd-MM-yyyy'T'HH:mm:ss.SSSZ";
    public static final String ANO_MES_DIA_HORA_MINUTO_SEGUNDO_TIMEZONE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String ANO_MES_DIA_HORA_MINUTO_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String ANO_MES_DIA_HORA_MINUTO_SEGUNDO_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String ANO_MES_DIA_HORA_MINUTO_SEGUNDO_PATTERN_JUNTO = "yyyyMMddHHmmss";
    public static final String DIA_MES_ANO_HORA_MINUTO_SEGUNDO_PATTERN = "dd/MM/yyyy HH:mm:ss";
    public static final String DIA_MES_ANO_HORA_MINUTO_PATTERN = "dd/MM/yyyy HH:mm";
    public static final String HORA_MINUTO_SEGUNDO_PATTERN = "HH:mm:ss";

    public static final String ANO_QUATRO_DIGITOS_PATTERN = "yyyy";
    public static final String ANO_DOIS_DIGITOS_FINAIS_PATTERN = "yy";
    public static final String MES_PATTERN = "MM";
    public static final String DIA_PATTERN = "dd";

    private static final Locale LOCALE_DEFAULT = new Locale("pt", "AO");

    private DateUtil() {// not called
    }

    /**
     * Recupera o formato da data necessario.
     *
     * @param formato {@link String}.
     * @return {@link SimpleDateFormat}.
     */
    private static SimpleDateFormat getFormatter(String formato) {
        SimpleDateFormat sd = new SimpleDateFormat(formato, LOCALE_DEFAULT);
        sd.setLenient(false);
        return sd;
    }

    /**
     * Utilizar o metodo
     *
     * @param data1
     * @param data2
     * @return long
     */
    public static long calcularDiferencaDias(Date data1, Date data2) {
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(data1);
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(data2);
        long dif = cal1.getTimeInMillis() - cal2.getTimeInMillis();
        return dif / (24 * 60 * 60 * 1000);
    }

    /**
     * metodo responsavel por somaDias
     *
     * @param data
     * @param quantidadeDeDias
     * @return Date
     */
    public static java.util.Date somarDias(
            java.util.Date data,
            int quantidadeDeDias
    ) {
        return somarCampo(Calendar.DAY_OF_MONTH, data, quantidadeDeDias);
    }

    /**
     * metodo responsavel adicionar quantidade de dias na data.
     *
     * @param data
     * @param quantidadeDeDias
     * @return
     */
    public static java.util.Date somarDias(
            java.util.Date data,
            short quantidadeDeDias
    ) {
        return somarCampo(Calendar.DAY_OF_MONTH, data, quantidadeDeDias);
    }

    /**
     * metodo responsavel por somaCampo
     *
     * @param campo
     * @param data
     * @param quantidade
     * @return Date
     */
    public static java.util.Date somarCampo(
            int campo,
            java.util.Date data,
            int quantidade
    ) {
        Calendar calendar = getCalendarDaData(data);
        calendar.add(campo, quantidade);
        return calendar.getTime();
    }

    /**
     * Decrementa uma data a partir da sua escala, definida pelas constantes de
     * Calendar.
     *
     * @param data   a data a ser decrementada.
     * @param escala a escala.
     * @param valor  o valor a ser decrementado.
     * @return uma nova data, decrementada.
     */
    public static java.util.Date decrementarData(
            Date data,
            int escala,
            int valor
    ) {
        Calendar calendario = Calendar.getInstance();

        calendario.setTime(data);
        calendario.add(escala, -valor);

        return calendario.getTime();
    }

    /**
     * metodo responsavel por getCalendarDaData
     *
     * @param data
     * @return Calendar
     */
    protected static Calendar getCalendarDaData(java.util.Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return calendar;
    }

    /**
     * metodo responsavel por
     *
     * @param dataA
     * @param dataB
     * @return Boolean
     */
    public static Boolean maiorQue(Date dataA, Date dataB) {
        Boolean retorno = Boolean.FALSE;

        if (datasNaoNulas(dataA, dataB)) {
            retorno = dataA.after(dataB);
        }
        return retorno;
    }

    /**
     * metodo responsavel por
     *
     * @param dataA
     * @param dataB
     * @return Boolean
     */
    public static Boolean menorQue(Date dataA, Date dataB) {
        Boolean retorno = Boolean.FALSE;

        if (datasNaoNulas(dataA, dataB)) {
            retorno = dataA.before(dataB);
        }
        return retorno;
    }

    /**
     * metodo responsavel por
     *
     * @param dataA
     * @param dataB
     * @return Boolean
     */
    public static Boolean igualA(Date dataA, Date dataB) {
        Boolean retorno = Boolean.FALSE;

        if (dataA != null && dataB != null) {
            retorno = ((int) 0 == dataA.compareTo(
                    dataB
            ) ? Boolean.TRUE : Boolean.FALSE);
        }
        return retorno;
    }

    /**
     * metodo responsavel por
     *
     * @param dataA
     * @param dateB
     * @return Boolean
     */
    private static Boolean datasNaoNulas(Date dataA, Date dateB) {
        Boolean retorno = Boolean.FALSE;

        if (dataA != null && dateB != null) {
            retorno = Boolean.TRUE;
        }
        return retorno;
    }

    /**
     * metodo responsavel por Formatar a data e hora no padrao.
     *
     * @param data
     * @return String
     */
    public static String formatarDataHoraLocalePt(Date data) {
        return formatarData(data, "dd/MM/yyyy HH:mm:ss");
    }

    public static String formatarDataHoraTimestampLocalePt(Timestamp data) {
        return formatarDataTimestamp(data, "dd/MM/yyyy HH:mm:ss");
    }

    public static String formatarDataLocalePt(Date data) {
        return formatarData(data, "dd/MM/yyyy");
    }

    public static String formatarDataTimestamLocalePt(Timestamp data) {
        return formatarDataTimestamp(data, "dd/MM/yyyy");
    }

    public static String formatarDataTimestamLocaleEn(Timestamp data) {
        return formatarDataTimestamp(data, "yyyy-MM-dd");
    }

    /**
     * Recupera o timestamp para manter em banco de acordo com a data passada e seu temanho, assim determinando
     * o seu formato.
     *
     * @param data {@link String}
     * @return Timestamp.
     * @throws ParseException Excecao a ser lancada
     */
    public static Timestamp formataTimestampCompleto(String data) throws ParseException {
        String formato = null;

        if (!Utils.isEmpty(data)) {
            //padrao novo a ser utilizado (yyyy-MM-dd ....)
            if (data.matches("\\d{4}-\\d{2}-\\d{2}.*")) {
                if (data.length() == 10) {
                    formato = DateUtil.ANO_MES_DIA_PATTERN;
                } else if (data.length() == 16) {
                    formato = DateUtil.ANO_MES_DIA_HORA_MINUTO_PATTERN;
                } else {
                    formato = DateUtil.ANO_MES_DIA_HORA_MINUTO_SEGUNDO_PATTERN;
                }
                return new java.sql.Timestamp(
                        DateUtils.parseDate(data, formato).getTime());
            }
            //padrao antigo (dd/MM/yyyy ... )
            else {
                if (data.length() == 10) {
                    formato = DateUtil.DIA_MES_ANO_PATTERN;
                } else if (data.length() == 16) {
                    formato = DateUtil.DIA_MES_ANO_HORA_MINUTO_PATTERN;
                } else {
                    formato = DateUtil.DIA_MES_ANO_HORA_MINUTO_SEGUNDO_PATTERN;
                }
                return new java.sql.Timestamp(DateUtils.parseDate(data, formato).getTime());
            }
        }
        return null;
    }

    /**
     * metodo responsavel por Formatar a data de acordo o pattern. (Ex:
     * dd/MM/yyyy)
     *
     * @param data
     * @param pattern
     * @return String
     */
    public static String formatarData(Date data, String pattern) {
        String retorno = "";
        if (data != null && pattern != null) {
            SimpleDateFormat formatar = new SimpleDateFormat(pattern);
            retorno = formatar.format(data);
        }
        return retorno;
    }

    /**
     * metodo responsavel por Formatar a data de acordo o pattern. (Ex:
     * dd/MM/yyyy)
     *
     * @param data
     * @param pattern
     * @return String
     */
    public static String formatarDataTimestamp(Timestamp data, String pattern) {
        String retorno = "";
        if (data != null && pattern != null) {
            SimpleDateFormat formatar = new SimpleDateFormat(pattern);

            Date aux = new Date(data.getTime());

            retorno = formatar.format(aux);
        }
        return retorno;
    }

    /**
     * Converte String em Date conforme o formato passado.
     *
     * @param data    a string que representa a data.
     * @return a data.
     */

    public static java.util.Date converterStringToDate(String data)
            throws
            Exception {
        java.util.Date dataRetornada = new Date(
                converterStringParaTimestamp(data).getTime()
        );
        return dataRetornada;
    }

    /**
     * metodo responsavel por transformar uma data tipo Date, em long, porem
     * retorna em formato String
     *
     * @param data
     * @return String
     */
    public static String converterDateToLong(Date data) throws Exception {
        String retorno = null;
        if (data != null) {
            long aux = data.getTime();
            retorno = Long.toString(aux);
        }
        return retorno;
    }

    /**
     * metodo responsavel por obter a data sem a hora
     *
     * @param data
     * @return
     */
    public static java.util.Date obterDataSemHora(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * metodo responsavel por obter o valor do objeto Timestamp a partir do Date
     *
     * @param data
     * @return
     */
    public static Timestamp converterDateToTimestamp(java.util.Date data) {
        Timestamp dataAux = null;
        if (data != null) {
            dataAux = new Timestamp(data.getTime());
        }
        return dataAux;
    }

    /**
     * metodo responsavel por obter o valor do objeto Date a partir do Timestamp
     *
     * @param times
     * @return
     */
    public static java.util.Date converterTimestampToDate(Timestamp times) {
        Date dataAux = null;
        if (times != null) {
            dataAux = new Date(times.getTime());
        }
        return dataAux;
    }

    public static Timestamp converterStringParaTimestamp(String data) {
        Timestamp timestamp = null;
        try {
            String dataAux[] = data.split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(
                    Integer.valueOf(dataAux[0]),
                    (Integer.valueOf(dataAux[1]) - 1),
                    Integer.valueOf(dataAux[2])
            );

            timestamp = new Timestamp(calendar.getTimeInMillis());
        } catch (Exception e) {
            return timestamp;
        }
        return timestamp;
    }

    public static Timestamp converterStringParaTimestampTela(String data) {
        Timestamp timestamp = null;
        try {
            String dataAux[] = data.split("/");
            Calendar calendar = Calendar.getInstance();
            calendar.set(
                    Integer.valueOf(dataAux[2]),
                    (Integer.valueOf(dataAux[1]) - 1),
                    Integer.valueOf(dataAux[0])
            );

            timestamp = new Timestamp(calendar.getTimeInMillis());
        } catch (Exception e) {
            return timestamp;
        }
        return timestamp;
    }

    /**
     * metodo responsavel por obter a data sem a hora
     *
     * @param data
     * @return
     */
    public static java.util.Date obterDataSemHora(Timestamp data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * metodo responsavel por obter a data sem a hora
     *
     * @param data
     * @return
     */
    public static Long obterLongTimestampSemHora(Timestamp data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime().getTime();
    }

    public static Integer compararDataTimestampInformadaComDataAtual(String sdata)
            throws
            Exception {
        Integer time = null;
        if (sdata != null) {
            Timestamp data = converterStringParaTimestamp(sdata);

            java.util.Date d = new java.util.Date();
            Timestamp t2 = new Timestamp(d.getTime());
            time = (obterLongTimestampSemHora(data).compareTo(
                    obterLongTimestampSemHora(t2)
            ));
        }
        return time;
    }

    /**
     * O metodo e responsavel concatenar a data e hora de termino. Tomada como
     * hora limite o valor padrao de <b>23:59:59.0</b>
     *
     * @param dataTermino obrigatorio passar como parametro a String contendo o valor da
     *                    data de termino no formato java.util.Date <b>aaaa/MM/dd</b>
     * @return <b>aaaa/MM/dd 23:59:59.0</b>
     */
    public static String formatarDataComHoraTermino(String dataTermino) {
        String retorno = null;
        final String HORA_FINAL = " 23:59:59";

        if (dataTermino != null && dataTermino.length() == 10) {
            retorno = dataTermino.replaceAll(
                    "(\\d+)/(\\d+)/(\\d+)",
                    "$3-$2-$1"
            ).concat(HORA_FINAL);
        }
        return retorno;
    }

    /**
     * Recupera uma data a fim de uma String e formato.
     *
     * @param dataParam {@link String}.
     * @param formato   {@link String}.
     * @return {@link Date}.
     * @throws Exception Excecao a ser lancada.
     */
    public static java.util.Date formataData(String dataParam, String formato)
            throws
            Exception {
        java.util.Date retorno = null;
        try {
            retorno = getFormatter(formato).parse(dataParam);
        } catch (ParseException e) {
            throw new Exception(e);
        }
        return retorno;
    }

    /**
     * Recupera uma data a fim de uma String e formato.
     *
     * @param dataParam {@link String}.
     * @param formato   {@link String}.
     * @return {@link Date}.
     * @throws Exception Excecao a ser lancada.
     */
    public static java.sql.Date formataDataSQL(String dataParam, String formato)
            throws
            Exception {
        if (Utils.isEmpty(dataParam))
            return null;
        java.sql.Date retorno = null;
        try {
            retorno = new java.sql.Date(
                    getFormatter(formato).parse(dataParam).getTime()
            );
        } catch (ParseException e) {
            throw new Exception(e);
        }
        return retorno;
    }

    /**
     * Recupera uma data a fim de uma String e formato.
     *
     * @param dataParam {@link Date}.
     * @param formato   {@link String}.
     * @return {@link String}.
     * @throws Exception Excecao a ser lancada.
     */
    public static String formataData(java.util.Date dataParam, String formato) throws Exception {
        if (dataParam == null)
            return null;
        return getFormatter(formato).format(dataParam);
    }

    /**
     * Recupera uma data a fim de uma String e formato.
     *
     * @param dataParam {@link String}.
     * @param formato   {@link String}.
     * @return {@link Date}.
     * @throws Exception Excecao a ser lancada.
     */
    public static String mudaFormatoData(String dataParam, String formato)
            throws
            Exception {
        String retorno = null;
        try {
            java.util.Date data = formataData(dataParam, DIA_MES_ANO_PATTERN);
            retorno = getFormatter(formato).format(data);
        } catch (ParseException e) {
            throw new Exception(e);
        }
        return retorno;
    }

    /**
     * Recupera uma data a fim de uma String e formato.
     *
     * @param dataParam {@link String}.
     * @param formato   {@link String}.
     * @return {@link Date}.
     * @throws Exception Excecao a ser lancada.
     */
    public static java.util.Date mudaFormatoData(
            java.util.Date dataParam,
            String formato
    )
            throws
            Exception {
        java.util.Date retorno = null;
        try {
            retorno = getFormatter(formato).parse(
                    getFormatter(formato).format(dataParam)
            );
        } catch (ParseException e) {
            throw new Exception(e);
        }
        return retorno;
    }

    public static Integer getDataAnoQuatroDigitos(Timestamp data) {
        java.sql.Date dataAux = new java.sql.Date(data.getTime());
        return Integer.valueOf(formatarData(dataAux, ANO_QUATRO_DIGITOS_PATTERN));
    }

    public static Integer getAnoQuatroDigitosData() {
        return Integer.valueOf(
                formatarData(getDataCorrente(), ANO_QUATRO_DIGITOS_PATTERN)
        );
    }

    public static Integer getAnoDoisDigitosData() {
        return Integer.valueOf(
                formatarData(getDataCorrente(), ANO_DOIS_DIGITOS_FINAIS_PATTERN)
        );
    }

    public static Integer getAnoDoisDigitosData(Timestamp data) {
        java.sql.Date dataAux = new java.sql.Date(data.getTime());
        return Integer.valueOf(
                formatarData(dataAux, ANO_DOIS_DIGITOS_FINAIS_PATTERN)
        );
    }

    public static Date getDataCorrente() {
        java.util.Date dataAtual = new java.util.Date();
        return new java.sql.Date(dataAtual.getTime());
    }

    public static Date formataDataParaBanco(String dataParam) throws Exception {
        if (Utils.isEmpty(dataParam))
            return null;
        java.util.Date data = formataData(
                alteraFormatoDataBanco(dataParam),
                DateUtil.ANO_MES_DIA_PATTERN
        );
        return new Date(data.getTime());
    }

    public static String alteraFormatoDataBanco(String dataFront) {
        StringBuilder dataConvertida = null;
        if (!Utils.isEmpty(dataFront)) {
            dataConvertida = new StringBuilder();
            dataConvertida.append(dataFront.split("/")[2]);
            dataConvertida.append("-");
            dataConvertida.append(dataFront.split("/")[1]);
            dataConvertida.append("-");
            dataConvertida.append(dataFront.split("/")[0]);

            return dataConvertida.toString();
        }
        return null;
    }

    public static Timestamp formataDataParaBancoTimestamp(String dataParam)
            throws
            Exception {
        if (Utils.isEmpty(dataParam))
            return null;
        java.util.Date data = formataData(
                alteraFormatoDataBanco(dataParam),
                DateUtil.ANO_MES_DIA_PATTERN
        );
        return new Timestamp(data.getTime());
    }

    public static Timestamp formataDataParaBancoCompleto(String dataParam)
            throws
            Exception {
        if (Utils.isEmpty(dataParam))
            return null;
        java.util.Date data = formataData(
                alteraFormatoDataBanco(dataParam),
                DateUtil.DIA_MES_ANO_HORA_MINUTO_SEGUNDO_PATTERN
        );
        return new Timestamp(data.getTime());
    }

    public static String convertDataFormatoJsonParaQuery(String dateStr) {
        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataRetorno = "";
        try {
            dataRetorno = myFormat.format(fromUser.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataRetorno;
    }

    /**
     * Retorna o ano da data corrente.
     *
     * @param agora {@link Date} caso passe pega dela, senao pode passar null.
     * @return <code>int</code>
     */
    public static int getAno(Date agora) {
        if (Utils.isEmpty(agora)) {
            return Calendar.getInstance().get(Calendar.YEAR);
        } else {
            Calendar now = Calendar.getInstance();
            now.setTime(agora);
            return now.get(Calendar.YEAR);
        }
    }

    public static String convertDataFormatoTampstamptoDate(String dateStr) {
        SimpleDateFormat fromUser = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
        ); //2017-03-01T00:00:00.000+01:00  ou 2001-07-04T12:08:56.235-07:00
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataRetorno = "";
        try {
            dataRetorno = myFormat.format(fromUser.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataRetorno;
    }

    public static String formatarDataHoraSemSegTimestampLocalePt(Timestamp data) {
        return formatarDataTimestamp(data, "dd/MM/yyyy HH:mm");
    }

    /**
     * Indica se a data passada para comparacao é menor ou igual.
     *
     * @param dataA
     * @param dataB
     * @return Boolean
     */
    public static Boolean maiorIgualQue(Date dataA, Date dataB) {
        Boolean retorno = Boolean.FALSE;

        if (datasNaoNulas(dataA, dataB)) {
            retorno = dataA.after(dataB) || igualA(dataA, dataB);
        }
        return retorno;
    }

    /**
     * Indica se a data passada para comparacao é menor ou igual.
     *
     * @param dataA
     * @param dataB
     * @return Boolean
     */
    public static Boolean menorIgualQue(Date dataA, Date dataB) {
        Boolean retorno = Boolean.FALSE;

        if (datasNaoNulas(dataA, dataB)) {
            retorno = dataA.before(dataB) || igualA(dataA, dataB);
        }
        return retorno;
    }

    /**
     * Indica se a data passada esta entre.
     *
     * @param min
     * @param max
     * @param compare a comparar
     * @return <code>indicador</code>
     */
    public static boolean entre(Date min, Date max, Date compare) {
        LocalDate now = LocalDate.now();
        
        return now.isBefore(LocalDate.fromDateFields(max)) && now.isAfter(
                LocalDate.fromDateFields(min)
        ) || (igualA(min, compare) || igualA(max, compare));
    }

    public static boolean isDate(String date) {
        try {
            String regex = "\\d+";
            String[] dateParts = date.split("/");
            String day = dateParts[0];
            String month = dateParts[1];
            String year = dateParts[2];
            if (day.length() == 2 && month.length() == 2 && year.length(

            ) == 4 && day.matches(regex) && month.matches(regex) && (year.matches(
                    regex
            ))) {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

}
