package ao.bolseiro.api.bolseiro.util;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@SuppressWarnings("deprecation")
public final class Utils {
    private static final MyLogger LOGGER = MyLogger.getInstance(Utils.class);

    /**
     * constructor private
     */
    private Utils() {
    }

    /**
     * Verifica se atributo ? nulo ou vazio(String).
     *
     * @param atributo {@link Object}.
     * @return <code>boolean</code> indicando a verifica??o.
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object atributo) {
        if (atributo != null) {
            if (atributo instanceof String && "".equals(((String) atributo).trim())) {
                return true;
            } else if (atributo instanceof Collection) {
                return ((Collection) atributo).size() < 1;
            } else if (atributo instanceof Map) {
                return ((Map) atributo).size() < 1;
            } else if (atributo.getClass().isArray()) {
                return Array.getLength(atributo) < 1;
            } else if (atributo instanceof Number) {
                return (Number) atributo == null;
            } else if (atributo instanceof Date) {
                return (Date) atributo == null;
            }
            return false;
        }
        return true;
    }

    /**
     * Verifica se uma String e nula
     *
     * @param str
     * @return
     */
    public static Boolean isStringEmpty(String str) {
        if (null != str) {
            char[] array = str.toCharArray();
            for (char c : array) {
                if (!Character.isWhitespace(c)) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Adiciona o primeiro item vazio na lista.
     *
     * @param listaParamatro {@link List} retorno.
     * @return {@link List}.
     */
    public static List<?> montaPrimeiroItemVazio(List<?> listaParamatro) {
        List<Object> listaFormatada = new ArrayList<Object>();
        if (listaParamatro != null) {
            listaFormatada.add(0, "");
            listaFormatada.addAll(listaParamatro);
        }
        return listaFormatada;
    }

    /**
     * Monta a String de acordo com o tamanho maximo passado.
     *
     * @param comparar {@link String}.
     * @param valor    <code>int</code>.
     * @return {@link String} valor formatado.
     */
    public static String montaStringAteTamanhoMaximo(String comparar, int valor) {
        String valorFormatado = comparar;

        if (comparar.length() >= valor) {
            valorFormatado = comparar.substring(0, valor);
        }
        return valorFormatado;
    }

    /**
     * Gerador de senhas.
     */
    public static String gerarSenha() {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0, 6);
    }

    /**
     * Criptografa a String passada por par?metro
     */
    public static String Cripto(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(
                    "MD5"
            );
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString(array[i] & 0xFF | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * M?todo respons?vel por verificar se o conte?do da string informado no
     * par?metro ? num?rico.
     *
     * @param conteudo a string a ser analisada.
     * @return <code>true</code> se a string for numerica, caso contr?rio
     * <code>false</code>.
     */
    public static Boolean isNumeric(String conteudo) {
        Boolean condicao = Boolean.TRUE;

        if (conteudo != null && !conteudo.trim().equals("")) {
            for (int i = 0; i < conteudo.length(); i++) {
                Character caracterParametro = conteudo.charAt(i);

                if (!Character.isDigit(caracterParametro)) {
                    condicao = Boolean.FALSE;
                    break;
                }
            }
        } else {
            condicao = Boolean.FALSE;
        }
        return condicao;
    }

    public static String getIp() throws UnknownHostException {
        InetAddress IP = InetAddress.getLocalHost();
        return IP.getHostAddress();
    }

    public static Double trataValorNumericoRetiraTodosPontosEVigurlas(
            String texto
    ) {
        Double valor2 = null;
        String aux = "";
        if (texto != null) {
            aux = texto.replaceAll(".", "");
            aux = texto.replaceAll(",", ".");
            aux = aux.replace(".", "");
        }
        valor2 = Double.parseDouble(aux);

        return valor2;
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll(
                "[^\\p{ASCII}]",
                ""
        );
    }

    public static BigDecimal stringToBigDecimal(
            final String formattedString,
            final Locale locale
    ) {
        final DecimalFormatSymbols symbols;
        final char groupSeparatorChar;
        final String groupSeparator;
        final char decimalSeparatorChar;
        final String decimalSeparator;
        String fixedString;
        final BigDecimal number;

        symbols = new DecimalFormatSymbols(locale);
        groupSeparatorChar = symbols.getGroupingSeparator();
        decimalSeparatorChar = symbols.getDecimalSeparator();

        if (groupSeparatorChar == '.') {
            groupSeparator = "\\" + groupSeparatorChar;
        } else {
            groupSeparator = Character.toString(groupSeparatorChar);
        }

        if (decimalSeparatorChar == '.') {
            decimalSeparator = "\\" + decimalSeparatorChar;
        } else {
            decimalSeparator = Character.toString(decimalSeparatorChar);
        }
        fixedString = formattedString.replaceAll(groupSeparator, "");
        fixedString = fixedString.replaceAll(decimalSeparator, ".");
        number = new BigDecimal(fixedString);

        return (number);
    }

    public static String formatarValor(Double price, Locale locale) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        formatter.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));
        return formatter.format(price);
    }

    public static String formatarQuantidade(Double price, Locale locale) {
        NumberFormat formatter = NumberFormat.getNumberInstance(locale);
        return formatter.format(price);
    }

    public static String formatarValorCambio(Double price, Locale locale) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.000");
        formatter.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));
        return formatter.format(price);
    }

    public static Double removeVirgula(String valor) {
        valor = valor.replaceAll(",", ".");
        return Double.parseDouble(valor);
    }

    public static String formatarValorMoeda(int valor) {
        if (valor > 0) {
            DecimalFormat mask = new DecimalFormat("###,###,###.00");
            return mask.format(valor);
        } else {
            return "0";
        }
    }

    public static String capitalize(String aCapitalizar) {
        return !Utils.isEmpty(aCapitalizar) ? WordUtils.capitalize(
                aCapitalizar.toLowerCase()
        ) : null;
    }

    /**
     * Jurelmo Neto
     * Validates Google reCAPTCHA V2 or Invisible reCAPTCHA.
     *
     * @param secret             Secret key (key given for communication between your site and Google)
     * @param gRecaptchaResponse reCAPTCHA response from client side. (g-recaptcha-response)
     * @return true if validation successful, false otherwise.
     */
    public static boolean verifyReCaptcha(String secret, String gRecaptchaResponse) throws IOException {

        String url = "https://www.google.com/recaptcha/api/siteverify";
        String USER_AGENT = "Mozilla/5.0";

        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }

        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.jupiter.co.ao", 3128));

            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection(proxy);

            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String postParams = "secret=" + secret + "&response=" + gRecaptchaResponse;

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + postParams);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonNode jsonNode = new ObjectMapper().readTree(response.toString());
            return jsonNode.get("success").asBoolean();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    

	@SuppressWarnings("restriction")
	public static boolean validarReCaptchaComProxy(String secret, String gRecaptchaResponse, String urlProxy, int portaProxy) throws IOException {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String USER_AGENT = "Mozilla/5.0";

        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }

        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(urlProxy, portaProxy));

            URL obj = new URL(null, url, new sun.net.www.protocol.https.Handler());
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection(proxy);

            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String postParams = "secret=" + secret + "&response=" + gRecaptchaResponse;

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonNode jsonNode = new ObjectMapper().readTree(response.toString());
            return jsonNode.get("success").asBoolean();
        } catch (Exception e) {
            LOGGER.severe("Error ao obter o captcha", e);
            return false;
        }
    }

    /**
     * Validates Google reCAPTCHA V2 or Invisible reCAPTCHA.
     *
     * @param secretKey Secret key (key given for communication between your site and Google)
     * @param response  reCAPTCHA response from client side. (g-recaptcha-response)
     * @return true if validation successful, false otherwise.
     */
    public static boolean isCaptchaValid(String secretKey, String response) throws IOException {
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify?"
                    + "secret=" + secretKey
                    + "&response=" + response;
            InputStream res = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            res.close();

            JsonNode jsonNode = new ObjectMapper().readTree(jsonText.toString());
            return jsonNode.get("success").asBoolean();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

   

    
    /*
     * NOME: ERNESTO TADEU TCHITECULO SAMBONGO
     * DATA: 01-06-2020
     * 
     */
    public static void copyObjecto(Object source,Object target) {
    	BeanUtils.copyProperties(source, target);
    }
    /*
     * NOME: ERNESTO TADEU TCHITECULO SAMBONGO
     * DATA: 14-06-2020
     * 
     */
    public static LocalDateTime dateParaLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
   
    /*
     * NOME: ERNESTO TADEU TCHITECULO SAMBONGO
     * DATA: 22-07-2020
     * 
     */
    public static long diferencaEntreDatas(Date data1,Date data2) {		
    	return  Duration.between(data1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), data2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).toDays();
	}

    /*
     * NOME: ERNESTO TADEU TCHITECULO SAMBONGO
     * DATA: 14-06-2020
     * 
     */
    public static Date deLocalDateTimeParaDate(LocalDateTime dateToConvert) {
	    return java.util.Date
	      .from(dateToConvert.atZone(ZoneId.systemDefault())
	      .toInstant());
	}
    /*
     * NOME: ERNESTO TADEU TCHITECULO SAMBONGO
     * DATA: 14-06-2020
     * 
     */
    public static Date deStringParaData(String data) throws ParseException {
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		return formatar.parse(data);
	}
    /*
     * NOME: ERNESTO TADEU TCHITECULO SAMBONGO
     * DATA: 14-06-2020
     * 
     */
    
    
    public static Date deStringParaDataSemHora(String data) throws ParseException {
  		SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd");
  		return formatar.parse(data);
  	}
    
    public static Date deStringParaDataII(String data) throws ParseException {
		//SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatar.parse(data);
	}

    /*
     * NOME: ERNESTO TADEU TCHITECULO SAMBONGO
     * DATA: 29-06-2020
     * 
     */    
    public static Date adicionaDiasDate(Date data,Integer dias) {
    	return deLocalDateTimeParaDate(dateParaLocalDate(data).plusDays(dias));
	}
    
    /*
     * NOME: ERNESTO TADEU TCHITECULO SAMBONGO
     * DATA: 29-06-2020
     * 
     */  
    public static Gson instanciaJson() {
    	return  new Gson();
    }
    
    /*
     * NOME: ERNESTO TADEU TCHITECULO SAMBONGO
     * DATA: 03-07-2020
     * 
     */  
    public static String formataDataComecoAnoFimDia(Date data) {
		return new SimpleDateFormat("yyyy-MM-dd").format(data);
	}
    
    public static String formataDataDiaMesAno(Date data) {
		return new SimpleDateFormat("dd-MM-yyyy").format(data);
	}
    
    public static String decrypt(String secret, String cipherText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] cipherData = Base64.getDecoder().decode(cipherText);
        //byte[] cipherData = Base64.getMimeDecoder().decode(cipherText);
        byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secret.getBytes(StandardCharsets.UTF_8), md5);
        SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
        IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

        byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
        Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decryptedData = aesCBC.doFinal(encrypted);
        return new String(decryptedData, StandardCharsets.UTF_8);
    }
    
    public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) {

        int digestLength = md.getDigestLength();
        int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
        byte[] generatedData = new byte[requiredLength];
        int generatedLength = 0;

        try {
            md.reset();

            // Repeat process until sufficient data has been generated
            while (generatedLength < keyLength + ivLength) {

                // Digest data (last digest if available, password data, salt if available)
                if (generatedLength > 0)
                    md.update(generatedData, generatedLength - digestLength, digestLength);
                md.update(password);
                if (salt != null)
                    md.update(salt, 0, 8);
                md.digest(generatedData, generatedLength, digestLength);

                // additional rounds
                for (int i = 1; i < iterations; i++) {
                    md.update(generatedData, generatedLength, digestLength);
                    md.digest(generatedData, generatedLength, digestLength);
                }
                generatedLength += digestLength;
            }
            // Copy key and IV into separate byte arrays
            byte[][] result = new byte[2][];
            result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
            if (ivLength > 0)
                result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);
            return result;
        } catch (DigestException e) {
            throw new RuntimeException(e);

        } finally {
            // Clean out temporary data
            Arrays.fill(generatedData, (byte)0);
        }
    }
}
