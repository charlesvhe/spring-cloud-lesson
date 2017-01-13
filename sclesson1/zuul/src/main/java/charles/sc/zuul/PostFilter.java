package charles.sc.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.util.FileCopyUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * Created by Charles on 2016/8/26.
 */
public class PostFilter extends ZuulFilter {

    private static String KEY = "abcd1234efgh6789hijkmyth";
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;


    public PostFilter() {
        try {
            byte[] key = new byte[24];
            byte[] temp = KEY.getBytes("UTF-8");
            if (key.length > temp.length) {
                System.arraycopy(temp, 0, key, 0, temp.length);
            } else {
                System.arraycopy(temp, 0, key, 0, key.length);
            }

            SecretKey deskey = new SecretKeySpec(key, "DESede");
            encryptCipher = Cipher.getInstance("DESede");
            encryptCipher.init(Cipher.ENCRYPT_MODE, deskey);

            decryptCipher = Cipher.getInstance("DESede");
            decryptCipher.init(Cipher.DECRYPT_MODE, deskey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 99;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if (!ctx.sendZuulResponse()) {    // sendZuulResponse=false 跳过加密
            return null;
        }
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();

        if (Application.privatePattern.matcher(uri).find()) {
            ctx.setSendZuulResponse(true);
            InputStream rds = ctx.getResponseDataStream();
            try {
                byte[] data = FileCopyUtils.copyToByteArray(rds);
                byte[] encrypted = encryptCipher.doFinal(data);

                StringBuilder body = new StringBuilder();
                body.append("{\"encrypted\":\"");
                body.append(new String(Base64.encode(encrypted)));
                body.append("\"}");

                ctx.setResponseBody(body.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }
}
